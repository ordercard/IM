package cs.client.command.console;

import cs.client.command.ConsoleCommandSend;
import cs.util.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 16:19 2018/10/13 2018
 * @Modify:
 */
public class JoinGroupConsoleCommand implements ConsoleCommandSend {
    @Override
    public void execute(Scanner scanner, Channel channel) {
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();

        System.out.print("输入 groupId，加入群聊：");
        String groupId = scanner.next();

        joinGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
