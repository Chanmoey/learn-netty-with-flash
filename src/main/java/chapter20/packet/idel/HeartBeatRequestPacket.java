package chapter20.packet.idel;

import chapter20.packet.Command;
import chapter20.packet.Packet;

/**
 * @author Chanmoey
 * @date 2022年09月05日
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.HEART_BEAT_REQUEST;
    }
}
