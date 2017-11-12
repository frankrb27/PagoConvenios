package co.edu.javeriana.modval.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.modval.control.PagoConvenioService;
import co.edu.javeriana.modval.entities.Convenio;
import co.edu.javeriana.modval.entities.Factura;
import co.edu.javeriana.modval.entities.Respuesta;

@RestController
@RequestMapping("banco/convenio/v1")
public class PagoConvenio {

	@Autowired
	private PagoConvenioService pagoConvenioService;

	@RequestMapping(path = "pagos/{idFactura}", method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Factura consultarFactura(@PathVariable("idFactura") String idFactura) {
		try{
			//Consultar convenio
			Convenio convenio = pagoConvenioService.getConvenio(idFactura);
			if(convenio!=null && convenio.getUrlConsulta()!=null){
				//Invocar servicio
				String responseXML = pagoConvenioService.invokeRest(convenio.getUrlConsulta().concat(idFactura), HttpMethod.GET);
				return pagoConvenioService.getFactura(responseXML,convenio.getTemplateConsulta());
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(path = "pagos/{idFactura}", method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Respuesta pagarFactura(@PathVariable("idFactura") String idFactura) {
		try{
			//Consultar convenio
			Convenio convenio = pagoConvenioService.getConvenio(idFactura);
			if(convenio!=null && convenio.getUrlPago()!=null){
				//Invocar servicio
				String responseXML = pagoConvenioService.invokeRest(convenio.getUrlCompensacion().concat(idFactura), HttpMethod.POST);
				return pagoConvenioService.getRespuesta(responseXML,convenio.getTemplatePago());
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}	
	
	@RequestMapping(path = "pagos/{idFactura}", method = RequestMethod.DELETE,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Respuesta compensarFactura(@PathVariable("idFactura") String idFactura) {
		try{
			//Consultar convenio
			Convenio convenio = pagoConvenioService.getConvenio(idFactura);
			if(convenio!=null && convenio.getUrlCompensacion()!=null){
				//Invocar servicio
				String responseXML = pagoConvenioService.invokeRest(convenio.getUrlCompensacion().concat(idFactura), HttpMethod.DELETE);
				return pagoConvenioService.getRespuesta(responseXML,convenio.getTemplateCompensacion());
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}	
}
