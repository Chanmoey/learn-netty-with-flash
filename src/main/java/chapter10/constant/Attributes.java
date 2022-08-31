package chapter10.constant;

import io.netty.util.AttributeKey;

/**
 * @author Chanmoey
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
