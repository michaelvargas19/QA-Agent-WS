package servidor.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Servicio {
	
	
	private Long id;
	
	private String nombre;
    private String URI;
    
    @OneToMany (mappedBy="servicio", cascade = CascadeType.ALL)
    private List<Politica> politicas;
    
           
	public Servicio(Long id, String nombre, String URI, List<Politica> politicas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.URI = URI;
		//this.politicas = politicas;
		cargarPoliticas();
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getURI() {
		return URI;
	}
	public void setURI(String uRI) {
		URI = uRI;
	}
	public List<Politica> getPoliticas() {
		return politicas;
	}
	public void setPoliticas(List<Politica> politicas) {
		this.politicas = politicas;
	}  

	public void cargarPoliticas(){
		
		this.politicas = new ArrayList<Politica>();
		
		this.politicas.add(new Politica (null, "Firma Digital", "configuracion/politica/1"));
		this.politicas.add(new Politica (null, "TimeStamp", "configuracion/politica/2"));

	}
    

}
