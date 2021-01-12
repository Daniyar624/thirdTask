import item.Knife;
import item.VisualParameters;
import org.junit.Test;
import org.junit.Assert;
import org.xml.sax.SAXException;
import parser.DomParser;
import parser.SaxParser;
import parser.StAXParser;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KnifeTest {

    public static List<Knife> getExpectedKnifeList(){
        List<Knife> expectedKnifeList = new ArrayList<>();
        expectedKnifeList.add(new Knife(6,"Shadowmourne","ax","two-handed","Azeroth",
                new VisualParameters(55,300,"steel","wood",true),
                "collectible"));
        expectedKnifeList.add(new Knife(4,"DaggerBlast","dagger","one-handed","Kazakhstan",
                new VisualParameters(20,28,"bronze","plastic",false),
                "non-collectible"));
        expectedKnifeList.add(new Knife(71,"DickJohn","dagger","one-handed","America",
                new VisualParameters(60,75,"gold","wood",false),
                "collectible"));
        expectedKnifeList.add(new Knife(2,"Tesak","sword","one-handed","Kazakhstan",
                new VisualParameters(20,28,"steel","metal",true),
                "non-collectible"));

        return expectedKnifeList;
    }

    @Test
    public void testDomParserTest() throws IOException, SAXException, ParserConfigurationException {
        DomParser domParser = new DomParser();
        List<Knife> actualKnifeList = domParser.parse(new File("src/main/resources/knifeShop.xml"));
        Assert.assertEquals(getExpectedKnifeList(), actualKnifeList);

    }

    @Test
    public void testStaxParserTest() throws FileNotFoundException, XMLStreamException {
        StAXParser stAXParser = new StAXParser();
        List<Knife> actualKnifeList = stAXParser.parse("src/main/resources/knifeShop.xml");
        Assert.assertEquals(getExpectedKnifeList(),actualKnifeList);
    }

    @Test
    public void testSaxParserTest() throws IOException, SAXException, ParserConfigurationException {
        SaxParser saxParser = new SaxParser();
        List<Knife> actualKnifeList = saxParser.parse(new File("src/main/resources/knifeShop.xml"));
        Assert.assertEquals(getExpectedKnifeList(),actualKnifeList);
    }
}
