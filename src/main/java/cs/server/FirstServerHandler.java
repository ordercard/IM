package cs.server;

import cs.util.*;
import cs.entry.Packet;
import cs.util.request.LoginRequestPacket;
import cs.util.request.MessageRequestPacket;
import cs.util.response.LoginResponsePacket;
import cs.util.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 11:29 2018/10/11 2018
 * @Modify:
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(new Date() + ": 收到消息");

        ByteBuf requestByteBuf = (ByteBuf) msg;

        Packet packet = EDcode.INSTANCE.decode(requestByteBuf);

        if (packet instanceof LoginRequestPacket) {
            // 登录流程
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            System.out.println(loginResponsePacket.getCommand());
            if (valid(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + ": 登录成功!");
            } else {
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
                System.out.println(new Date() + ": 登录失败!");
            }
            // 登录响应
            ByteBuf responseByteBuf = EDcode.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);



        }

        else if (packet instanceof MessageRequestPacket){

            MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
            messageResponsePacket.setMessage("服务端回复 内容为【" + messageRequestPacket.getMessage() + "】");
            ByteBuf responseByteBuf = EDcode.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }


    }
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
//    public  void  channelActive(ChannelHandlerContext context){
//        System.out.println(new Date() + ":客户端写出数据");
//
//        // 1.获取数据
//        ByteBuf buffer = getByteBuf(context);
//
//        // 2.写数据
//        context.channel().writeAndFlush(buffer);
//    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "你好，连接已经成功正在准备验证权限!".getBytes(Charset.forName("utf-8"));

        ByteBuf buffer = ctx.alloc().buffer();

        buffer.writeBytes(bytes);

        return buffer;
    }

}
