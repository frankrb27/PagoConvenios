package co.edu.javeriana.modval.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.modval.control.ConvenioService;
import co.edu.javeriana.modval.entities.Convenio;

@RestController
@RequestMapping("banco/convenio/v1")
public class ConvenioResource {

	@Autowired
	private ConvenioService pagoConvenioService;

	@RequestMapping(path = "consulta/{idConvenio}", method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Convenio consultarFactura(@PathVariable("idConvenio") String idConvenio) {
		try{
			//Consultar convenio
			Convenio convenio = pagoConvenioService.getConvenio(idConvenio);
			if(convenio!=null && convenio.getUrlConsulta()!=null){
				return convenio;
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
