package cs.client.command;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 13:53 2018/10/13 2018
 * @Modify:
 */
public interface ConsoleCommandSend {
void execute(Scanner scanner, Channel channel);
}
