package cs.util.hander;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Auther :huiqiang
 * @Description : 心跳检测
 * @Date: Create in 23:17 2018/10/14 2018
 * @Modify:
 *
 *
 * 连接假死的现象是：在某一端（服务端或者客户端）看来，底层的 TCP 连接已经断开了，但是应用程序并没有捕获到，因此会认为这条连接仍然是存在的，从 TCP 层面来说，只有收到四次握手数据包或者一个 RST 数据包，连接的状态才表示已断开。
 *
 * 连接假死会带来以下两大问题
 *
 * 对于服务端来说，因为每条连接都会耗费 cpu 和内存资源，大量假死的连接会逐渐耗光服务器的资源，最终导致性能逐渐下降，程序奔溃。
 * 对于客户端来说，连接假死会造成发送数据超时，影响用户体验。
 * 通常，连接假死由以下几个原因造成的
 *
 * 应用程序出现线程堵塞，无法进行数据的读写。
 * 客户端或者服务端网络相关的设备出现故障，比如网卡，机房故障。
 * 公网丢包。公网环境相对内网而言，非常容易出现丢包，网络抖动等现象，如果在一段时间内用户接入的网络连续出现丢包现象，那么对客户端来说数据一直发送不出去，而服务端也是一直收不到客户端来的数据，连接就一直耗着。
 * 如果我们的应用是面向用户的，那么公网丢包这个问题出现的概率是非常大的。对于内网来说，内网丢包，抖动也是会有一定的概率发生。一旦出现此类问题，客户端和服务端都会受到影响，接下来，我们分别从服务端和客户端的角度来解决连接假死的问题。...
 *
 *
 *
 *
 * 222
 *
 * 对于服务端来说，客户端的连接如果出现假死，那么服务端将无法收到客户端的数据，也就是说，如果能一直收到客户端发来的数据，那么可以说明这条连接还是活的，因此，服务端对于连接假死的应对策略就是空闲检测。
 *
 * 何为空闲检测？空闲检测指的是每隔一段时间，检测这段时间内是否有数据读写
 *
 *
 * 为什么要插入到最前面？是因为如果插入到最后面的话，如果这条连接读到了数据，但是在 inBound 传播的过程中出错了或者数据处理完完毕就不往后传递了（我们的应用程序属于这类），那么最终 IMIdleStateHandler 就不会读到数据，最终导致误判。...
 */
public class IMIdleStateHandler  extends IdleStateHandler {

    private static final int READER_IDLE_TIME = 15;

    public IMIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }


    /*
    连接假死之后会回调 channelIdle() 方法，我们这个方法里面打印消息，并手动关闭连接
     */

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        System.out.println(READER_IDLE_TIME + "秒内未读到数据，关闭连接");
        ctx.channel().close();
    }

}
