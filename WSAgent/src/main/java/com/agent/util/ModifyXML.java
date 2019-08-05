package com.agent.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
//Source: https://www.journaldev.com/901/modify-xml-file-in-java-dom-parser

@Configuration
public class ModifyXML {

	private String WSDL;
	
	public String generateWSDL(String location) {
		
        String filePath = "./Class/calculator.wsdl";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            //Configure Biding
            updateBiding(doc,"http://127.0.0.1/operations");
            
            //write the updated document to file and return
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            
            //create and configurate channel to export the new WSDL
            StreamResult result = new StreamResult(new File("./Class/WSDl_updated.wsdl"));
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(source, result);
            
            StringWriter sw = new StringWriter();
            result = new StreamResult(sw);
            transformer.transform(source, result);
            
            this.WSDL = sw.toString();
            //System.out.println("XML file updated successfully");
            
            
        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
            e1.printStackTrace();
        }
        return this.WSDL;
    }

    
    private static void updateBiding(Document doc, String location) {
    	//Identify labels <soap:address.../>
    	NodeList bidings = doc.getElementsByTagName("soap:address");
    	Element bidingSOAP = null;
    	
    	//loop to select the label <soap:address.../>
    	for(int i=0; i<bidings.getLength();i++){
    		bidingSOAP = (Element) bidings.item(i);
    		System.out.println("Address WS: " + bidingSOAP.getAttribute("location"));
    		
    		//Set attribute WS location on label <soap:address.../>
    		bidingSOAP.setAttribute("location", location);
    	}
    	
    	
        NodeList operations = doc.getElementsByTagName("wsdl:operation");
        Element operationWSDL = null;
        
      //loop to select the label <wsdl:operation.../>
        for(int i=0; i<operations.getLength();i++){
        	operationWSDL = (Element) operations.item(i);
            
        	//Identify name operation
        	String name = operationWSDL.getAttribute("name");
            System.out.println("Name operationWSDL: "+name);
                    
            NodeList operationsSOAP = operationWSDL.getElementsByTagName("soap:operation");
            
            //loop to select the labels <soap:operation.../>
            for(int j=0; j<operationsSOAP.getLength();j++){
            	
            	Element operationSOAP = (Element) operationsSOAP.item(j) ;                
                
                //Set soapAction with the new location and the same name
                //operationSOAP.setAttribute("soapAction", location + "/" + name);
            	operationSOAP.setAttribute("soapAction", location + "/" + "services");
                System.out.println("Name operationSOAP: "+operationSOAP.getAttribute("soapAction"));
            }            
        }
    }

  public ModifyXML() {
	  this.WSDL = "";
  }
    
}
