package chapter18.packet.group;

import chapter18.packet.Command;
import chapter18.packet.Packet;
import lombok.Data;

/**
 * @author Chanmoey
 * @date 2022年09月03日
 */
@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;
    private boolean success;

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_RESPONSE;
    }
}
