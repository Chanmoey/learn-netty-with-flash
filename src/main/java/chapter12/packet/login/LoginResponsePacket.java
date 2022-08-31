package chapter12.packet.login;

import chapter12.packet.Command;
import chapter12.packet.Packet;
import lombok.Data;

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
