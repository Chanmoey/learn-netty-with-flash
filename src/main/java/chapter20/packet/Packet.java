package chapter20.packet;

import lombok.Data;

/**
 * @author Chanmoey
 * @date 2022年08月30日
 */
@Data
public abstract class Packet {

    private Byte version = 1;
    public abstract Byte getCommand();
}
