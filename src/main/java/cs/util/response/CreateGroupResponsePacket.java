package cs.util.response;

import cs.entry.Packet;
import lombok.Data;

import java.util.List;

import static cs.entry.Command.CREATE_GROUP_RESPONSE;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 14:14 2018/10/13 2018
 * @Modify:
 */
@Data
public class CreateGroupResponsePacket extends Packet {
    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {

        return CREATE_GROUP_RESPONSE;
    }
}
