package chapter20.packet.group;

import chapter20.packet.Command;
import chapter20.packet.Packet;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Chanmoey
 * @date 2022年09月01日
 */
@Getter
@Setter
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
