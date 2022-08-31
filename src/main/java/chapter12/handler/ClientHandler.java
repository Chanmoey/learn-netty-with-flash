package chapter12.handler;

import chapter12.packet.Packet;
import chapter12.packet.PacketCodec;
import chapter12.packet.login.LoginRequestPacket;
import chapter12.packet.login.LoginResponsePacket;
import chapter12.packet.message.MessageResponsePacket;
import chapter12.utils.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

/**
 * @author Chanmoey
 * @date 2022年08月30日
 */
@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("Leslie");
        loginRequestPacket.setPassword("pwd");

        // 编码
        ByteBuf buffer = PacketCodec.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

        log.info("客户端开始登录");
        // 写数据
        ctx.channel().writeAndFlush(buffer);
    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf byteBuf = (ByteBuf) msg;
//
//        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);
//
//        if (packet instanceof LoginResponsePacket loginResponsePacket) {
//            if (loginResponsePacket.isSuccess()) {
//                LoginUtil.markAsLogin(ctx.channel());
//                log.info("{}: 客户端登录成功", new Date());
//            } else {
//                log.info("{}: 客户端登录失败, 失败原因: {}", new Date(), loginResponsePacket.getReason());
//            }
//        } else if (packet instanceof MessageResponsePacket messageResponsePacket) {
//            log.info("{}: 收到服务端的消息: {}", new Date(), messageResponsePacket.getMessage());
//        }
//    }
}
