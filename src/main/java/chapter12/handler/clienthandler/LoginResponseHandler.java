package chapter12.handler.clienthandler;

import chapter12.packet.login.LoginResponsePacket;
import chapter12.utils.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author Chanmoey
 * @date 2022年08月31日
 */
@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        if (loginResponsePacket.isSuccess()) {
            LoginUtil.markAsLogin(channelHandlerContext.channel());
            log.info("{}: 客户端登录成功", new Date());
        } else {
            log.info("{}: 客户端登录失败, 失败原因: {}", new Date(), loginResponsePacket.getReason());
        }
    }
}
