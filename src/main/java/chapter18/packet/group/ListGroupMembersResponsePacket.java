package chapter18.packet.group;

import chapter18.utils.Session;
import chapter18.packet.Command;
import chapter18.packet.Packet;
import lombok.Data;

import java.util.List;

/**
 * @author Chanmoey
 * @date 2022年09月03日
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;
    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
