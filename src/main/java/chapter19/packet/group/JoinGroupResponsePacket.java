package chapter19.packet.group;

import chapter19.packet.Command;
import chapter19.packet.Packet;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Chanmoey
 * @date 2022年09月02日
 */
@Getter
@Setter
public class JoinGroupResponsePacket extends Packet {

    private boolean success;
    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }
}
