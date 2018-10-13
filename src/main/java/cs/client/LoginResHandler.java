package cs.client;

import cs.util.*;
import cs.util.request.LoginRequestPacket;
import cs.util.response.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 20:50 2018/10/11 2018
 * @Modify:
 */
public class LoginResHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

//    @Override
//    public  void  channelActive(ChannelHandlerContext context){
//        System.out.println(new Date()+"客户登陆");
//        LoginRequestPacket loginRequestPacket =new LoginRequestPacket();
//        loginRequestPacket.setUserId(UUID.randomUUID().toString());
//        loginRequestPacket.setUsername("flash");
//        loginRequestPacket.setPassword("pwd");
//        ByteBuf     byteBuf = EDcode.INSTANCE.encode(context.alloc(),loginRequestPacket);
//        context.channel().writeAndFlush(byteBuf);
//    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
//        LoginResponsePacket loginResponsePacket  = (LoginResponsePacket) msg;
//
//        if (loginResponsePacket.isSuccess()) {
//            LoginUtil.markAsLogin(ctx.channel());
//            System.out.println(new Date() + ": 客户端登录成功");
//        } else {
//            System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
//        }




        String userId = msg.getUserId();
        String userName = msg.getUserName();

        if (msg.isSuccess()) {
            System.out.println("[" + userName + "]登录成功，userId 为: " + msg.getUserId());
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
        } else {
            System.out.println("[" + userName + "]登录失败，原因：" + msg.getReason());
        }

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
        System.out.println("客户端连接被关闭!");
    }
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
