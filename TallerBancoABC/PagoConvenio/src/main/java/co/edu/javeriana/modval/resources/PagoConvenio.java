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

	@RequestMapping(path = "pagos/test/{idFactura}", method = RequestMethod.GET,
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public String test(@PathVariable("idFactura") String idFactura) throws Exception{
		return "TEST";
	}	

	@RequestMapping(path = "pagos/{idFactura}", method = RequestMethod.GET,
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Factura consultarFactura(@PathVariable("idFactura") String idFactura) throws Exception{
		Convenio convenio = null;
		try{
			//Consultar convenio
			convenio = pagoConvenioService.getConvenio(idFactura);
			String responseXML = null;
			if(convenio!=null && convenio.getUrlConsulta()!=null){
				if(convenio.isREST()) {
					//Invocar servicio
					responseXML = pagoConvenioService.invokeRest(convenio.getUrlConsulta().concat(idFactura), HttpMethod.GET);
				}else {
					responseXML = pagoConvenioService.invokeSoap(convenio.getUrlConsulta());
				}
				return pagoConvenioService.getFactura(responseXML,convenio.getTemplateConsulta());
			}else{
				return new Factura("0",0.0);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	@RequestMapping(path = "pagos/{idFactura}", method = RequestMethod.POST,
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
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
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
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
