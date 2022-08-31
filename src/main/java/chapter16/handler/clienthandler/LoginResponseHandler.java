package chapter16.handler.clienthandler;

import chapter16.packet.login.LoginRequestPacket;
import chapter16.packet.login.LoginResponsePacket;
import chapter16.utils.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

/**
 * @author Chanmoey
 * @date 2022年08月31日
 */
@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("Leslie");
        loginRequestPacket.setPassword("pwd");
        log.info("客户端开始登录");
        // 写数据
        ctx.channel().writeAndFlush(loginRequestPacket);
    }

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
