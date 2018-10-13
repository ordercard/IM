package cs.util.response;

import cs.entry.Packet;
import lombok.Data;

import static cs.entry.Command.LOGOUT_RESPONSE;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 14:15 2018/10/13 2018
 * @Modify:
 */
@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return LOGOUT_RESPONSE;
    }
}
