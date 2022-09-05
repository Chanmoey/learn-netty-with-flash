package chapter19.handler.console;

import chapter19.packet.message.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author Chanmoey
 * @date 2022年09月01日
 */
public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        String toUserId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
    }
}
