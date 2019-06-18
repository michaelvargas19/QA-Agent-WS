package servidor.servicios;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Configuracion {

	@RequestMapping("configuracion")
	public String getConfiguracion() {
		return "hello";
	}
	
}
