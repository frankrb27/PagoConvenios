package co.edu.javeriana.modval.control;

import java.io.ByteArrayInputStream;
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
	private static String TEMPLATE_JSON = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:output method=\"text\" encoding=\"utf-8\"/><xsl:template match=\"/*[node()]\"><xsl:text>{</xsl:text><xsl:apply-templates select=\".\" mode=\"detect\" /><xsl:text>}</xsl:text></xsl:template><xsl:template match=\"*\" mode=\"detect\"><xsl:choose><xsl:when test=\"name(preceding-sibling::*[1]) = name(current()) and name(following-sibling::*[1]) != name(current())\"><xsl:apply-templates select=\".\" mode=\"obj-content\" /><xsl:text>]</xsl:text><xsl:if test=\"count(following-sibling::*[name() != name(current())]) &gt; 0\">, </xsl:if></xsl:when><xsl:when test=\"name(preceding-sibling::*[1]) = name(current())\"><xsl:apply-templates select=\".\" mode=\"obj-content\" /><xsl:if test=\"name(following-sibling::*) = name(current())\">, </xsl:if></xsl:when><xsl:when test=\"following-sibling::*[1][name() = name(current())]\"><xsl:text>\"</xsl:text><xsl:value-of select=\"name()\"/><xsl:text>\" : [</xsl:text><xsl:apply-templates select=\".\" mode=\"obj-content\" /><xsl:text>, </xsl:text></xsl:when><xsl:when test=\"count(./child::*) > 0 or count(@*) > 0\"><xsl:text>\"</xsl:text><xsl:value-of select=\"name()\"/>\" : <xsl:apply-templates select=\".\" mode=\"obj-content\" /><xsl:if test=\"count(following-sibling::*) &gt; 0\">, </xsl:if></xsl:when><xsl:when test=\"count(./child::*) = 0\"><xsl:text>\"</xsl:text><xsl:value-of select=\"name()\"/>\" : \"<xsl:apply-templates select=\".\"/><xsl:text>\"</xsl:text><xsl:if test=\"count(following-sibling::*) &gt; 0\">, </xsl:if></xsl:when></xsl:choose></xsl:template><xsl:template match=\"*\" mode=\"obj-content\"><xsl:text>{</xsl:text><xsl:apply-templates select=\"@*\" mode=\"attr\" /><xsl:if test=\"count(@*) &gt; 0 and (count(child::*) &gt; 0 or text())\">, </xsl:if><xsl:apply-templates select=\"./*\" mode=\"detect\" /><xsl:if test=\"count(child::*) = 0 and text() and not(@*)\"><xsl:text>\"</xsl:text><xsl:value-of select=\"name()\"/>\" : \"<xsl:value-of select=\"text()\"/><xsl:text>\"</xsl:text></xsl:if><xsl:if test=\"count(child::*) = 0 and text() and @*\"><xsl:text>\"text\" : \"</xsl:text><xsl:value-of select=\"text()\"/><xsl:text>\"</xsl:text></xsl:if><xsl:text>}</xsl:text><xsl:if test=\"position() &lt; last()\">, </xsl:if></xsl:template><xsl:template match=\"@*\" mode=\"attr\"><xsl:text>\"</xsl:text><xsl:value-of select=\"name()\"/>\" : \"<xsl:value-of select=\".\"/><xsl:text>\"</xsl:text><xsl:if test=\"position() &lt; last()\">,</xsl:if></xsl:template><xsl:template match=\"node/@TEXT | text()\" name=\"removeBreaks\"><xsl:param name=\"pText\" select=\"normalize-space(.)\"/><xsl:choose><xsl:when test=\"not(contains($pText, '&#xA;'))\"><xsl:copy-of select=\"$pText\"/></xsl:when><xsl:otherwise><xsl:value-of select=\"concat(substring-before($pText, '&#xD;&#xA;'), ' ')\"/><xsl:call-template name=\"removeBreaks\"><xsl:with-param name=\"pText\" select=\"substring-after($pText, '&#xD;&#xA;')\"/></xsl:call-template></xsl:otherwise></xsl:choose></xsl:template></xsl:stylesheet>";
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
		StreamSource styleJson = new StreamSource((InputStream)(new ByteArrayInputStream(TEMPLATE_JSON.getBytes("UTF-8"))));
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
