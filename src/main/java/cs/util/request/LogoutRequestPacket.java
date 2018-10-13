package cs.util.request;

import cs.entry.Packet;
import lombok.Data;

import static cs.entry.Command.LOGOUT_REQUEST;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 14:13 2018/10/13 2018
 * @Modify:
 */
@Data
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {

        return LOGOUT_REQUEST;
    }
}

