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
        KnifeHandler knifeHandler = new KnifeHandler();
        saxParser.parse(file,knifeHandler);
        return knifeHandler.getKnifeList();
    }

    static VisualParameters parseVisualParameters (File file, int k) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        VisualParametersHandler visualParametersHandler = new VisualParametersHandler();
        saxParser.parse(file,visualParametersHandler);
        return visualParametersHandler.getVisualParametersList().get(k);
    }

    static class KnifeHandler extends DefaultHandler {
        private List<Knife> knifeList = null;
        private Knife currentKnife = null;
        private String currentElement;
        int count=0;


        public List<Knife> getKnifeList() {
        return knifeList;
    }

        public void startElement(String nameSpace, String localName,
                                 String qName, Attributes attributes) {
            currentElement = qName;
            if ("knife".equals(currentElement)) {
                String id = attributes.getValue("id");
                currentKnife = new Knife();
                currentKnife.setId(Integer.parseInt(id));
                if (knifeList == null)
                    knifeList = new ArrayList<>();
            }
        }

        public void characters(char[] ch, int start, int end) {
            String text = new String(ch, start, end);
            if (text.contains("<") || currentElement == null) {
                return; }

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
                case "visual_parameters" : {

                    try {
                        VisualParameters vs = parseVisualParameters(new File("src/main/resources/knifeShop.xml"), count);
                        currentKnife.setVisualParameters(vs);
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    count++;
                    break;
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

     static class VisualParametersHandler extends DefaultHandler {
         private List<VisualParameters> visualParametersList = null;
         private VisualParameters currentVisualParameters = null;
         private String currentElement;

         public List<VisualParameters> getVisualParametersList() {
             return visualParametersList;
         }

            public void startElement (String nameSpace, String localName,
                                      String qName, Attributes attributes) {
                currentElement = qName;
                if ("visual_parameters".equals(currentElement)) {
                    currentVisualParameters = new VisualParameters();
                    if (visualParametersList == null)
                        visualParametersList = new ArrayList<>();
                }
            }

            public void characters(char[] ch, int start, int end) {
                String text = new String(ch, start, end);
                if (text.contains("<") || currentElement == null) {
                    return;
                }
                switch (currentElement) {
                    case "length": {
                        currentVisualParameters.setLength(Integer.parseInt(text));
                        break;
                    }
                    case "width": {
                        currentVisualParameters.setWidth(Integer.parseInt(text));
                        break;
                    }
                    case "material": {
                        currentVisualParameters.setMaterial(text);
                        break;
                    }
                    case "handle": {
                        currentVisualParameters.setHandle(text);
                        break;
                    }
                    case "bloodstream_presence": {
                        if (text.equalsIgnoreCase("Yes")) {
                            currentVisualParameters.setBloodstreamPresence(true);
                        } else if (text.equalsIgnoreCase("No")) {
                            currentVisualParameters.setBloodstreamPresence(false);
                        }
                    }
                }
            }

            public void endElement (String nameSpace, String localName,
                                    String qName) {
                if ("visual_parameters".equals(qName)) {
                    visualParametersList.add(currentVisualParameters);
                    currentVisualParameters = null;
                }
             currentElement = null;
            }
        }
}
