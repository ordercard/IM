package cs.server;

import cs.entry.Packet;
import cs.util.SessionUtil;
import cs.util.response.LogoutResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 17:10 2018/10/13 2018
 * @Modify:
 */
public class LogoutRequestHandler  extends SimpleChannelInboundHandler<LogoutRequestHandler> {
    public static final SimpleChannelInboundHandler<? extends Packet> INSTANCE = new ListGroupMembersRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestHandler msg) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(logoutResponsePacket);

    }
}
