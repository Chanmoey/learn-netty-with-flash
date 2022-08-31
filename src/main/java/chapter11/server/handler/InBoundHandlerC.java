package chapter11.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Chanmoey
 */
@Slf4j
public class InBoundHandlerC extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("InBoundHandlerC: {}", msg);
        ctx.channel().write(msg);
        super.channelRead(ctx, msg);
    }
}
