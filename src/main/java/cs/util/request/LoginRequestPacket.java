package cs.util.request;

import cs.entry.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static cs.entry.Command.LOGIN_REQUEST;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 10:57 2018/10/11 2018
 * @Modify:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestPacket extends Packet {
    private String userId;

    private String userName;

    private String password;
    private boolean success;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }

}
