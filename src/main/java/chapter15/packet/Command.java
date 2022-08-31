package chapter15.packet;

/**
 * @author Chanmoey
 * @date 2022年08月30日
 */

public interface Command {
    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;
    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;
}
