package cs.client.command.console;

import cs.client.command.ConsoleCommandSend;
import cs.util.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 13:58 2018/10/13 2018
 * @Modify:
 */
public class SendToUserConsoleCommand implements ConsoleCommandSend {
    @Override
    public void execute(Scanner scanner, Channel channel) {
        System.out.print("输入对方用户名: ");
        String toUserId = scanner.next();
        System.out.print("输入消息: ");
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));

    }
}
