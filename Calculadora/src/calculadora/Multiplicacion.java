/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

/**
 *
 * @author mvargasb
 */
public class Multiplicacion extends Operacion{
    
    double multi;
       
    public Multiplicacion(){
        super(0,0,'*');
    }
    
    public Multiplicacion(double n1, double n2) {
             
        super(n1, n2, '*');
        this.multi = n1 * n2;
        this.setRes(this.multi);
    }
}
