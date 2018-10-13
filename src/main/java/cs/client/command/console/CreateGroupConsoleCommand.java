package cs.client.command.console;

import cs.client.command.ConsoleCommandSend;
import cs.util.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 13:59 2018/10/13 2018
 * @Modify:
 */
public class CreateGroupConsoleCommand implements ConsoleCommandSend {
    private static final String USER_ID_SPLITER = ",";
    @Override
    public void execute(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        System.out.print("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");
        String userIds = scanner.next();
        createGroupRequestPacket.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLITER)));
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
