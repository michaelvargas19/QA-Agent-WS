/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserver;

import java.net.URL;


public class ClientApp {

    public static void main(String[] args) {

        System.out.println("In main");

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

//A SOAP web service call

            SSLClient sslClient = SSLClient.getSSLClient();
            String strurl ;//= "https://localhost:23521/app/v1/myservice";//you can add all the urls in config file
            URL url;// = new URL(strurl);
            String method ;//= "POST";
            String message ;//= "your soap message body";
            String msgtype;// = "text/xml";
            String response;// = sslClient.sendRequest(url, method, message, msgtype);

//A RESTFul GET web service call
            strurl = "https://192.168.1.107:8443/WSGuardian/api/myresource";

            url = new URL(strurl);

            method = "GET";

            message = "";

            msgtype = "application/json";

            response = sslClient.sendRequest(url, method, message, msgtype);
            
            System.out.println("respons"+response);

        } catch (Exception e) {

            System.out.println("e"+e);

        }

    }

}
