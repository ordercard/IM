package cs.server;

import cs.entry.Packet;
import cs.util.SessionUtil;
import cs.util.request.GroupMessageRequestPacket;
import cs.util.request.MessageRequestPacket;
import cs.util.response.GroupMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 20:48 2018/10/14 2018
 * @Modify:
 */
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    public static final SimpleChannelInboundHandler<? extends Packet> INSTANCE = new GroupMessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket msg) throws Exception {

        ChannelGroup group = SessionUtil.getChannelGroup(msg.getToGroupId());
        GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
        responsePacket.setFromGroupId(msg.getToGroupId());
        responsePacket.setMessage(msg.getMessage());
        responsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));

        group.writeAndFlush(responsePacket);


    }
}
