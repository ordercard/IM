package cs.util.request;

import cs.entry.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static cs.entry.Command.MESSAGE_REQUEST;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 15:52 2018/10/11 2018
 * @Modify:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestPacket extends Packet {

    private String toUserId;

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}