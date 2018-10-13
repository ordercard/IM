package cs.client;

import cs.util.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 20:55 2018/10/11 2018
 * @Modify:
 */
public class MessageResHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) {

//        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
//        System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
//        messageResponsePacket.setMessage("服务端回复 内容为【" + messageRequestPacket.getMessage() + "】");
//        ByteBuf responseByteBuf = EDcode.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
//        ctx.channel().writeAndFlush(responseByteBuf);


        String fromUserId = messageResponsePacket.getFromUserId();
        String fromUserName = messageResponsePacket.getFromUserName();
        System.out.println(fromUserId + ":" + fromUserName + " -> " + messageResponsePacket .getMessage());
    }
}