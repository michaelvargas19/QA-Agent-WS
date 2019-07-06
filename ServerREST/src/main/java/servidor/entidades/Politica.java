package servidor.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Politica {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date expiracion;
    private String nombre;
    private String uri;
    
    @ManyToOne
    private Servicio servicio;
    
    
	public Politica(Date expiracion, String nombre, String uri) {
		super();
		this.expiracion = expiracion;
		this.nombre = nombre;
		this.uri = uri;
	}
	
	
	
	
	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}




	public Date getExpiracion() {
		return expiracion;
	}
	public void setExpiracion(Date expiracion) {
		this.expiracion = expiracion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getUri() {
		return uri;
	}




	public void setUri(String uri) {
		this.uri = uri;
	}

	
    
}
