package chapter18.utils;

import chapter18.constant.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

import java.util.concurrent.atomic.AtomicInteger;

public class LoginUtil {

    public static final AtomicInteger ID_CREATE = new AtomicInteger();


    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;
    }
}
