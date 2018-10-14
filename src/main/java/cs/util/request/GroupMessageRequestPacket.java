package cs.util.request;

import cs.entry.Command;
import cs.entry.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 20:39 2018/10/14 2018
 * @Modify:
 */
@Data
@NoArgsConstructor
public class GroupMessageRequestPacket  extends Packet {


    private String toGroupId;
    private String message;

    public GroupMessageRequestPacket(String toGroupId, String message) {
        this.toGroupId = toGroupId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_REQUEST;
    }
}
