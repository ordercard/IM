package cs.client.command;

import cs.util.request.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 20:38 2018/10/14 2018
 * @Modify:
 */
public class SendToGroupConsoleCommand implements  ConsoleCommandSend {
    @Override
    public void execute(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个某个群组：");
        String toGroupId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new GroupMessageRequestPacket(toGroupId, message));
    }
}
