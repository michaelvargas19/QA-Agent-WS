/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleserver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Java program to create a simple HTTP Server to demonstrate how to use
 * ServerSocket and Socket class.
 * 
 * @author Javin Paul
 */
public class SimpleServer {

    public static void main(String args[]) throws IOException {

        //Create socket to receive http message
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening for connection on port 8080 ....");
        
        while (true) {
            try (Socket socket = server.accept()) {
                
                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                String line = reader.readLine();
                
                //Show message received
                System.out.println("-------------------------------------------------<Menssage>");
                
                if( line != null){  
                    while (!(line.compareTo("") == 0)  )
                    { 
                    System.out.println(line); 
                    line = reader.readLine();
                    }
                }
                
                System.out.println("-------------------------------------------------</Menssage>");

                //Send response
                Date today = new Date();
                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
                socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
            }
        }
    }

}

