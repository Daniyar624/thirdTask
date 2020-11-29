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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StAXParser {
    public List<Knife> parse(String fileName) throws FileNotFoundException, XMLStreamException, NullPointerException {
        List<Knife> knifeList = new ArrayList<>();
        Knife knife = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
        while (reader.hasNext()) {
            XMLEvent xmlEvent = reader.nextEvent();
            if (xmlEvent.isStartElement()) {
                StartElement startElement = xmlEvent.asStartElement();
                switch (startElement.getName().getLocalPart()){
                    case "knife" : {
                        knife = new Knife(new VisualParameters());
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        if (idAttr != null) {
                            knife.setId(Integer.parseInt(idAttr.getValue()));
                        }
                    }
                    case "name" : {
                        xmlEvent = reader.nextEvent();
                        assert knife != null;
                        knife.setName(xmlEvent.asCharacters().getData());
                        break; }
                    case "type" : {
                        xmlEvent = reader.nextEvent();
                        assert knife != null;
                        knife.setType(xmlEvent.asCharacters().getData());
                        break; }
                    case "handy" : {
                        xmlEvent = reader.nextEvent();
                        assert knife != null;
                        knife.setHandy(xmlEvent.asCharacters().getData());
                        break; }
                    case "origin" : {
                        xmlEvent = reader.nextEvent();
                        assert knife != null;
                        knife.setOrigin(xmlEvent.asCharacters().getData());
                        break; }
                    case "length" : {
                        xmlEvent = reader.nextEvent();
                        assert knife != null;
                        knife.getVisualParameters().setLength(Integer.parseInt(xmlEvent.asCharacters().getData()));
                        break; }
                    case "width" : {
                        xmlEvent = reader.nextEvent();
                        assert knife != null;
                        knife.getVisualParameters().setWidth(Integer.parseInt(xmlEvent.asCharacters().getData()));
                        break; }
                    case "material" : {
                        xmlEvent = reader.nextEvent();
                        assert knife != null;
                        knife.getVisualParameters().setMaterial(xmlEvent.asCharacters().getData());
                        break; }
                    case "handle" : {
                        xmlEvent = reader.nextEvent();
                        assert knife != null;
                        knife.getVisualParameters().setHandle(xmlEvent.asCharacters().getData());
                        break; }
                    case "bloodstream_presence" : {
                        xmlEvent = reader.nextEvent();
                        String text = xmlEvent.asCharacters().getData();
                        assert knife != null;
                        if (text.equalsIgnoreCase("Yes")){
                            knife.getVisualParameters().setBloodstreamPresence(true);
                        }
                        else if (text.equalsIgnoreCase("No")) {
                            knife.getVisualParameters().setBloodstreamPresence(false);
                        }
                        break; }
                    case "value" : {
                        xmlEvent = reader.nextEvent();
                        assert knife != null;
                        knife.setValue(xmlEvent.asCharacters().getData());
                        break; }
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
}
