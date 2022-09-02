package chapter17.packet.login;

import chapter17.packet.Command;
import chapter17.packet.Packet;
import lombok.Data;

/**
 * @author Chanmoey
 * @date 2022年08月30日
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;
    private String userId;
    private String userName;
    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
