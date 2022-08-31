package chapter16.handler.serverhandler;

import chapter16.utils.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Chanmoey
 * @date 2022年08月31日
 */
@Slf4j
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!LoginUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            // 第一次校验成功之后，后续就不需要校验了，只要连接还存在，就说明用户一直在线。
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (LoginUtil.hasLogin(ctx.channel())) {
            log.info("当前连接登录校验完毕，无需再次验证，AuthHandler 被移除");
        } else {
            log.info("无登录验证，强制关闭连接");
        }
    }
}
