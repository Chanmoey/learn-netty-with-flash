package chapter06.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author Chanmoey
 * @date 2022年08月30日
 */
@Slf4j
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("{}: 客户端写出数据", new Date());

        // 1. 获取数据
        ByteBuf buffer = getByteBuf(ctx);

        // 2. 写数据
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buffer = (ByteBuf) msg;
        log.info("{}: 客户端读到数据 -> {}", new Date(), buffer.toString(StandardCharsets.UTF_8));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        // 1. 获取二进制抽象 ByteBuf
        ByteBuf buffer = ctx.alloc().buffer();

        // 2. 准备数据，指定字符串字符集为 UTF-8
        byte[] bytes = "Hello, World".getBytes(StandardCharsets.UTF_8);

        // 3. 填充数据到 ByteBuf
        buffer.writeBytes(bytes);

        return buffer;
    }
}
