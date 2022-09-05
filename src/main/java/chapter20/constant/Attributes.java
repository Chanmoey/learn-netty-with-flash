package chapter20.constant;

import chapter20.utils.Session;
import io.netty.util.AttributeKey;

/**
 * @author Chanmoey
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
