package chapter10.serializer;

/**
 * @author Chanmoey
 * @date 2022年08月30日
 */
public interface Serializer {

    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * 将 Java 对象转换成二进制数据
     */
    byte[] serializer(Object object);

    /**
     * 二进制数据转换成 Java 对象
     */
    <T> T deserializer(Class<T> clazz, byte[] bytes);
}
