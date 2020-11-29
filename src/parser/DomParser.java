package parser;

import item.Knife;
import item.VisualParameters;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser {

    public List<Knife> parse (File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        NodeList knifeNodeList = document.getElementsByTagName("knife");
        List<Knife> knifeList = new ArrayList<>();
        for (int i=0; i< knifeNodeList.getLength(); i++){
            if(knifeNodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element knifeElement = (Element) knifeNodeList.item(i);

                Knife knife = new Knife(new VisualParameters());
                knife.setId(Integer.parseInt(knifeElement.getAttribute("id")));

                NodeList knifeChildNodes = knifeElement.getChildNodes();
                for (int j=0; j<knifeChildNodes.getLength(); j++) {
                    if(knifeChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                        Element childElement = (Element) knifeChildNodes.item(j);

                        switch (childElement.getNodeName()){
                            case "name" : {
                                knife.setName(childElement.getTextContent()); }
                            case "type" : {
                                knife.setType(childElement.getTextContent()); }
                            case "handy" : {
                                knife.setHandy(childElement.getTextContent()); }
                            case "origin" : {
                                knife.setOrigin(childElement.getTextContent()); }
                            case "visual_parameters" :{

                                NodeList visParChildNodes = childElement.getChildNodes();
                                for (int k=0; k<visParChildNodes.getLength(); k++){
                                    if(visParChildNodes.item(k).getNodeType() == Node.ELEMENT_NODE){
                                        Element visChildElement = (Element) visParChildNodes.item(k);

                                        switch (visChildElement.getNodeName()){
                                            case "length" : {
                                                knife.getVisualParameters().setLength(Integer.parseInt(visChildElement.getTextContent())); }
                                            case "width" : {
                                                knife.getVisualParameters().setWidth(Integer.parseInt(visChildElement.getTextContent())); }
                                            case "material" : {
                                                knife.getVisualParameters().setMaterial(visChildElement.getTextContent()); }
                                            case "handle" : {
                                                knife.getVisualParameters().setHandle(visChildElement.getTextContent()); }
                                            case "bloodstream_presence" : {
                                                if (visChildElement.getTextContent().equals("Yes")){
                                                    knife.getVisualParameters().setBloodstreamPresence(true); }
                                                else {
                                                    knife.getVisualParameters().setBloodstreamPresence(false); }
                                            }
                                        }
                                    }
                                }
                            }
                            case "value" : {
                                knife.setValue(childElement.getTextContent());
                            }
                        }
                    }
                }

                knifeList.add(knife);
            }
        }
        return knifeList;
    }
}
