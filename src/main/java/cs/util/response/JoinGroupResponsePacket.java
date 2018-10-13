package cs.util.response;

import cs.entry.Packet;
import lombok.Data;

import static cs.entry.Command.JOIN_GROUP_RESPONSE;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 16:24 2018/10/13 2018
 * @Modify:
 */
@Data
public class JoinGroupResponsePacket extends Packet {
    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return JOIN_GROUP_RESPONSE;
    }
}
