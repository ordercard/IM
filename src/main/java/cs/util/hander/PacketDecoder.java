package cs.util.hander;

import cs.util.EDcode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 20:22 2018/10/11 2018
 * @Modify:
 */
public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(EDcode.INSTANCE.decode(in));

    }
}
