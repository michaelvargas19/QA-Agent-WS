/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mvargasb
 */
public class Calculadora {

    
    public static void main(String[] args) {
        
        try {
            
            System.err.println("----------------------Main's Call----------------------\n");
            Thread.sleep(500);
            //Invocar multiplicación
            Operacion opM = new Multiplicacion();
            System.out.println("Multiplicación:");
            opM.mostrarResultado(5,4);
            
            //Invocar multiplicación
            Operacion opD = new Division();
            System.out.println("División:");
            opD.mostrarResultado(5,4);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Calculadora.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
