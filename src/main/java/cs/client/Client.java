package cs.client;

import cs.client.command.ConsoleCommandManage;
import cs.client.command.console.LoginConsoleCommand;
import cs.util.*;
import cs.util.hander.IMIdleStateHandler;
import cs.util.hander.PacketDecoder;
import cs.util.hander.PacketEncoder;
import cs.util.hander.Spliter;
import cs.util.request.LoginRequestPacket;
import cs.util.request.MessageRequestPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 10:20 2018/10/11 2018
 * @Modify:
 */
public class Client {

    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup workGroup  =  new NioEventLoopGroup();
        Bootstrap bootstrap =new Bootstrap();
        bootstrap.group(workGroup).channel(NioSocketChannel.class).
                  handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) {

              //  ch.pipeline().addLast(new FirstClientHandler());
              //  ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                ch.pipeline().addLast(new IMIdleStateHandler());
                ch.pipeline().addLast(new Spliter());
                ch.pipeline().addLast(new PacketDecoder());
                ch.pipeline().addLast(new LoginResHandler());
                ch.pipeline().addLast(new MessageResHandler());
                ch.pipeline().addLast(new CreateGroupResponseHandler());
                ch.pipeline().addLast(new JoinGroupResponseHandler());
                // 退群响应处理器
                ch.pipeline().addLast(new QuitGroupResponseHandler());
                // 获取群成员响应处理器
                ch.pipeline().addLast(new ListGroupMembersResponseHandler());
                //登出响应处理
                ch.pipeline().addLast(new LogoutResponseHandler());
                ch.pipeline().addLast(new PacketEncoder());

            }
        });
        connect(bootstrap, HOST, PORT, MAX_RETRY);

    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
                Channel channel =( (ChannelFuture)future).channel();
                // 连接成功之后，启动控制台线程
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }


    private static void startConsoleThread(Channel channel) {

        Scanner sc = new Scanner(System.in);
        ConsoleCommandManage consoleCommandManager = new ConsoleCommandManage();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        new Thread(() -> {
            while (!Thread.interrupted()) {
//                if (LoginUtil.hasLogin(channel)) {
//                    System.out.println("请输入消息发送至服务端: ");
//                    Scanner sc = new Scanner(System.in);
//                    String line = sc.nextLine();
//
//                    MessageRequestPacket packet = new MessageRequestPacket();
//                    packet.setMessage(line);
//                    ByteBuf byteBuf = EDcode.INSTANCE.encode(channel.alloc(), packet);
//                    channel.writeAndFlush(byteBuf);
//                }
       //         System.out.println(SessionUtil.hasLogin(channel));
              if (!SessionUtil.hasLogin(channel)){
                  loginConsoleCommand.execute(sc,channel);
              } else {
                  System.out.println("请输入一下命令");
                  for(String key :consoleCommandManager.getConsoleCommandSendMap().keySet() ){
                      System.out.println("*****>>>"+key);
                  }
                  consoleCommandManager.execute(sc,channel);
              }

            }
        }).start();
    }

    public static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
