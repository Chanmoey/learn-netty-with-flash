package chapter20.handler.console;

import chapter20.packet.group.JoinGroupRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author Chanmoey
 * @date 2022年09月02日
 */
@Slf4j
public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        JoinGroupRequestPacket requestPacket = new JoinGroupRequestPacket();

        log.info("输入 groupId，加入群聊: ");
        String groupId = scanner.next();

        requestPacket.setGroupId(groupId);
        channel.writeAndFlush(requestPacket);
    }
}
