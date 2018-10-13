package cs.util.response;

import cs.entry.Packet;
import lombok.Data;

import static cs.entry.Command.QUIT_GROUP_RESPONSE;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 17:02 2018/10/13 2018
 * @Modify:
 */
@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return QUIT_GROUP_RESPONSE;
    }
}
