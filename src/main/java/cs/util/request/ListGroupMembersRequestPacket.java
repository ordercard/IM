package cs.util.request;

import cs.entry.Packet;
import lombok.Data;

import static cs.entry.Command.LIST_GROUP_MEMBERS_REQUEST;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 17:04 2018/10/13 2018
 * @Modify:
 */
@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_REQUEST;
    }
}
