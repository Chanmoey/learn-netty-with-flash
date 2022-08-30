package chapter09.packet.login;

import chapter09.packet.Packet;
import chapter09.packet.Command;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Chanmoey
 * @date 2022年08月30日
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
