package parser;

import item.Knife;
import item.VisualParameters;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxParser {
    public List<Knife> parse (File file) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        Myhandler myhandler = new Myhandler();
        saxParser.parse(file,myhandler);
        return myhandler.knifeList;
    }

    static class Myhandler extends DefaultHandler {
    private List<Knife> knifeList = null;
    private Knife currentKnife = null;
    private String currentElement;

    public List<Knife> getKnifeList() {
        return knifeList;
    }

    public void startElement(String nameSpace, String localName,
                             String qName, Attributes attributes)
            throws SAXException {
        currentElement = qName;
        switch (currentElement) {
            case "knife": {
                String id = attributes.getValue("id");
                currentKnife = new Knife(new VisualParameters());
                currentKnife.setId(Integer.parseInt(id));

                if (knifeList == null)
                    knifeList = new ArrayList<>();
            }
            break;
        }
    }

    public void characters(char[] ch, int start, int end) {
        String text = new String(ch, start, end);
        if (text.contains("<") || currentElement == null) {
            return;
        }

        switch (currentElement) {
            case "name": {
                currentKnife.setName(text);
            }
            break;
            case "type": {
                currentKnife.setType(text);
            }
            break;
            case "handy": {
                currentKnife.setHandy(text);
            }
            break;
            case "origin": {
                currentKnife.setOrigin(text);
            }
            break;
            case "length": {
                currentKnife.getVisualParameters().setLength(Integer.parseInt(text));
                break;
            }
            case "width": {
                currentKnife.getVisualParameters().setWidth(Integer.parseInt(text));
                break;
            }
            case "material": {
                currentKnife.getVisualParameters().setMaterial(text);
                break;
            }
            case "handle": {
                currentKnife.getVisualParameters().setHandle(text);
                break;
            }
            case "bloodstream_presence": {
                if (text.equalsIgnoreCase("Yes")) {
                    currentKnife.getVisualParameters().setBloodstreamPresence(true);
                } else if (text.equalsIgnoreCase("No")) {
                    currentKnife.getVisualParameters().setBloodstreamPresence(false);
                }
            }
            case "value": {
                currentKnife.setValue(text);
            }
        }
    }

    public void endElement(String nameSpace, String localName,
                           String qName) {
        switch (qName) {
            case "knife": {
                knifeList.add(currentKnife);
                currentKnife = null;
            }
            break;
            default: {

            }
        }
        currentElement = null;
    }
}

}
