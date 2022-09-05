package chapter20.handler.console;

import chapter20.packet.group.QuitGroupRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author Chanmoey
 * @date 2022年09月03日
 */
@Slf4j
public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        QuitGroupRequestPacket requestPacket = new QuitGroupRequestPacket();

        log.info("输入 groupId，退出群聊：");
        String groupId = scanner.next();
        requestPacket.setGroupId(groupId);

        channel.writeAndFlush(requestPacket);
    }
}
