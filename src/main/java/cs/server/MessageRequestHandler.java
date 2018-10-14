package cs.server;

import cs.entry.Packet;
import cs.util.*;
import cs.util.request.MessageRequestPacket;
import cs.util.response.MessageResponsePacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 20:55 2018/10/11 2018
 * @Modify:
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    public static final SimpleChannelInboundHandler<? extends Packet> INSTANCE =  new MessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {

////        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
////        System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
////        messageResponsePacket.setMessage("服务端回复 内容为【" + messageRequestPacket.getMessage() + "】");
////        ByteBuf responseByteBuf = EDcode.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
////        ctx.channel().writeAndFlush(responseByteBuf);





        // 1.拿到消息发送方的会话信息
        Session session = SessionUtil.getSession(ctx.channel());
        // 2.通过消息发送方的会话信息构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());
        // 3.拿到消息接收方的 channel
        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

        // 4.将消息发送给消息接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageResponsePacket);
        } else {
            System.err.println("[" + session.getUserId() + "] 不在线，发送失败!");
        }
    }
}