package cs.entry.channelBoundHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 19:55 2018/10/11 2018
 * @Modify:
 */
public class InBoundHandlerC extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InBoundHandlerC: " + msg);
     //   ctx.channel().writeAndFlush(msg);
         super.channelRead(ctx, msg);

    }
}
