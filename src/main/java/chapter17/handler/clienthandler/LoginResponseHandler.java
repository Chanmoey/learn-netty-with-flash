package chapter17.handler.clienthandler;

import chapter17.packet.login.LoginResponsePacket;
import chapter17.utils.Session;
import chapter17.utils.SessionUtil;
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
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket response) throws Exception {
        if (response.isSuccess()) {
            SessionUtil.bindSession(new Session(response.getUserId(), response.getUserName()), ctx.channel());
            log.info("{}: 客户端登录成功，id为: {}", new Date(), response.getUserId());
        } else {
            log.info("{}: 客户端登录失败, 失败原因: {}", new Date(), response.getReason());
        }
    }
}
