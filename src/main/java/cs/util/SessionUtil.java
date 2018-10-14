package cs.util;

import cs.entry.Attributes;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 19:27 2018/10/12 2018
 * @Modify:
 */
public class SessionUtil {
    // userId -> channel 的映射
    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    //群组id和 channelGroup的映射
    private static final Map<String, ChannelGroup> groupIdChannelGroupMap = new ConcurrentHashMap<>();


        //登录绑定
    public static void bindSession(Session session, Channel channel) {

        userIdChannelMap.put(session.getUserId(), channel);

        channel.attr(Attributes.SESSION).set(session);
    }
        //退出解绑
    public static void unBindSession(Channel channel) {

        if (hasLogin(channel)) {
            userIdChannelMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {

        return channel.hasAttr(Attributes.SESSION);
    }

        //获取这个channel对应的session

    public static Session getSession(Channel channel) {

        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {

        return userIdChannelMap.get(userId);

    }


    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {
        groupIdChannelGroupMap.put(groupId, channelGroup);
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return groupIdChannelGroupMap.get(groupId);
    }

    public static void remove(String groupId) {
        groupIdChannelGroupMap.remove(groupId);
    }
}