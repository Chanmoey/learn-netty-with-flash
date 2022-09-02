package chapter17.handler.clienthandler;

import chapter17.packet.group.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Chanmoey
 * @date 2022年09月02日
 */
@Slf4j
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket responsePacket) throws Exception {
        log.info("创建群聊成功，id 为[{}]", responsePacket.getGroupId());
        log.info("群聊成员有：{}",responsePacket.getUserNameList());
    }
}
