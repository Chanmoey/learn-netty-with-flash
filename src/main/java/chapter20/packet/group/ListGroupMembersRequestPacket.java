package chapter20.packet.group;

import chapter20.packet.Command;
import chapter20.packet.Packet;
import lombok.Data;

/**
 * @author Chanmoey
 * @date 2022年09月03日
 */
@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }
}
