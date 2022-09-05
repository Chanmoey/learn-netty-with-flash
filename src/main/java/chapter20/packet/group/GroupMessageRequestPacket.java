package chapter20.packet.group;

import chapter20.packet.Command;
import chapter20.packet.Packet;
import lombok.Data;

/**
 * @author Chanmoey
 * @date 2022年09月03日
 */
@Data
public class GroupMessageRequestPacket extends Packet {

    private String groupId;
    private String message;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_REQUEST;
    }
}
