package cs.server;

import cs.entry.Packet;
import cs.util.SessionUtil;
import cs.util.request.JoinGroupRequestPacket;
import cs.util.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 16:38 2018/10/13 2018
 * @Modify:
 */
public class JoinGroupRequestHandler  extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    public static final SimpleChannelInboundHandler<? extends Packet> INSTANCE = new JoinGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) throws Exception {

        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());
// 2. 构造加群响应发送给客户端
        JoinGroupResponsePacket responsePacket = new JoinGroupResponsePacket();

        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);
        ctx.channel().writeAndFlush(responsePacket);
    }
}
