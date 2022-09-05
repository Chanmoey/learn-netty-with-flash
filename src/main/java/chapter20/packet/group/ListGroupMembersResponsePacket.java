package chapter20.packet.group;

import chapter20.packet.Command;
import chapter20.packet.Packet;
import chapter20.utils.Session;
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
