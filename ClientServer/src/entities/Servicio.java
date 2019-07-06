package entities;


import java.util.ArrayList;
import java.util.List;


public class Servicio {
	
    private Long id;
	
    private String nombre;
    private String uri;
    private List<Politica> politicas;
    
           
	public Servicio(String nombre, String uri, List<Politica> politicas) {
		super();
		this.nombre = nombre;
		this.uri = uri;
		this.politicas = politicas;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Politica> getPoliticas() {
		return politicas;
	}
	public void setPoliticas(List<Politica> politicas) {
		this.politicas = politicas;
	}  

	

}
