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
public class Operacion {
    
    double n1;
    double n2;
    double res;
    char operacion;

    public Operacion(){
    
    }
    
    public Operacion(double n1, double n2, char operacion) {
        this.n1 = n1;
        this.n2 = n2;
        this.operacion = operacion;
    }
    
        
    public void mostrarResultado(double n1, double n2){
        calcular(n1, n2);
        System.out.println("\t"+this.n1+" "+this.operacion+" "+this.n2+" = "+this.res);
        
    }
    
    public String resultadoToString(double n1, double n2){
        calcular(n1, n2);
        return this.n1+" "+this.operacion+" "+this.n2+" = "+this.res;
        
    }
    public double getResultado(double n1, double n2){
        calcular(n1, n2);
        return this.res;
        
    }
    
    
    private void calcular(double n1, double n2){
        
        this.n1 = n1;
        this.n2 = n2;
        
        if(this.operacion == '+'){
            this.res = this.n1 + this.n2;
        }
        if(this.operacion == '-'){
            this.res = this.n1 - this.n2;
        }
        if(this.operacion == '*'){
            this.res = this.n1 * this.n2;
        }
        if(this.operacion == '/'){
            if(n2==0){
                this.res = 0;
            } 
            else{
                this.res = n1 / n2;
            } 
        }
    }

    //---------------------------------------Getters and Setters
    
    public double getN1() {
        return n1;
    }

    public void setN1(double n1) {
        this.n1 = n1;
    }

    public double getN2() {
        return n2;
    }

    public void setN2(double n2) {
        this.n2 = n2;
    }

    public double getRes() {
        return res;
    }

    public void setRes(double res) {
        this.res = res;
    }

    public char getOperacion() {
        return operacion;
    }

    public void setOperacion(char operacion) {
        this.operacion = operacion;
    }
    
    
    
    
}
