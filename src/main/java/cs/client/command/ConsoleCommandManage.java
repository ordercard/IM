package cs.client.command;

import cs.client.command.console.*;
import io.netty.channel.Channel;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 13:55 2018/10/13 2018
 * @Modify:
 */
@Data
public class ConsoleCommandManage implements ConsoleCommandSend {

    private Map<String,ConsoleCommandSend> consoleCommandSendMap;

    public ConsoleCommandManage() {
        consoleCommandSendMap =new HashMap<>();
        consoleCommandSendMap.put("sendToUser",new SendToUserConsoleCommand());
        consoleCommandSendMap.put("logout",new LogoutConsoleCommand());
        consoleCommandSendMap.put("createMap",new CreateGroupConsoleCommand());
        consoleCommandSendMap.put("login",new LoginConsoleCommand());
        consoleCommandSendMap.put("joinGroup", new JoinGroupConsoleCommand());
        consoleCommandSendMap.put("quitGroup", new QuitGroupConsoleCommand());
        consoleCommandSendMap.put("listGroupMembers", new ListGroupMembersConsoleCommand());
    }

    @Override
    public void execute(Scanner scanner, Channel channel) {
//  获取第一个指令
        System.out.println("请输入操作指令：");
        String command = scanner.next();

        ConsoleCommandSend consoleCommand = consoleCommandSendMap.get(command);

        if (consoleCommand != null) {
            consoleCommand.execute(scanner, channel);
        } else {
            System.err.println("无法识别[" + command + "]指令，请重新输入!");
        }
    }
}
