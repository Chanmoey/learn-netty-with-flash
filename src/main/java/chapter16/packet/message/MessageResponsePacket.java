package chapter16.packet.message;

import chapter16.packet.Command;
import chapter16.packet.Packet;
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
