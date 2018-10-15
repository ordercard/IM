package cs.util.hander;

import cs.entry.Command;
import cs.entry.Packet;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 23:29 2018/10/14 2018
 * @Modify:
 */
public class KeepHeartPacket  extends Packet {
    @Override
    public Byte getCommand() {
        return Command.HEART_KEEP;
    }
}
