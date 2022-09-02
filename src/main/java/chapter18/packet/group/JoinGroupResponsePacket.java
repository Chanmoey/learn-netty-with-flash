package chapter18.packet.group;

import chapter18.packet.Command;
import chapter18.packet.Packet;
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
