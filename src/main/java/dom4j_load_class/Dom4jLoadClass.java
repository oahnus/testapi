package dom4j_load_class;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by oahnus on 2017/4/15
 * 21:00.
 */
public class Dom4jLoadClass {
    public static void main(String... args) throws DocumentException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Path xmlPath = Paths.get("src/main/resources/dom4j_load_class/factory.xml");

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(xmlPath.toFile());
        Element rootElement = document.getRootElement();
        Element factoryElement = rootElement.element("factory");

        String packageName = Dom4jLoadClass.class.getPackage().getName();
        String className = packageName + "." + factoryElement.getData();
        ICarFactory factory = (ICarFactory) Class.forName(className).newInstance();

        factory.makeCar();
    }
}
