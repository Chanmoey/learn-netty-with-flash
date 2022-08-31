package chapter16.server;

import chapter16.handler.serverhandler.AuthHandler;
import chapter16.handler.serverhandler.LoginRequestHandler;
import chapter16.handler.serverhandler.MessageRequestHandler;
import chapter16.packet.PacketDecoder;
import chapter16.packet.PacketEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Chanmoey
 * @date 2022年08月30日
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
                                nioSocketChannel.pipeline().addLast(new PacketDecoder());
                                nioSocketChannel.pipeline().addLast(new LoginRequestHandler());
                                nioSocketChannel.pipeline().addLast(new AuthHandler());
                                nioSocketChannel.pipeline().addLast(new MessageRequestHandler());
                                nioSocketChannel.pipeline().addLast(new PacketEncoder());
                            }
                        }
                );
        serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
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
