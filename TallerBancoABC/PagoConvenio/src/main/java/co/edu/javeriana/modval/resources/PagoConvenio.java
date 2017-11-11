package co.edu.javeriana.modval.resources;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.Response;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import co.edu.javeriana.modval.entities.Factura;

@RestController
@RequestMapping("banco/convenio/v1")
public class PagoConvenio {
    
    private static final String TS_SERVER = "localhost";
    private static final int TS_PORT = 8080;
    private static final String RESOURCE = "payments";
    private static String TS_URL = String.format("http://%s:%d/servicios/pagos/v1/%s/1000", TS_SERVER, TS_PORT, RESOURCE);
	
    @RequestMapping(path = "pagos/{idFactura}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Factura consultarFactura(@PathVariable("idFactura") String idFactura) {
    	
    	// Set XML content type explicitly to force response in XML (If not spring gets response in JSON)
    	HttpHeaders headers = new HttpHeaders();
    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
    	HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
    	RestTemplate restTemplate = new RestTemplate();
    	System.out.println("TS_URL: "+TS_URL);
    	ResponseEntity<String> response = restTemplate.exchange(TS_URL, HttpMethod.GET, entity, String.class);
    	String responseBody = response.getBody();
    	StringReader sr = new StringReader(responseBody);
    	Factura factura = null;
    	try{
    	JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
    	Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    	factura = (Factura) unmarshaller.unmarshal(sr);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	System.out.println(factura);
    	/*
    	Map<String, String> params = new HashMap<String, String>();
        params.put("idFactura", idFactura);
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("TS_URL: "+TS_URL);
        Object object = restTemplate.getForEntity(TS_URL, Object.class, params);
        System.out.println(object);
        */
        return factura;
    }
}
