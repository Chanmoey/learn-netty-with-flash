package chapter04;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Chanmoey
 */
@Slf4j
public class NettyServer {

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {

                            }
                        }
                );
        // handler 方法指定服务器启功过程中的逻辑。
        serverBootstrap.handler(
                new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel nioServerSocketChannel) throws Exception {
                        log.info("服务器启动中");
                    }
                }
        );

        // attr 方法给NioServerSocketChannel指定一些自定义属性
        serverBootstrap.attr(AttributeKey.newInstance("serverName"), "nettyServer");

        // attr 给每一个连接附上属性
        serverBootstrap.childAttr(AttributeKey.newInstance("clientKey"), "clientValue");

        // 给服务端 channel 设置 TCP 参数
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        // 给每个连接设置 TCP 参数, 更多内容去看书
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
//        serverBootstrap.bind(8000).addListener(future -> {
//            if (future.isSuccess()) {
//                log.info("端口绑定成功");
//            } else {
//                log.info("端口绑定失败");
//            }
//        });
        bind(serverBootstrap, 1000);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("端口[{}]绑定成功", port);
            } else {
                log.error("端口[{}]绑定失败", port);
                log.info("尝试绑定新端口[{}]", port + 1);
                bind(serverBootstrap, port + 1);
            }
        });
    }

}
