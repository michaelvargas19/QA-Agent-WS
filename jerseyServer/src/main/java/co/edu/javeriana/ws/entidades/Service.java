/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ws.entidades;
import java.util.List;
/**
 *
 * @author USUARIO
 */
public class Service {
    
    private String nombre;
    private String URL;
    private List<Politicas> politicas;  

    public Service(String nombre, String URL, List<Politicas> politicas) {
        this.nombre = nombre;
        this.URL = URL;
        this.politicas = politicas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public List<Politicas> getPoliticas() {
        return politicas;
    }

    public void setPoliticas(List<Politicas> politicas) {
        this.politicas = politicas;
    }

    
}
