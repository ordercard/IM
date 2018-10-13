package cs.util.response;

import cs.entry.Packet;
import cs.util.Session;
import lombok.Data;

import java.util.List;

import static cs.entry.Command.LIST_GROUP_MEMBERS_RESPONSE;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 17:03 2018/10/13 2018
 * @Modify:
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
