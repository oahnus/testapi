package md2html;

import org.apache.commons.io.FileUtils;
import org.pegdown.PegDownProcessor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by oahnus on 2017/7/10
 * 17:27.
 */
public class Md2Html {

    public static void main(String... args) throws IOException {
        String path = "C:\\D\\Document\\input.md";
        String output = "C:\\D\\Document\\output.html";
        File outputFile = Paths.get(output).toFile();
        String fileName = Paths.get(path).getFileName().toString();
        FileUtils.writeStringToFile(outputFile, addTemplate(fileName,getHtml(path)), "utf-8");
    }

    // 纯html 无body 无head
    public static String getHtml(String filePath) throws IOException {
        File mdFile = Paths.get(filePath).toFile();
        String mdSource = FileUtils.readFileToString(mdFile, "utf-8");
        PegDownProcessor processor = new PegDownProcessor(Integer.MAX_VALUE);
        return processor.markdownToHtml(mdSource);
    }

    public static String addTemplate(String title, String html) {
        String templateHead = "<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <title>" + title + "</title>\n" +
                "    <meta name=\"author\" content=\"BieYuu\" />\n" +
                "    <meta name=\"baidu-site-verification\" content=\"poz3x3Hymg\" />\n" +
                "    <meta name=\"renderer\" content=\"webkit\">\n" +
                "    <meta name=\"description\" content=\"BeiYuu's Blog\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0\">\n" +
                "    <link rel=\"stylesheet\" href=\"github-markdown-css.css\">" +
                "  </head>" +
                "  <body class=\"markdown-body\">";
        String templateTail = "</html>";
        return templateHead + html + templateTail;
    }
}
