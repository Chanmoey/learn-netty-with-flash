package chapter02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * sd
 *
 * @author Chanmoey
 * @date 2022年09月05日
 */
public class NIOClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(true);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));

        while (true) {
            Thread.sleep(2000);
            socketChannel.write(ByteBuffer.wrap("hello".getBytes()));
        }
    }
}
