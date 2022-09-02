package chapter18.constant;

import chapter18.utils.Session;
import io.netty.util.AttributeKey;

/**
 * @author Chanmoey
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
