package cs.server;

import cs.util.IDUtil;
import cs.util.SessionUtil;
import cs.util.request.CreateGroupRequestPacket;
import cs.util.response.CreateGroupResponsePacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 14:37 2018/10/13 2018
 * @Modify:
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        List<String> userIdList = msg.getUserIdList();
        List<String> userNameList = new ArrayList<>();
            //创建一个分组
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        for (String userID :userIdList) {
            Channel channel = SessionUtil.getChannel(userID);
            if (channel!=null){
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }
        //创建返回包
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(IDUtil.randomId());
        createGroupResponsePacket.setUserNameList(userNameList);

        // 4. 给每个客户端发送拉群通知
        channelGroup.writeAndFlush(createGroupResponsePacket);

        System.out.print("群创建成功，id 为[" + createGroupResponsePacket.getGroupId() + "], ");
        System.out.println("群里面有：" + createGroupResponsePacket.getUserNameList());

        SessionUtil.bindChannelGroup(createGroupResponsePacket.getGroupId(),channelGroup);
    }
}
