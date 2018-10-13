package cs.entry;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 10:23 2018/10/11 2018
 * @Modify:
 */

@Data
public abstract class Packet {

    //版本号

    @JSONField(deserialize = false, serialize = false)
    private byte version =1;

    @JSONField(deserialize = false, serialize = false)
    public abstract Byte getCommand();
}
