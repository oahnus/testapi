package threadpool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by oahnus on 2016/11/8.
 */
public class ClientThread implements Runnable {
    private Socket client = null;
    private DataInputStream dis;
    private DataOutputStream dos;

    public ClientThread(Socket client) throws IOException {
        this.client = client;
        this.dos = new DataOutputStream(client.getOutputStream());
        this.dis = new DataInputStream(client.getInputStream());
    }

    @Override
    public void run() {
        try{
            while (true){
//                client.sendUrgentData(0xff);
                String str = dis.readUTF();
                if(str.equals("exit")){
                    break;
                }
System.out.println("client ==> "+str);
                String output = Thread.currentThread().getName();
System.out.println("server ==> "+output);
                dos.writeUTF(output);
                dos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            client = null;
        }
    }
}
