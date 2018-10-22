package cs.server;

import cs.util.hander.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 10:20 2018/10/11 2018
 * @Modify:
 */
public class Server {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

         ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {

//                        ch.pipeline().addLast(new InBoundHandlerA());
//                        ch.pipeline().addLast(new InBoundHandlerB());
//                        ch.pipeline().addLast(new InBoundHandlerC());
//
//                        // outBound，处理写数据的逻辑链
//                        ch.pipeline().addLast(new OutBoundHandlerA());
//                        ch.pipeline().addLast(new OutBoundHandlerB());
//                        ch.pipeline().addLast(new OutBoundHandlerC());
                 //       ch.pipeline().addLast(new FirstServerHandler());


                    //  ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(EDHandler.INSTANCE);
                     // ch.pipeline().addLast(new PacketDecoder());
                    //  ch.pipeline().addLast(new LoginRequestHandler());
                        //netty提供了多个连接共享一个handler的方式
                        ch.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                        ch.pipeline().addLast(LoginRequestHandler.getReqhander());
                        ch.pipeline().addLast(AuthHandler.INSTANCE);
                        ch.pipeline().addLast(IMGenHandler.INSTANCE);
                        ch.pipeline().addLast(new AuthHandler());
//                        ch.pipeline().addLast(new MessageRequestHandler());
//                        ch.pipeline().addLast(new CreateGroupRequestHandler());
//                        ch.pipeline().addLast(new JoinGroupRequestHandler());
//                        ch.pipeline().addLast(new ListGroupMembersRequestHandler());
//                        ch.pipeline().addLast(new QuitGroupRequestHandler());
//                        ch.pipeline().addLast(new GroupMessageRequestHandler());
//                        ch.pipeline().addLast(new LogoutRequestHandler());
 //                       ch.pipeline().addLast(new PacketEncoder());

                    }
                });
                bind(serverBootstrap, PORT);
    }


    /*

    Netty 里面很多方法都是异步的操作，在业务线程中如果要统计这部分操作的时间，都需要使用监听器回调的方式来统计耗时，如果在 NIO 线程中调用，就不需要这么干。
1.对于耗时的操作，我们需要把这些耗时的操作丢到我们的业务线程池中去处理

xxx.writeAndFlush().addListener(future -> {
            if (future.isDone()) {
                // 4. balabala 其他的逻辑
                long time =  System.currentTimeMillis() - begin;
            }
        });writeAndFlush() 方法会返回一个 ChannelFuture 对象，我们给这个对象添加一个监听器，然后在回调方法里面，我们可以监听这个方法执行的结果，进而再执行其他逻辑，最后统计耗时，这样统计出来的耗时才是最准确的。
     */
    private static ChannelFuture bind(final ServerBootstrap serverBootstrap, final int port) {
        return
                serverBootstrap.bind(port).addListener(x->{
            if (x.isSuccess()){
                System.out.println("端口绑定成功");
            }else {
                System.out.println("端口绑定失败");
                bind(serverBootstrap,port+1);
            }
        });

    }
}
