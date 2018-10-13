package cs.entry;

import cs.util.Session;
import io.netty.util.AttributeKey;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 15:54 2018/10/11 2018
 * @Modify:
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
