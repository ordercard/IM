package netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 14:10 2018/9/30 2018
 * @Modify:
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        int part=8000;
         ChannelFuture channelFuture=null;
         channelFuture = serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
                                System.out.println(s);
                            }

//                            @Override
//                            protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
//                                System.out.println(msg);
//                            }

                        });
                    }
                })
                .bind(8000).addListener(future -> {
                    if (future.isSuccess()){
                        System.out.println("端口绑定成功");
                    }else {
                        System.out.println("端口绑定失败");
                        bind(serverBootstrap,part+1);
                    }
                });
               //  .sync();
      //  channelFuture.channel().closeFuture().sync();



    }


    private static ChannelFuture bind(final ServerBootstrap serverBootstrap, final int port) throws InterruptedException {
     return    serverBootstrap.bind(port).addListener(x->{
            if (x.isSuccess()){
                System.out.println("端口绑定成功");
            }else {
                System.out.println("端口绑定失败");
              bind(serverBootstrap,port+1);
            }
        });

    }
}

/*
首先看到，我们创建了两个NioEventLoopGroup，这两个对象可以看做是传统IO编程模型的两大线程组，bossGroup表示监听端口，accept 新连接的线程组，
workerGroup表示处理每一条连接的数据读写的线程组，不理解的同学可以看一下上一小节《Netty是什么》。用生活中的例子来讲就是，一个工厂要运作，
必然要有一个老板负责从外面接活，然后有很多员工，负责具体干活，老板就是bossGroup，员工们就是workerGroup，bossGroup接收完连接，扔给workerGroup去处理。
接下来 我们创建了一个引导类 ServerBootstrap，这个类将引导我们进行服务端的启动工作，直接new出来开搞。
我们通过.group(bossGroup, workerGroup)给引导类配置两大线程组，这个引导类的线程模型也就定型了。
然后，我们指定我们服务端的 IO 模型为NIO，我们通过.channel(NioServerSocketChannel.class)来指定 IO 模型，当然，这里也有其他的选择，
如果你想指定 IO 模型为 BIO，那么这里配置上OioServerSocketChannel.class类型即可，当然通常我们也不会这么做，因为Netty的优势就在于NIO。
接着，我们调用childHandler()方法，给这个引导类创建一个ChannelInitializer，这里主要就是定义后续每条连接的数据读写，业务处理逻辑，不理解没关系，
在后面我们会详细分析。ChannelInitializer这个类中，我们注意到有一个泛型参数NioSocketChannel，这个类呢，就是 Netty 对 NIO 类型的连接的抽象，
而我们前面NioServerSocketChannel也是对 NIO 类型的连接的抽象，NioServerSocketChannel和NioSocketChannel的概念可以和 BIO 编程模型中的ServerSocket以及Socket两个概念对应上
我们的最小化参数配置到这里就完成了，我们总结一下就是，要启动一个Netty服务端，必须要指定三类属性，分别是线程模型、IO 模型、连接读写处理逻辑，
有了这三者，之后在调用bind(8000)，我们就可以在本地绑定一个 8000 端口启动起来，以上这段代码读者可以直接拷贝到你的 IDE 中运行。...

在上面代码中我们绑定了 8000 端口，接下来我们实现一个稍微复杂一点的逻辑，我们指定一个起始端口号，比如 1000，然后呢，我们从1000号端口往上找一个端口，
直到这个端口能够绑定成功，比如 1000 端口不可用，我们就尝试绑定 1001，然后 1002，依次类推。

serverBootstrap.bind(8000);这个方法呢，它是一个异步的方法，调用之后是立即返回的，他的返回值是一个ChannelFuture，
我们可以给这个ChannelFuture添加一个监听器GenericFutureListener，然后我们在GenericFutureListener的operationComplete方法里面，我们可以监听端口是否绑定成功，接下来是监测端口是否绑定成功的代码片段...
我们接下来从 1000 端口号，开始往上找端口号，直到端口绑定成功，我们要做的就是在 if (future.isSuccess())的else逻辑里面重新绑定一个递增的端口号，接下来，我们把这段绑定逻辑抽取出一个bind方法
 */