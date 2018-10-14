package cs.server;

import cs.util.*;
import cs.util.request.LoginRequestPacket;
import cs.util.response.LoginResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 20:50 2018/10/11 2018
 * @Modify:
 */
@ChannelHandler.Sharable
public class LoginRequestHandler  extends SimpleChannelInboundHandler<LoginRequestPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
//        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
//        loginResponsePacket.setVersion(msg.getVersion());
//        System.out.println(loginResponsePacket.getCommand());
//        if (valid(msg)) {
//            loginResponsePacket.setSuccess(true);
//            LoginUtil.markAsLogin(ctx.channel());
//            System.out.println(new Date() + ": 登录成功!");
//        } else {
//            loginResponsePacket.setReason("账号密码校验失败");
//            loginResponsePacket.setSuccess(false);
//            System.out.println(new Date() + ": 登录失败!");
//        }
//        // 登录响应
//        ByteBuf responseByteBuf = EDcode.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
//        ctx.channel().writeAndFlush(responseByteBuf);

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUserName());

        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            String userId = IDUtil.randomId();
            loginResponsePacket.setUserId(userId);
            System.out.println("[" + loginRequestPacket.getUserName() + "]登录成功");
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUserName()), ctx.channel());
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }

        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);


    }
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        //可以在这儿做一些登录验证
        return true;
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) {

        SessionUtil.unBindSession(ctx.channel());
    }


    private static class  Handler {
        private static   LoginRequestHandler loginRequestHandler =new LoginRequestHandler();

    }

    public  static LoginRequestHandler  getReqhander(){
        return Handler.loginRequestHandler;
    }
}
