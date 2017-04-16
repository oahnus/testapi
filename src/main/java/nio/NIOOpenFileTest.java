package nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by oahnus on 2017/2/11.
 * 23:28
 */
public class NIOOpenFileTest {
    static AtomicInteger n = new AtomicInteger(0);
    public static void main(String[] args){
        A a = new A();
        B b = new B();
        long start = System.currentTimeMillis();
        for(int i=0;i<=5000;i++){
            Thread thread = new Thread(a);
            thread.start();
        }
        while(n.longValue() < 5000) {

        }
        System.out.println("\t\t"+(System.currentTimeMillis() - start));
    }

    static class A implements Runnable{
        @Override
        public void run() {
            String filename = "E:\\bootstrap-3.3.0-dist\\dist\\js\\bootstrap.js";
            readFileByNIO(filename);
            n.getAndAdd(1);
System.out.println(n.longValue());
        }
    }

    static class B implements Runnable{
        @Override
        public void run() {
            String filename = "E:\\bootstrap-3.3.0-dist\\dist\\js\\bootstrap.js";
            readFileByIO(filename);
            n.getAndAdd(1);
System.out.println(n.longValue());
        }
    }

    public static void readFileByIO(String filename){
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(filename);
            byte[] bytes = new byte[1024];
            while(fis.read(bytes) != -1){
//                System.out.write(bytes);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readFileByNIO(String filename){
        FileInputStream fis = null;
        FileChannel fc = null;
        try {
            // 获取通道
            fis = new FileInputStream(filename);
            fc = fis.getChannel();
            long size = fc.size();
            // 指定缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 将通道中的数据读取到缓冲区
            fc.read(buffer);

            Buffer bf = buffer.flip();
//System.out.println("limit:"+bf.limit());

            byte[] bt = buffer.array();
//System.out.println(new String(bt));

            buffer.clear();
            buffer = null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fc.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
