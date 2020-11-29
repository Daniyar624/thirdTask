package boot;

import item.Knife;
import item.VisualParameters;
import jdk.internal.org.xml.sax.SAXException;
import parser.DomParser;
import parser.SaxParser;
import parser.StAXParser;
import validation.XML;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class Main {
    public static void main(String[] args) throws Exception {

/*        //Initialize SAXParser
        System.out.println("Initialize SAXParser");
        SaxParser saxParser = new SaxParser();
        List<Knife> knifeListSax = saxParser.parse(new File("D:\\thirdTask\\src\\resources\\knifeShop.xml"));
        knifeListSax.forEach(System.out::println);

        //Initialize DomParser
        System.out.println("Initialize DomParser");
        DomParser domParser = new DomParser();
        List<Knife> knifeListDom = domParser.parse(new File("D:\\thirdTask\\src\\resources\\knifeShop.xml"));
        knifeListDom.forEach(System.out::println);
*/
      //Initialize StAXParser
        System.out.println("Initialize StAXParser");
        String fileName = "D:\\thirdTask\\src\\resources\\knifeShop.xml";
        StAXParser stAXParser = new StAXParser();
        List<Knife> knifeListStax = stAXParser.parse(fileName);
        knifeListStax.sort(Comparator.comparing(Knife::getId));
        knifeListStax.forEach(System.out::println);

        //Lets check XML for XSD
        XML xml = new XML();
        boolean b = xml.checkXMLforXSD("D:\\thirdTask\\src\\resources\\knifeShop.xml", "D:\\thirdTask\\src\\resources\\knifeShopSchemaDesign.xsd");
        System.out.println("XML соответствует XSD : " + b);

    }

}

