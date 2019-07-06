package servidor.servicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import servidor.entidades.Servicio;

@RestController
@RequestMapping("/configuracion")
public class Configuracion {
	
	private static final String FILE_PATH = ".\\src\\main\\resources\\Jars\\Calculadora.jar";
    private static final String APPLICATION_TYPE = "application/java";

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public Servicio getConfiguracion(@PathVariable("id") Long id) {
		
		List<Servicio> servicios = new ArrayList<Servicio>();
		servicios.add(new Servicio(Long.parseLong("1"), "Sumar", "calculadora/sumar", null));
		return servicios.get(0);
	}
	
	@RequestMapping(value = "/politica/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody void getPolitica(@PathVariable("id") Long id, HttpServletResponse response) {
		
		try {
			File file = getFile();
	        InputStream in = new FileInputStream(file);

	        response.setContentType(APPLICATION_TYPE);
	        
	        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
	        response.setHeader("Content-Length", String.valueOf(file.length()));
	        FileCopyUtils.copy(in, response.getOutputStream());
	        
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		//return null;

	}
	
	
	private File getFile() throws FileNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()){
            throw new FileNotFoundException("file with path: " + FILE_PATH + " was not found.");
        }
        return file;
    }
	
}
