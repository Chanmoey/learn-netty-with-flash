package chapter19.constant;

import chapter19.utils.Session;
import io.netty.util.AttributeKey;

/**
 * @author Chanmoey
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
