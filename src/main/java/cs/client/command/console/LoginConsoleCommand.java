package cs.client.command.console;

import cs.client.command.ConsoleCommandSend;
import cs.util.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

import static cs.client.Client.waitForLoginResponse;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 14:22 2018/10/13 2018
 * @Modify:
 */
public class LoginConsoleCommand implements ConsoleCommandSend {
    @Override
    public void execute(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        System.out.print("输入用户名登录: ");
        String username = scanner.nextLine();
        loginRequestPacket.setUserName(username);
        // 密码使用默认的
        loginRequestPacket.setPassword("pwd");
        // 发送登录数据包
        channel.writeAndFlush(loginRequestPacket);
        waitForLoginResponse();
    }
}
