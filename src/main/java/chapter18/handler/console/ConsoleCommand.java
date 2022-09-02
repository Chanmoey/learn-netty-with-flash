package chapter18.handler.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author Chanmoey
 * @date 2022年09月01日
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
