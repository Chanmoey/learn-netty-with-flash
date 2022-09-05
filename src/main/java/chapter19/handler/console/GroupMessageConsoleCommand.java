package chapter19.handler.console;

import chapter19.packet.group.GroupMessageRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Chanmoey
 * @date 2022年09月03日
 */
@Slf4j
public class GroupMessageConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        log.info("输入 groupId 和消息，以#隔开");

        String ctx = scanner.next();
        String[] message = ctx.split("#");
        if (message.length != 2) {
            log.error("消息内容格式不正确，请重新输入");
            return;
        }

        GroupMessageRequestPacket requestPacket = new GroupMessageRequestPacket();
        requestPacket.setGroupId(message[0]);
        requestPacket.setMessage(message[1]);

        channel.writeAndFlush(requestPacket);
    }
}
