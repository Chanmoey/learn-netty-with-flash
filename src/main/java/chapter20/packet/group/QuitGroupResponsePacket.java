package chapter20.packet.group;

import chapter20.packet.Command;
import chapter20.packet.Packet;
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
