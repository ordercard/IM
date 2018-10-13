package cs.util.request;

import cs.entry.Packet;
import lombok.Data;

import java.util.List;

import static cs.entry.Command.CREATE_GROUP_REQUEST;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 14:06 2018/10/13 2018
 * @Modify:
 */
@Data
public class CreateGroupRequestPacket extends Packet {
    private List<String> userIdList;

    @Override
    public Byte getCommand() {

        return CREATE_GROUP_REQUEST;
    }
}
