package nio.SocketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by oahnus on 2017/2/12.
 * 23:28
 */
public class NonBlockSocketServer {
    public static void main(String[] args){
        int port = 2333;
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            // 设为非阻塞模式
            ssc.configureBlocking(false);

            ServerSocket ss = ssc.socket();
            ss.bind(new InetSocketAddress(port));

System.out.println("开始等待客户端数据,当前时间"+System.currentTimeMillis());

            while (true){
                SocketChannel sc = ssc.accept();
                if(sc == null){
                    // 如果当前无数据传输过来，等待一分钟后再次获取
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
System.out.println("接收到客户端数据,客户端ip:"+sc.socket().getRemoteSocketAddress()
        +" 时间为"+System.currentTimeMillis());
                    ByteBuffer buffer = ByteBuffer.allocate(100);
                    sc.read(buffer);
                    buffer.flip();

                    // 判断当前位置是否在限制范围内，
                    // 告知程序在当前位置和限制之间是否有元素，是否继续往下读取数据。
                    while(buffer.hasRemaining()){
System.out.print((char)buffer.get());
                    }
System.out.println();
                    sc.close();
//                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
