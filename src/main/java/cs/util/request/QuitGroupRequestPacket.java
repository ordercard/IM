package cs.util.request;

import cs.entry.Packet;
import lombok.Data;

import static cs.entry.Command.QUIT_GROUP_REQUEST;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 17:02 2018/10/13 2018
 * @Modify:
 */
@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return QUIT_GROUP_REQUEST;
    }
}