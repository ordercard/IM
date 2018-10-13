package cs.util.request;

import cs.entry.Packet;
import lombok.Data;

import static cs.entry.Command.JOIN_GROUP_REQUEST;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 16:22 2018/10/13 2018
 * @Modify:
 */
@Data
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return JOIN_GROUP_REQUEST;
    }
}