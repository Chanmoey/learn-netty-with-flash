package chapter15.client;

import chapter15.handler.clienthandler.LoginResponseHandler;
import chapter15.handler.clienthandler.MessageResponseHandler;
import chapter15.packet.PacketDecoder;
import chapter15.packet.PacketEncoder;
import chapter15.packet.message.MessageRequestPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author Chanmoey
 * @date 2022年08月30日
 */
@Slf4j
public class NettyClient {

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // socket 连接上后，发送登录请求的逻辑放在哪里好呢?
                        socketChannel.pipeline().addLast(new PacketDecoder());
                        socketChannel.pipeline().addLast(new LoginResponseHandler());
                        socketChannel.pipeline().addLast(new MessageResponseHandler());
                        socketChannel.pipeline().addLast(new PacketEncoder());
                    }
                });
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        connect(bootstrap, "localhost", 1000, 1, 5);
    }

    /**
     * 带延时的重连
     */
    private static void connect(Bootstrap bootstrap, String host, int port, int retry, int maxRetry) {
        bootstrap.connect(host, port).addListener(future -> {
            log.info("尝试第{}连接", retry);
            if (future.isSuccess()) {
                log.info("连接成功");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retry == maxRetry) {
                log.error("重连次数耗尽，依旧无法连接, 关闭客户端Bootstrap");
            } else {
                int delay = 1 << (retry - 1);
                log.info("{} : 连接失败, 等待{}秒后, 尝试第{}连接", new Date(), delay, retry + 1);
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry + 1, maxRetry),
                        delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        // 更佳实践：不要显示开启线程，而是使用线程池。
        new Thread(() -> {
            while (!Thread.interrupted()) {
                log.info("输入消息发送至服务器: ");
                Scanner sc = new Scanner(System.in);
                String line = sc.nextLine();
                channel.writeAndFlush(new MessageRequestPacket(line));
            }
        }).start();

    }
}