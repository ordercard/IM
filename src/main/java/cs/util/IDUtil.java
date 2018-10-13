package cs.util;

import java.util.UUID;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 14:04 2018/10/13 2018
 * @Modify:
 */
public class IDUtil {
    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
