package cs.client;

import cs.util.*;
import cs.entry.Packet;
import cs.util.request.LoginRequestPacket;
import cs.util.response.LoginResponsePacket;
import cs.util.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 11:42 2018/10/11 2018
 * @Modify:
 */
public class FirstClientHandler  extends ChannelInboundHandlerAdapter {


    @Override
    public  void  channelActive(ChannelHandlerContext context){
        System.out.println(new Date()+"客户登陆");
        LoginRequestPacket loginRequestPacket =new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("flash");
        loginRequestPacket.setPassword("pwd");
        ByteBuf     byteBuf = EDcode.INSTANCE.encode(context.alloc(),loginRequestPacket);
        context.channel().writeAndFlush(byteBuf);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
      ByteBuf byteBuf =(ByteBuf) msg;
        Packet packet = EDcode.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket  = (LoginResponsePacket) packet;

            if (loginResponsePacket.isSuccess()) {
                LoginUtil.markAsLogin(ctx.channel());
                System.out.println(new Date() + ": 客户端登录成功");
            } else {
                System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }

        }else if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
        }


    }
}
