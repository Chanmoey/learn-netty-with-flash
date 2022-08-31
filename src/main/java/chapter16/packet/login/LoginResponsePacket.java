package chapter16.packet.login;

import chapter16.packet.Command;
import chapter16.packet.Packet;
import lombok.Data;

/**
 * @author Chanmoey
 * @date 2022年08月30日
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;
    private String userId;
    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
