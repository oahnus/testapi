package threadpool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by oahnus on 2016/11/8.
 * 00:45
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(7777);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Socket client = null;
        while(true){
            client = server.accept();
System.out.println("A New Client Connect Into ...");
            executorService.execute(new ClientThread(client));
        }
    }
}
