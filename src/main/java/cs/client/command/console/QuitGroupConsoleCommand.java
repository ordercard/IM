package cs.client.command.console;

import cs.client.command.ConsoleCommandSend;
import cs.util.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 17:16 2018/10/13 2018
 * @Modify:
 */
public class QuitGroupConsoleCommand implements ConsoleCommandSend {
    @Override
    public void execute(Scanner scanner, Channel channel) {
        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();

        System.out.print("输入 groupId，退出群聊：");
        String groupId = scanner.next();

        quitGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(quitGroupRequestPacket);

    }
}
