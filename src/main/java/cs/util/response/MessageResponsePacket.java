package cs.util.response;

import cs.entry.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static cs.entry.Command.MESSAGE_RESPONSE;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 15:53 2018/10/11 2018
 * @Modify:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponsePacket extends Packet {

    private String message;

    private String fromUserId;

    private String fromUserName;
    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }
}
