package chapter05;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Chanmoey
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

                    }
                });
        /*
        bootstrap.connect("localhost", 1000).addListener(future -> {
            if (future.isSuccess()) {
                log.info("连接成功");
            } else {
                log.info("连接失败");
            }
        });
        connect(bootstrap, "localhost", 1000);
        Bootstrp还有其他属性可以设置，与ServerBootstrp大同小异，不在赘述
         */
        connect(bootstrap, "localhost", 1000, 1, 5);
    }

    /**
     * 失败重连
     *
     * @param bootstrap client
     * @param host      host
     * @param port      port
     */
    private static void connect(Bootstrap bootstrap, String host, int port) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("连接成功");
            } else {
                log.info("连接失败, 尝试重连");
                connect(bootstrap, host, port);
            }
        });
    }

    /**
     * 带延时的重连
     */
    private static void connect(Bootstrap bootstrap, String host, int port, int retry, int maxRetry) {
        bootstrap.connect(host, port).addListener(future -> {
            log.info("尝试第{}连接", retry);
            if (future.isSuccess()) {
                log.info("连接成功");
            } else if (retry == maxRetry) {
                log.error("重连次数耗尽，依旧无法连接, 关闭客户端Bootstrap");
            } else {
                int delay = 1 << (retry - 1);
                log.info("{} : 连接失败, 等待{}秒后, 尝试第{}连接", new Date(), delay, retry + 1);
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry + 1, maxRetry), delay, TimeUnit.SECONDS);
            }
        });
    }
}
