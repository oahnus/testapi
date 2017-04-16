package threadpool;

import java.io.*;
import java.net.Socket;

/**
 * Created by oahnus on 2016/11/8.
 * 00:45
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1",7777);
        client.setSoTimeout(10000);

        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        client.setOOBInline(true);

        while(true){
            String str = input.readLine();
System.out.println("client ==> "+str);

            if(str.equals("q")){
                dos.writeUTF("exit");
                dos.flush();
                break;
            }

            dos.writeUTF(str);
            dos.flush();

            String resp = dis.readUTF();
System.out.println("server ==> "+resp);
        }
    }
}
