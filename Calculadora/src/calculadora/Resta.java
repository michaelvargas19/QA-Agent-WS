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
public class Resta extends Operacion{
    
    double resta;
       
    public Resta(){
        super(0,0,'-');
    }
    
    public Resta(double n1, double n2) {
             
        super(n1, n2, '-');
        this.resta = n1 - n2;
        this.setRes(this.resta);
    }
}
