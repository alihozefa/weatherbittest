package utils;


import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;
import java.util.logging.Logger;

public class GenericTest {

    public Properties config = new Properties();
    public static Logger log = Logger.getLogger("devpinoyLogger");

    public GenericTest() {
        try {

            FileInputStream fis = new FileInputStream(".\\src\\main\\resources\\config.properties");
            config.load(fis);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static JsonPath convertRawToJson(Response response){
        String responseStr = response.asString();
        JsonPath json = new JsonPath(responseStr);
        return json;
    }

}
