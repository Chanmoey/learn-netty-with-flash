package chapter16.handler.serverhandler;

import chapter16.packet.login.LoginRequestPacket;
import chapter16.packet.login.LoginResponsePacket;
import chapter16.utils.LoginUtil;
import chapter16.utils.Session;
import chapter16.utils.SessionUtil;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author Chanmoey
 * @date 2022年08月31日
 */
@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket response = new LoginResponsePacket();
        if (valid(loginRequestPacket)) {
            response.setSuccess(true);
            String userId = randomUserId();
            response.setUserId(userId);
            response.setVersion(loginRequestPacket.getVersion());
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUsername()), ctx.channel());
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            response.setReason("账号或密码校验失败");
            response.setSuccess(false);
        }
        ctx.channel().writeAndFlush(response);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        log.info("验证登录信息：{}", JSON.toJSONString(loginRequestPacket));
        double luck = Math.random();
        return luck < 0.999999;
    }

    private String randomUserId() {
        return UUID.randomUUID().toString().substring(0, 4);
    }
}