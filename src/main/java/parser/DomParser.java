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

                Knife knife = new Knife();
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
                                VisualParameters vs = parseVisualParameters(file, i);
                                knife.setVisualParameters(vs);}
                            case "value" : {
                                knife.setValue(childElement.getTextContent()); }

                        }
                    }
                }
                knifeList.add(knife);
            }
        }
        return knifeList;
    }

    static VisualParameters parseVisualParameters(File file, int k) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        NodeList visualParametersNodeList = document.getElementsByTagName("visual_parameters");

        VisualParameters visualParameters = new VisualParameters();

            if(visualParametersNodeList.item(k).getNodeType() == Node.ELEMENT_NODE){
                Element visualParametersElement = (Element) visualParametersNodeList.item(k);
                NodeList childNodes = visualParametersElement.getChildNodes();
                for (int y = 0; y<childNodes.getLength(); y++) {
                    if (childNodes.item(y).getNodeType() == Node.ELEMENT_NODE){
                        Element childElement = (Element) childNodes.item(y);

                        switch (childElement.getNodeName()) {
                            case "length": {
                                visualParameters.setLength(Integer.parseInt(childElement.getTextContent()));}
                            case "width": {
                                visualParameters.setWidth(Integer.parseInt(childElement.getTextContent()));}
                            case "material": {
                                visualParameters.setMaterial(childElement.getTextContent());}
                            case "handle": {
                                visualParameters.setHandle(childElement.getTextContent());}
                            case "bloodstream_presence": {
                                visualParameters.setBloodstreamPresence(childElement.getTextContent().equalsIgnoreCase("Yes"));}
                        }
                    }
                }
            }

        return visualParameters;
    }
}
