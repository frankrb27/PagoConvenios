package co.edu.javeriana.modval.control;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Arrays;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import co.edu.javeriana.modval.entities.Convenio;
import co.edu.javeriana.modval.entities.Factura;
import co.edu.javeriana.modval.entities.Respuesta;

@Service
public class PagoConvenioService {
	
	public Convenio getConvenio(String idFactura){
		if(idFactura.equals("10000")){
		return new Convenio(
				"1000",
				"http://localhost:8080/servicios/pagos/v1/payments/",
				"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:template match=\"factura\"><factura><idFactura><xsl:value-of select=\"idFactura\"/></idFactura><valorFactura><xsl:value-of select=\"valorFactura\"/></valorFactura></factura></xsl:template></xsl:stylesheet>",
				"http://localhost:8080/servicios/pagos/v1/payments/10000?idFactura=",
				"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:template match=\"resultado\"><respuesta><idFactura><xsl:value-of select=\"idFactura\"/></idFactura><mensaje><xsl:value-of select=\"mensaje\"/></mensaje></respuesta></xsl:template></xsl:stylesheet>",
				"http://localhost:8080/servicios/pagos/v1/payments/10000?idFactura=",
				"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:template match=\"resultado\"><respuesta><idFactura><xsl:value-of select=\"idFactura\"/></idFactura><mensaje><xsl:value-of select=\"mensaje\"/></mensaje></respuesta></xsl:template></xsl:stylesheet>");
		}else if(idFactura.equals("20000")){
			return new Convenio(
					"1000",
					"http://localhost:8080/servicios/pagos/v1/payments/",
					"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:template match=\"factura\"><factura><idFactura><xsl:value-of select=\"idFactura\"/></idFactura><valorFactura><xsl:value-of select=\"valorFactura\"/></valorFactura></factura></xsl:template></xsl:stylesheet>",
					"http://localhost:8080/servicios/pagos/v1/payments/10000?idFactura=",
					"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:template match=\"resultado\"><respuesta><idFactura><xsl:value-of select=\"idFactura\"/></idFactura><mensaje><xsl:value-of select=\"mensaje\"/></mensaje></respuesta></xsl:template></xsl:stylesheet>",
					"http://localhost:8080/servicios/pagos/v1/payments/10000?idFactura=",
					"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:template match=\"resultado\"><respuesta><idFactura><xsl:value-of select=\"idFactura\"/></idFactura><mensaje><xsl:value-of select=\"mensaje\"/></mensaje></respuesta></xsl:template></xsl:stylesheet>");
			
		}else{
			return new Convenio();
		}
	}
	
	/**
	 * Invocar servicio REST
	 * @param url
	 * @param httpMethod
	 * @return
	 */
	public String invokeRest(String url, HttpMethod httpMethod){
		// Set XML content type explicitly to force response in XML (If not spring gets response in JSON)
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
		headers.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		RestTemplate restTemplate = new RestTemplate();
		System.out.println("URL: "+url);
		ResponseEntity<String> response = restTemplate.exchange(url, httpMethod, entity, String.class);
		return response.getBody();
	}
	
	/**
	 * Obtener objeto Factura
	 * @param xml
	 * @param template
	 * @return
	 * @throws Exception
	 */
	public Factura getFactura (String xml, String template) throws Exception{
		String response = getResponse(xml, template);
		response = response.replace("{\"factura\" : ", "").replace("}}", "}");
		return (Factura)getObject(response, Factura.class);
	}
	
	/**
	 * Obtener objeto Respuesta
	 * @param xml
	 * @param template
	 * @return
	 * @throws Exception
	 */
	public Respuesta getRespuesta (String xml, String template) throws Exception{
		String response = getResponse(xml, template);
		response = response.replace("{\"respuesta\" : ", "").replace("}}", "}");
		return (Respuesta)getObject(response, Respuesta.class);
	}
	
	/**
	 * Transformar respuesta del servicio XML a un objeto Factura
	 * @param xml
	 * @param template
	 * @return
	 */
	private String getResponse(String xml, String template) throws Exception{
		//Cargar XML como StreamSource
		InputStream isXMLResponse = new ByteArrayInputStream(xml.getBytes("UTF-8"));
	    StreamSource source = new StreamSource(isXMLResponse);
	    
	    //Cargar plantilla XLS según el convenio
	    InputStream isXSLTemplate = new ByteArrayInputStream(template.getBytes("UTF-8"));
	    StreamSource stylesource = new StreamSource(isXSLTemplate);
	    
	    //Transformar objeto
	    TransformerFactory factory = TransformerFactory.newInstance();
	    Transformer transformer = factory.newTransformer(stylesource);
	    
	    StringWriter writer = new StringWriter();
	    StreamResult result = new StreamResult(writer);
	    transformer.transform(source, result);
	    
		return getJson(writer.toString());
	}
	
	/**
	 * Obtener JSON a partir de un XML
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	private String getJson(String xml) throws Exception{
	    //Cargar template XML to JSON
		StreamSource styleJson = new StreamSource(new File("D:\\Frank\\Javeriana\\Semestre I\\Modelado y validación de arquitectura\\workspace\\XSLTransform\\json.xsl"));
		TransformerFactory factory = TransformerFactory.newInstance();
	    Transformer transformer = factory.newTransformer(styleJson);
	    StringWriter writer = new StringWriter();
	    writer = new StringWriter();
	    StreamResult result = new StreamResult(writer);
	    InputStream isXML2 = new ByteArrayInputStream(xml.getBytes("UTF-8"));
	    transformer.transform(new StreamSource(isXML2), result);
	    return writer.toString();
	}
	
	/**
	 * Obtener Object a partir de un JSON
	 * @param json
	 * @return
	 */
	private Object getObject(String json, Class class_){
		Gson gson = new Gson();
		return gson.fromJson(json, class_); 
	}
}
