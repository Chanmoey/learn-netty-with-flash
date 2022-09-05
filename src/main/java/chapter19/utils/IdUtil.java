package chapter19.utils;

import java.util.UUID;

/**
 * @author Chanmoey
 * @date 2022年09月02日
 */
public class IdUtil {

    public static String randomId() {
        return UUID.randomUUID().toString().substring(0, 4);
    }
}
