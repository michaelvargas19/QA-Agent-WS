/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package clientserver;

import com.google.gson.Gson;
import entities.Servicio;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.RunJar;


public class ClientApp {
    
    private List<Servicio> servicios;

    public ClientApp() {
        this.servicios = new ArrayList<Servicio>();
    }
    
    public static void main(String[] args) {
        
        ClientApp app = new ClientApp();
        
        System.out.println("\t______________Download Configuration______________\n");
        app.downloadConfiguration();
        
        System.out.println("\n\n\t______________Download JAR______________\n");
        app.downloadJAR();
        
        System.out.println("\n\n\t______________Run JAR______________\n");
        app.runJAR();
    }
    
    private void downloadConfiguration(){
    
        //Load Keys
        ApplicationConfig ac = ApplicationConfig.getInstance();
        ac.setKEYSTOREPATH("./client.keystore");
        ac.setTRUSTSTOREPATH("./client.truststore");
        ac.setKEYSTOREPW("javeriana");
        ac.setTRUSTSTOREPW("javeriana");
        ac.setKEYPASS("javeriana");
        ac.setKeystoreType("jks");
        ac.setTrustAllCertificate("true");
        ac.setKeymanageralgorithm("SunX509");

        try {
        //Create sslClient
            SSLClient sslClient = SSLClient.getSSLClient();
            String strurl ;
            URL url;
            String method;
            String message;
            String msgtype;
            String response;

        //A RESTFul GET web service call
            strurl = "https://localhost:8080/configuracion/1";

            url = new URL(strurl);
            method = "GET";
            message = "";
            msgtype = "application/json";
            
            response = sslClient.sendRequest(url, method, message, msgtype);
            Servicio data = new Gson().fromJson(response, Servicio.class);
            this.servicios.add(data);
            
            System.out.println("--Servicios actualizados--");
            
        } catch (Exception e) {
          System.out.println(e);
        }
        
    }
    
    private void downloadJAR(){
    
        //Load Keys
        ApplicationConfig ac = ApplicationConfig.getInstance();
        ac.setKEYSTOREPATH("./client.keystore");
        ac.setTRUSTSTOREPATH("./client.truststore");
        ac.setKEYSTOREPW("javeriana");
        ac.setTRUSTSTOREPW("javeriana");
        ac.setKEYPASS("javeriana");
        ac.setKeystoreType("jks");
        ac.setTrustAllCertificate("true");
        ac.setKeymanageralgorithm("SunX509");

        try {
            //Create sslClient
            SSLClient sslClient = SSLClient.getSSLClient();
            String strurl;
            URL url;
            String method;
            String message;
            String msgtype;
            String response;

        //A RESTFul GET web service call
            strurl = "https://localhost:8080/configuracion/politica/1";
            url = new URL(strurl);
            method = "GET";
            message = "";
            msgtype = "application/json";
                        
            response = sslClient.downloadJAR(url, method, message, msgtype);
            System.out.println("--El JAR se guardó en: " + response);
            
            
        } catch (Exception e) {
          System.out.println(e);
        }
        
    }
    
    
    private void runJAR(){
        RunJar execute = new RunJar();
        
        try{
          
            execute.run(".\\Jars\\Calculadora.jar");
            
        } catch (IOException ex) {
            Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
