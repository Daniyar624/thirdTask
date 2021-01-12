package boot;

import item.Knife;
import parser.DomParser;
import parser.SaxParser;
import parser.StAXParser;

import java.io.File;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        //Initialize SAXParser
        System.out.println("Initialize SAXParser");
        SaxParser saxParser = new SaxParser();
        List<Knife> knifeListSax = saxParser.parse(new File("src/main/resources/knifeShop.xml"));
        knifeListSax.forEach(System.out::println);

        //Initialize DomParser
        System.out.println("Initialize DomParser");
        DomParser domParser = new DomParser();
        List<Knife> knifeListDom = domParser.parse(new File("src/main/resources/knifeShop.xml"));
        knifeListDom.forEach(System.out::println);

       //Initialize StAXParser
        System.out.println("Initialize StAXParser");
        String fileName = "src/main/resources/knifeShop.xml";
        StAXParser stAXParser = new StAXParser();
        List<Knife> knifeListStax = stAXParser.parse(fileName);
        knifeListStax.sort(Comparator.comparing(Knife::getId));
        knifeListStax.forEach(System.out::println);

    }
}

