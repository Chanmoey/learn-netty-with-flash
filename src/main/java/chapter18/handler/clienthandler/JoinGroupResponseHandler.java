package chapter18.handler.clienthandler;

import chapter18.packet.group.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Chanmoey
 * @date 2022年09月02日
 */
@Slf4j
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            log.info("加入群[{}]成功", responsePacket.getGroupId());
        } else {
            log.info("加入群[{}]失败", responsePacket.getGroupId());
        }
    }
}
