package cs.util.hander;

import cs.entry.Packet;
import cs.util.EDcode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 20:54 2018/10/11 2018
 * @Modify:
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        EDcode.INSTANCE.encode(out,msg);
    }
}
