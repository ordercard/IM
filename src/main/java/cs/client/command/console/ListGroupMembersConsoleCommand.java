package cs.client.command.console;

import cs.client.command.ConsoleCommandSend;
import cs.util.request.ListGroupMembersRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 17:17 2018/10/13 2018
 * @Modify:
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommandSend {
    @Override
    public void execute(Scanner scanner, Channel channel) {
        ListGroupMembersRequestPacket listGroupMembersRequestPacket =  new ListGroupMembersRequestPacket();
        System.out.print("输入 groupId，获取群成员列表：");
        String groupId = scanner.next();

        listGroupMembersRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(listGroupMembersRequestPacket);
    }
}
