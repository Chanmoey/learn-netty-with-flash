package chapter15.packet;

import chapter15.packet.login.LoginRequestPacket;
import chapter15.packet.login.LoginResponsePacket;
import chapter15.packet.message.MessageRequestPacket;
import chapter15.packet.message.MessageResponsePacket;
import chapter15.serializer.JSONSerializer;
import chapter15.serializer.Serializer;
import chapter15.serializer.SerializerAlgorithm;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author Chanmoey
 * @date 2022年08月30日
 */
public class PacketCodec {

    public static final int MAGIC_NUMBER = 0x12345678;

    public static final PacketCodec INSTANCE = new PacketCodec();

    private PacketCodec() {
    }

    public void encode(ByteBuf byteBuf, Packet packet) {
        byte[] bytes = Serializer.DEFAULT.serializer(packet);

        // 写入魔数
        byteBuf.writeInt(MAGIC_NUMBER);

        // 写入协议版本号
        byteBuf.writeByte(packet.getVersion());

        // 写入序列化算法标识
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());

        // 写入指令
        byteBuf.writeByte(packet.getCommand());

        // 写入数据长度
        byteBuf.writeInt(bytes.length);

        // 写入具体数据
        byteBuf.writeBytes(bytes);
    }

    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
        // 获取 ByteBuf 和 对象字节数组
        ByteBuf byteBuf = byteBufAllocator.buffer();
        encode(byteBuf, packet);
        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过魔数
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 获取序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        // 获取指令
        byte command = byteBuf.readByte();

        // 数据长度
        int length = byteBuf.readInt();

        // 数据
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);

        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserializer(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        if (SerializerAlgorithm.JSON == serializeAlgorithm) {
            return new JSONSerializer();
        }

        return null;
    }

    private Class<? extends Packet> getRequestType(byte command) {
        if (Command.LOGIN_REQUEST.equals(command)) {
            return LoginRequestPacket.class;
        }
        if (Command.LOGIN_RESPONSE.equals(command)) {
            return LoginResponsePacket.class;
        }
        if (Command.MESSAGE_REQUEST.equals(command)) {
            return MessageRequestPacket.class;
        }
        if (Command.MESSAGE_RESPONSE.equals(command)) {
            return MessageResponsePacket.class;
        }

        return null;
    }
}
