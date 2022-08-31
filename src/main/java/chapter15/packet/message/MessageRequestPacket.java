package chapter15.packet.message;

import chapter15.packet.Command;
import chapter15.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Chanmoey
 */
@Data
@AllArgsConstructor
public class MessageRequestPacket extends Packet {



    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
