package chapter18.packet.message;

import chapter18.packet.Command;
import chapter18.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Chanmoey
 */
@Data
@AllArgsConstructor
public class MessageRequestPacket extends Packet {

    private String toUserId;
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
