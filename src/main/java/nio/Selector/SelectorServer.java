package nio.Selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by oahnus on 2017/2/13.
 * 23:28
 */
public class SelectorServer {
    private static final int PORT = 2333;

    private static ByteBuffer buffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) throws IOException {
        // 1 打开ServerSocketChannel
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 获取ServerSocketChannel绑定的Socket
        ServerSocket ss = ssc.socket();
        // 设置监听的端口
        ss.bind(new InetSocketAddress(PORT));
        // 设置为非阻塞状态
        ssc.configureBlocking(false);

        // 打开一个选择器
        Selector selector = Selector.open();
        // 将ssc注册到selector上,并监听accept事件
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while(true){
            // 可能会发生阻塞,等待就绪的通道
            // 返回就绪通道的个数
            // 程序会阻塞在这儿不会往下走，直到客户端有Socket数据的到来为止
            // 所以严格意义上来说，NIO并不是一种非阻塞IO
            // 因为NIO会阻塞在Selector的select()方法上
            int n = selector.select();

            if (n == 0){
               continue;
            }

            // 获取已就绪通道的集合
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            // 遍历每一个Key
            while(iterator.hasNext()){
                SelectionKey sk = iterator.next();
                // 检查通道上是否有可接受的通道
                if (sk.isAcceptable()){
                    // 核心点
                    // 满足isAcceptable时，不要立刻接收数据
                    // 我们做的事情只是简单地将对应的SocketChannel注册到选择器上，
                    // 通过传入OP_READ标记，告诉选择器我们关心新的Socket通道什么时候可以准备好读数据
                    ServerSocketChannel ssChannel = (ServerSocketChannel) sk.channel();
                    SocketChannel sc = ssChannel.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                }
                // 通道上是否有数据可读
                // 满足isReadable()则表示新注册的Socket通道已经可以读取数据了，
                // 此时调用readDataFromSocket方法读取SocketChannel中的数据
                else if (sk.isReadable()){
                    // 从socket中读取数据
                    readDataFromSocket(sk);
                }
                // 将键移除，这一行很重要也是容易忘记的一步操作。
                // 不加remove会在sc.configureBlocking(false);抛出空指针异常
                iterator.remove();
            }

        }
    }

    protected static void readDataFromSocket(SelectionKey sk) throws IOException {
        SocketChannel sc = (SocketChannel) sk.channel();
        buffer.clear();

        while(sc.read(buffer) > 0){
            buffer.flip();
            while(buffer.hasRemaining()){
System.out.print((char)buffer.get());
            }
System.out.println();
            buffer.clear();
        }
    }
}
