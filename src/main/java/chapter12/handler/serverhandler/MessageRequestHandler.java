package chapter12.handler.serverhandler;

import chapter12.packet.message.MessageRequestPacket;
import chapter12.packet.message.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author Chanmoey
 * @date 2022年08月31日
 */
@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {
        MessageResponsePacket messageResponsePacket = receiveMessage(messageRequestPacket);
        channelHandlerContext.channel().writeAndFlush(messageResponsePacket);
    }

    private MessageResponsePacket receiveMessage(MessageRequestPacket messageRequestPacket) {
        // 消息处理
        log.info("{}: 收到客户端消息: {}", new Date(), messageRequestPacket.getMessage());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage(
                new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage()
        );

        return messageResponsePacket;
    }
}
