package chapter20.packet.group;

import chapter20.packet.Command;
import chapter20.packet.Packet;
import chapter20.utils.Session;
import lombok.Data;

/**
 * @author Chanmoey
 * @date 2022年09月03日
 */
@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;
    private Session fromUser;
    private String message;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_RESPONSE;
    }
}
