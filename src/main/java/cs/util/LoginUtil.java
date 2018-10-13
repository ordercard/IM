package cs.util;

import cs.entry.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 15:56 2018/10/11 2018
 * @Modify:
 */
@Deprecated
public class LoginUtil {
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}