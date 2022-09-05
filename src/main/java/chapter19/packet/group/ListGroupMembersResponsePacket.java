package chapter19.packet.group;

import chapter19.packet.Command;
import chapter19.packet.Packet;
import chapter19.utils.Session;
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
