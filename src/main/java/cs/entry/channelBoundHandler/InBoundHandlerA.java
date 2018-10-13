package cs.entry.channelBoundHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 19:54 2018/10/11 2018
 * @Modify:
 */
public class InBoundHandlerA extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InBoundHandlerA: " + msg);
        ctx.channel().writeAndFlush(msg);//如果写出了，就代表 读取结束，在调用读取的hander就会出错
      //  super.channelRead(ctx, msg);
    }
}