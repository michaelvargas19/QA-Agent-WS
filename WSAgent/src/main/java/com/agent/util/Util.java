package com.agent.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.agent.entities.Agent;

@Component("Util")
public class Util {
	
	private Agent agent;
	
	public Agent readPropiertes() {
		
		try {
			
			Properties configuratio = new Properties();
			InputStream input = new FileInputStream("./conf.properties");
			
			configuratio.load(input);
			String ipGuardian = configuratio.getProperty("ipGuardian");
			String portGuardian = configuratio.getProperty("portGuardian");
			String ipAgent = configuratio.getProperty("ipAgent");
			String portAgent = configuratio.getProperty("portAgent");
			
			this.agent = new Agent( ipGuardian,Integer.parseInt(portGuardian), ipAgent, Integer.parseInt(portAgent));
			
			return this.agent;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@PostConstruct
    public void init() {
		readPropiertes();
    }
	
	
	public boolean saveKey(String primaryKey, String publicKeyWSG) {
		return true;
	}
	
	public boolean saveJAR(File policy) {
		return true;
	}
	
	public boolean saveWSDL(String wsdl) {
		return true;
	}
	
	public String fixWSDL(String wsdl) {

		
		String wsdlFixed = wsdl;
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        
        try {
            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(new InputSource(new StringReader(wsdlFixed)));
            
            doc.getDocumentElement().normalize();
            
            //Configure Services
            fixServicesURL(doc);
            
            //write the updated document to file and return
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            
            //create and configurate channel to export the new WSDL
            StreamResult result = new StreamResult(new File("./Class/WSDl_updated.wsdl"));
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(source, result);
            
            StringWriter sw = new StringWriter();
            result = new StreamResult(sw);
            transformer.transform(source, result);
            
            wsdlFixed = sw.toString();            
            
         
            
        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
            e1.printStackTrace();
        }
        return wsdlFixed;
		
	}
	
	private void fixServicesURL(Document doc) {
		
    	//Identify labels <soap:address.../>
    	NodeList bidings = doc.getElementsByTagName("soap:address");
    	Element bidingSOAP = null;
    	
    	//loop to select the label <soap:address.../>
    	for(int i=0; i<bidings.getLength();i++){
    		bidingSOAP = (Element) bidings.item(i);
    		
    		//Set attribute WS location on label <soap:address.../>
    		String location = bidingSOAP.getAttribute("location");
    		String [] subPaths = location.split("/");
    		
    		location = "http://" + this.agent.getIpAgent() +":" + this.agent.getPortAgent() + "/operations";
    		
    		for(int p = 3; p<subPaths.length ; p++) {
    			location = location + "/" + subPaths[p]; 
    		}
    		
    		bidingSOAP.setAttribute("location", location);
    	}
    	
    	//Identify labels <soap11:address.../>
    	bidings = doc.getElementsByTagName("soap11:address");
    	bidingSOAP = null;
    	
    	//loop to select the label <soap11:address.../>
    	for(int i=0; i<bidings.getLength();i++){
    		bidingSOAP = (Element) bidings.item(i);
    		
    		//Set attribute WS location on label <soap:address.../>
    		String location = bidingSOAP.getAttribute("location");
    		String [] subPaths = location.split("/");
    		
    		location = "http://" + this.agent.getIpAgent() +":" + this.agent.getPortAgent() + "/operations";
    		
    		for(int p = 3; p<subPaths.length ; p++) {
    			location = location + "/" + subPaths[p]; 
    		}
    		
    		bidingSOAP.setAttribute("location", location);
    	}
    	
    	//Identify labels <soap12:address.../>
    	bidings = doc.getElementsByTagName("soap12:address");
    	bidingSOAP = null;
    	
    	//loop to select the label <soap11:address.../>
    	for(int i=0; i<bidings.getLength();i++){
    		bidingSOAP = (Element) bidings.item(i);
    		
    		//Set attribute WS location on label <soap:address.../>
    		String location = bidingSOAP.getAttribute("location");
    		String [] subPaths = location.split("/");
    		
    		location = "http://" + this.agent.getIpAgent() +":" + this.agent.getPortAgent() + "/operations";
    		
    		for(int p = 3; p<subPaths.length ; p++) {
    			location = location + "/" + subPaths[p]; 
    		}
    		
    		bidingSOAP.setAttribute("location", location);
    	}
    	
    	deleteHttp(doc);
    	
	}
	
	
	public String getRequestToWSDL(String nameService) {
		String html = "<http>" + nameService + "</http>";
		return html;
	}
	
	public String getErrorConfiguration(String nameService) {
		String html = "{Error: ' configurating " + nameService + " WSDL'}</http>";
		
		return html;
	}
	
	public void deleteHttp(Document doc) {
	    // <wsdl:service...>
		List<Element> delElement = new ArrayList<Element>();
	    NodeList nodeS = doc.getElementsByTagName("wsdl:service");
	    

	    for (int i = 0; i < nodeS.getLength(); i++) {
	      Element labelS = (Element)nodeS.item(i);
	      
	      //<wsdl:port...>
	      NodeList nodesP = labelS.getElementsByTagName("wsdl:port");
	      
	      for (int l = 0; l < nodesP.getLength(); l++) {
		      Element labelP = (Element)nodesP.item(l);
		      
		      //<http:address...>
		      NodeList nodesH = labelP.getElementsByTagName("http:address");
		      
		      if(nodesH.getLength() >0) {
		    	  delElement.add(labelP);		    	  
		      }
	    	  
	      }
	      
	      for (Element element : delElement) {
	    	  labelS.removeChild(element);
	      }
	    }
	    
	 // <wsdl:service...>
	 		delElement = new ArrayList<Element>();
	 	    nodeS = doc.getElementsByTagName("wsdl:service");
	 	    

	 	    for (int i = 0; i < nodeS.getLength(); i++) {
	 	      Element labelS = (Element)nodeS.item(i);
	 	      
	 	      //<wsdl:port...>
	 	      NodeList nodesP = labelS.getElementsByTagName("wsdl:port");
	 	      
	 	      for (int l = 0; l < nodesP.getLength(); l++) {
	 		      Element labelP = (Element)nodesP.item(l);
	 		      
	 		      //<https:address...>
	 		      NodeList nodesH = labelP.getElementsByTagName("https:address");
	 		      
	 		      if(nodesH.getLength() >0) {
	 		    	  delElement.add(labelP);		    	  
	 		      }
	 	    	  
	 	      }
	 	      
	 	      for (Element element : delElement) {
	 	    	  labelS.removeChild(element);
	 	      }
	 	      
	 	      
	 	    }

	  }
	
	
	//---------------------------------------------------------------------------

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	

}
