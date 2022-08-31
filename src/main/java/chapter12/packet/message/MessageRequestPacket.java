package chapter12.packet.message;

import chapter12.packet.Command;
import chapter12.packet.Packet;
import lombok.Data;

/**
 * @author Chanmoey
 */
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
