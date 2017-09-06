package pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by oahnus on 2017/9/4
 * 17:29.
 */
public class PDFCreator {
    public static void main(String... args) throws IOException, DocumentException, com.lowagie.text.DocumentException, TemplateException {

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY年MM月dd日");

        // 向模版传入数据
        Map<String, String> map = new HashMap<>();
        map.put("name", "郭麒麟");
        map.put("date", sdf.format(new Date()));
        // pdf文件路径
        File path = new File("src/main/resources/pdf");
        path.mkdirs();
        File destFile = new File("src/main/resources/pdf", "Test.pdf");
        destFile.createNewFile();

        ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver().addFont("src/main/resources/pdf/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        String html = "";

        // 获取html模版
        Configuration config = new Configuration();
        config.setDirectoryForTemplateLoading(new File("src/main/resources/pdf/"));
        config.setEncoding(Locale.CHINA, "utf-8");
        Template template = config.getTemplate("template.html");
        template.setEncoding("utf-8");
        StringWriter writer = new StringWriter();
        template.process(map, writer);
        writer.flush();
        html = writer.toString();

        // 写入
        OutputStream os = new FileOutputStream(destFile);
        renderer.setDocumentFromString(html);

        renderer.getSharedContext().setBaseURL("file:src/main/resources/pdf/");

        renderer.layout();
        renderer.createPDF(os);
        renderer.finishPDF();
        os.close();
    }
}
