/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runjar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mvargasb
 */
public class RunJar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        
        try {
            RunJar runnable = new RunJar();
            
            runnable.invocateMainClass();
            
            Thread.sleep(1000);
            System.err.println("----------------------JAR's Call----------------------\n");
            
            //Define parameters types
            Class[] typesParameters = new Class[2];
            typesParameters[0] = double.class;
            typesParameters[1] = double.class;
            
            //Define parameters value
            Object[] parameters = new Object[2];
            parameters[0] = 4;
            parameters[1] = 5;
            
            //call to 'suma' the jar method
            System.out.println("Suma:");
            runnable.invocateMethods("calculadora.Suma", "resultadoToString", typesParameters,parameters);
            
            //call to 'resta' the jar method
            System.out.println("Resta:");
            runnable.invocateMethods("calculadora.Resta", "getResultado", typesParameters,parameters);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(RunJar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void invocateMainClass(){
 
        try {
            
            String [] args = new String [0];
            
            //Load Jar
            JarClassLoader jcl = new JarClassLoader(new File(".\\Jars\\Calculadora.jar").toURI().toURL());
            
            //Load Jar
            jcl.invokeClass("calculadora.Calculadora", args );
            

        } catch (MalformedURLException ex) {
            Logger.getLogger(RunJar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RunJar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(RunJar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(RunJar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void invocateMethods( String nClass, String nMethod, Class[] typesParameters, Object[] parameters){
 
        try {
 
            //Load Jar
            JarClassLoader jcl = new JarClassLoader(new File(".\\Jars\\Calculadora.jar").toURI().toURL());
            
            //Invoke Method Suma from JAR
            jcl.invokeMethod(nClass, nMethod,typesParameters, parameters);

        } catch (MalformedURLException ex) {
            Logger.getLogger(RunJar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
       
}
