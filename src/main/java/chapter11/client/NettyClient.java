package chapter11.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author Chanmoey
 */
public class NettyClient {
    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {

                            }
                        });

        bootstrap.connect("localhost", 1000).addListener(future -> {
            if (future.isSuccess()) {
                new Thread(() -> {
                    BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                    String msg;
                    try {
                        while (!Thread.interrupted()) {
                            msg = consoleReader.readLine();
                            Channel channel = ((ChannelFuture) future).channel();
                            ByteBuf byteBuf = channel.alloc().buffer();
                            byteBuf.writeBytes(msg.getBytes(StandardCharsets.UTF_8));
                            channel.writeAndFlush(byteBuf);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            consoleReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}
