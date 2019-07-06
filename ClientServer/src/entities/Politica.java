package entities;


import java.util.Date;



public class Politica {

    private Long id;
	
    private Date expiracion;
    private String nombre;
    private String uri;

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
