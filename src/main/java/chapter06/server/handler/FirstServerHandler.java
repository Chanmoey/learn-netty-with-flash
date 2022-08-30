package chapter06.server.handler;

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
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("{}: 服务端读到数据 -> {}", new Date(), byteBuf.toString(StandardCharsets.UTF_8));
        log.info("服务端回送数据");
        ByteBuf buffer = getByteBuf(ctx);
        ctx.channel().writeAndFlush(buffer);
    }

    /**
     * 思考：客户端连接成功时，服务端给客户端推送消息
     * 只看到第六章，两次 write 被整合成一次发送了，看看后面有没有方法解决。
     *
     * @param ctx ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("连接成功，服务端给客户端推送消息");
        byte[] bytes = "连接成功，服务端给客户端推送消息".getBytes(StandardCharsets.UTF_8);
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(bytes);
        ctx.channel().writeAndFlush(buffer);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "好好学习，天天向上。".getBytes(StandardCharsets.UTF_8);
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(bytes);
        return buffer;
    }
}
