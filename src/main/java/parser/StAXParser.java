package parser;

import item.Knife;
import item.VisualParameters;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StAXParser {
    public List<Knife> parse(String file) throws FileNotFoundException, XMLStreamException, NullPointerException {
        List<Knife> knifeList = new ArrayList<>();
        Knife knife = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(file));
        int count=0;
        while (reader.hasNext()) {
            XMLEvent xmlEvent = reader.nextEvent();
            if (xmlEvent.isStartElement()) {
                StartElement startElement = xmlEvent.asStartElement();
                switch (startElement.getName().getLocalPart()) {
                    case "knife": {
                        knife = new Knife();
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        if (idAttr != null) {
                            knife.setId(Integer.parseInt(idAttr.getValue()));
                        }
                    }
                    case "name": {
                        xmlEvent = reader.nextEvent();
                        assert knife != null;
                        knife.setName(xmlEvent.asCharacters().getData());
                        break;
                    }
                    case "type": {
                        xmlEvent = reader.nextEvent();
                        assert knife != null;
                        knife.setType(xmlEvent.asCharacters().getData());
                        break;
                    }
                    case "handy": {
                        xmlEvent = reader.nextEvent();
                        assert knife != null;
                        knife.setHandy(xmlEvent.asCharacters().getData());
                        break;
                    }
                    case "origin": {
                        xmlEvent = reader.nextEvent();
                        assert knife != null;
                        knife.setOrigin(xmlEvent.asCharacters().getData());
                        break;
                    }
                    case "visual_parameters": {
                        VisualParameters vs = parseVisualParameters(new File("src/main/resources/knifeShop.xml"), count);
                        assert knife != null;
                        knife.setVisualParameters(vs);
                        count++;
                    }

                    case "value": {
                        xmlEvent = reader.nextEvent();
                        assert knife != null;
                        knife.setValue(xmlEvent.asCharacters().getData());
                        break;
                    }
                }
            }
            if (xmlEvent.isEndElement()) {
                EndElement endElement = xmlEvent.asEndElement();
                if (endElement.getName().getLocalPart().equals("knife")) {
                    knifeList.add(knife);
                }
            }
        }
        return knifeList;
    }

    static VisualParameters parseVisualParameters(File file, int k) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(file));
        List<VisualParameters> visualParametersList = new ArrayList<>();
        VisualParameters visualParameters=null;
        while (reader.hasNext()) {
            XMLEvent xmlEvent = reader.nextEvent();
            if (xmlEvent.isStartElement()) {
                StartElement startElement = xmlEvent.asStartElement();

                switch (startElement.getName().getLocalPart()) {
                    case "visual_parameters": {
                        visualParameters = new VisualParameters();

                    }
                    case "length": {
                        xmlEvent = reader.nextEvent();
                        assert visualParameters != null;
                        try {
                            visualParameters.setLength(Integer.parseInt(xmlEvent.asCharacters().getData()));
                        }
                        catch (NumberFormatException ignored){
                            //похуй
                        }
                        break;}
                    case "width": {
                        xmlEvent = reader.nextEvent();
                        assert visualParameters != null;
                        try {
                            visualParameters.setWidth(Integer.parseInt((xmlEvent.asCharacters().getData())));
                        }
                        catch (NumberFormatException ignored){
                            //похуй
                        }
                        break;}
                    case "material": {
                        xmlEvent = reader.nextEvent();
                        assert visualParameters != null;
                        visualParameters.setMaterial(xmlEvent.asCharacters().getData());
                        break;}
                    case "handle": {
                        xmlEvent = reader.nextEvent();
                        assert visualParameters != null;
                        visualParameters.setHandle(xmlEvent.asCharacters().getData());
                        break;}
                    case "bloodstream_presence": {
                        xmlEvent = reader.nextEvent();
                        String text = xmlEvent.asCharacters().getData();
                        assert visualParameters != null;
                        if (text.equalsIgnoreCase("Yes")) {
                            visualParameters.setBloodstreamPresence(true);
                        } else if (text.equalsIgnoreCase("No")) {
                            visualParameters.setBloodstreamPresence(false);
                        }
                        break; }
                }
            }
        if (xmlEvent.isEndElement()) {
            EndElement endElement = xmlEvent.asEndElement();
            if (endElement.getName().getLocalPart().equals("visual_parameters")) {
                visualParametersList.add(visualParameters);
            }
        }
        } //конец цикла
        return visualParametersList.get(k);
    }
}
