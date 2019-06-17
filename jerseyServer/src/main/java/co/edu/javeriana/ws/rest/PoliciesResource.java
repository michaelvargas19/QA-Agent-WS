/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.ws.rest;


import co.edu.javeriana.ws.entidades.Politicas;
import co.edu.javeriana.ws.entidades.Service;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author USUARIO
 */
@Path("v1/policies")
public class PoliciesResource {
    
     @GET   
     @Produces("application/json")
    public List<Service> getServices() {
        List<Service> listaServicios=new ArrayList<>();
        List<Politicas> listaPoliticas=new ArrayList<>();
       
        listaPoliticas.add(new Politicas(new Date(),"Firma"));
        listaPoliticas.add(new Politicas(new Date(),"Timestamp"));
                
        
        listaServicios.add(new Service("service 1","http:localhost/service/one",listaPoliticas));
    	listaServicios.add(new Service("service 2","http:localhost/service/two",listaPoliticas));
    	return listaServicios;
    }
 
}
