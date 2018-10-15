package cs.util.hander;

import cs.entry.Command;
import cs.entry.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 23:25 2018/10/14 2018
 * @Modify:
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.executor().scheduleAtFixedRate(() -> {
            ctx.writeAndFlush(new KeepHeartPacket());
        }, HEARTBEAT_INTERVAL, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);

        super.channelActive(ctx);
    }
}