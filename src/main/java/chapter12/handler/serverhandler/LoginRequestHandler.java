package chapter12.handler.serverhandler;

import chapter12.packet.login.LoginRequestPacket;
import chapter12.packet.login.LoginResponsePacket;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Chanmoey
 * @date 2022年08月31日
 */
@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = login(loginRequestPacket);
        channelHandlerContext.channel().writeAndFlush(loginResponsePacket);
    }

    private LoginResponsePacket login(LoginRequestPacket loginRequestPacket) {
        LoginResponsePacket response = new LoginResponsePacket();
        response.setVersion(loginRequestPacket.getVersion());
        if (valid(loginRequestPacket)) {
            response.setSuccess(true);
        } else {
            response.setReason("账号或密码校验失败");
            response.setSuccess(false);
        }
        return response;
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        log.info("验证登录信息：{}", JSON.toJSONString(loginRequestPacket));
        double luck = Math.random();
        return luck < 0.999999;
    }
}
