package dom4j_test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * Created by oahnus on 2017/11/27
 * 21:37.
 */
public class Dom4JTest {
    public static void main(String... args) throws DocumentException {
        SAXReader reader = new SAXReader();
        reader.setEncoding("utf-8");
        Document document =reader.read(new File("src/main/resources/xml/test.aiml"));
        Element root = document.getRootElement();

        List topicElems = root.elements("topic");
        List categoryElems = root.elements("category");
        Iterator iterator = categoryElems.iterator();
        while (iterator.hasNext()) {
            Element category = (Element) iterator.next();
            Element pattern = category.element("pattern");
            Element that = category.element("that");
            Element template = category.element("template");

            System.out.println(pattern.getText());
        }
    }
}
