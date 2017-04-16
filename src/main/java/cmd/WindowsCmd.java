package cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by oahnus on 2017/4/13
 * 15:45.
 */
public class WindowsCmd {

    public static String executeCommand(String command) throws IOException {
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
        StringBuilder echo = new StringBuilder();
        String line;
        while((line = in.readLine()) != null) {
            echo.append(line + "\n");
        }
        process.destroy();
        return echo.toString();
    }

    public static void main(String... args) throws IOException {
        System.out.println(executeCommand("ping 127.0.0.1"));
    }
}
