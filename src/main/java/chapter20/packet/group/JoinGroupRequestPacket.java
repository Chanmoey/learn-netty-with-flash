package chapter20.packet.group;

import chapter20.packet.Command;
import chapter20.packet.Packet;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Chanmoey
 * @date 2022年09月02日
 */
@Getter
@Setter
public class JoinGroupRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }

    private String groupId;
}
