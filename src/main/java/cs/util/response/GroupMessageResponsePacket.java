package cs.util.response;

import cs.entry.Command;
import cs.entry.Packet;
import cs.util.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 20:43 2018/10/14 2018
 * @Modify:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;
    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_RESPONSE;
    }
}
