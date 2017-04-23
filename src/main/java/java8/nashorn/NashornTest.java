package java8.nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by oahnus on 2017/4/23
 * 13:07.
 */
public class NashornTest {
    public static void main(String... args) throws ScriptException, IOException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        Object result = engine.eval("'hello world'.length");
        System.out.println(result);

        result = engine.eval(Files.newBufferedReader(Paths.get("src/main/resources/java8/nashorn-test.js")));
        System.out.println(result);
    }
}
