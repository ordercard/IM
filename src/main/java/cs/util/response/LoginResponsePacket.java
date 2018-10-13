package cs.util.response;

import cs.entry.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static cs.entry.Command.LOGIN_RESPONSE;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 14:50 2018/10/11 2018
 * @Modify:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;

    private String userId;

    private String userName;
    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}