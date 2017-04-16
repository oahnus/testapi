package nio.SocketChannel;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by oahnus on 2017/2/12.
 * 23:28
 */
public class NonBlockSocketClient {
    public static final String STR = "Hello Server";
    public static final String REMOTE_IP = "127.0.0.1";

    public static void main(String[] args){
        int port = 2333;
        try {
            SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress(REMOTE_IP,port));
            while (!sc.finishConnect()){
                System.out.println("同" + REMOTE_IP+ "的连接正在建立，请稍等！");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("连接已建立，待写入内容至指定ip+端口！时间为" + System.currentTimeMillis());
            ByteBuffer buffer = ByteBuffer.allocate(100);
            buffer.put(STR.getBytes());
            buffer.flip();

            sc.write(buffer);
            buffer.clear();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
