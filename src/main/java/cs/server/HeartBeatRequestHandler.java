package cs.server;

import cs.util.hander.HeartBeatResponsePacket;
import cs.util.hander.KeepHeartPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 23:29 2018/10/14 2018
 * @Modify:
 */
@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<KeepHeartPacket> {
    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    private HeartBeatRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, KeepHeartPacket requestPacket) {
        ctx.writeAndFlush(new HeartBeatResponsePacket());
    }
}

