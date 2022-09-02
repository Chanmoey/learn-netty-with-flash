package chapter18.packet.message;

import chapter18.packet.Command;
import chapter18.packet.Packet;
import lombok.Data;

/**
 * @author Chanmoey
 */
@Data
public class MessageResponsePacket extends Packet {

    private String message;
    private String fromUserId;
    private String fromUserName;
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
