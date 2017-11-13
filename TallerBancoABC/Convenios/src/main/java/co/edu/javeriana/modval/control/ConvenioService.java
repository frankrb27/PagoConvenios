package co.edu.javeriana.modval.control;

import org.springframework.stereotype.Service;

import co.edu.javeriana.modval.entities.Convenio;

@Service
public class ConvenioService {
	public Convenio getConvenio(String idConvenio){
		if(idConvenio.equals("10000")){
		return new Convenio(
				"10000",
				"http://localhost:8080/servicios/pagos/v1/payments/",
				"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:template match=\"factura\"><factura><idFactura><xsl:value-of select=\"idFactura\"/></idFactura><valorFactura><xsl:value-of select=\"valorFactura\"/></valorFactura></factura></xsl:template></xsl:stylesheet>",
				"http://localhost:8080/servicios/pagos/v1/payments/10000?idFactura=",
				"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:template match=\"resultado\"><respuesta><idFactura><xsl:value-of select=\"idFactura\"/></idFactura><mensaje><xsl:value-of select=\"mensaje\"/></mensaje></respuesta></xsl:template></xsl:stylesheet>",
				"http://localhost:8080/servicios/pagos/v1/payments/10000?idFactura=",
				"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:template match=\"resultado\"><respuesta><idFactura><xsl:value-of select=\"idFactura\"/></idFactura><mensaje><xsl:value-of select=\"mensaje\"/></mensaje></respuesta></xsl:template></xsl:stylesheet>",
				true);
		}else if(idConvenio.equals("20000")){
			return new Convenio(
					"20000",
					"http://localhost:8080/servicios/pagos/v1/payments/",
					"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:template match=\"factura\"><factura><idFactura><xsl:value-of select=\"idFactura\"/></idFactura><valorFactura><xsl:value-of select=\"valorFactura\"/></valorFactura></factura></xsl:template></xsl:stylesheet>",
					"http://localhost:8080/servicios/pagos/v1/payments/10000?idFactura=",
					"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:template match=\"resultado\"><respuesta><idFactura><xsl:value-of select=\"idFactura\"/></idFactura><mensaje><xsl:value-of select=\"mensaje\"/></mensaje></respuesta></xsl:template></xsl:stylesheet>",
					"http://localhost:8080/servicios/pagos/v1/payments/10000?idFactura=",
					"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:template match=\"resultado\"><respuesta><idFactura><xsl:value-of select=\"idFactura\"/></idFactura><mensaje><xsl:value-of select=\"mensaje\"/></mensaje></respuesta></xsl:template></xsl:stylesheet>",
					true);
			
		}else if(idConvenio.equals("30000")){
			return new Convenio(
					"30000",
					"http://localhost:7070/w1-soap-svr/PagosServiceService",
					"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?><xsl:stylesheet xmlns:xsl=\\\"http://www.w3.org/1999/XSL/Transform\\\" version=\\\"1.0\\\" xmlns:S=\\\"http://schemas.xmlsoap.org/soap/envelope/\\\"  xmlns:A=\\\"http://www.servicios.co/pagos/schemas\\\"><xsl:output method=\\\"xml\\\" omit-xml-declaration=\\\"no\\\" encoding=\\\"utf-8\\\" indent=\\\"yes\\\" /><xsl:template match=\\\"/\\\"><factura><idFactura><xsl:value-of select=\\\"S:Envelope/S:Body/A:ResultadoConsulta/A:referenciaFactura/A:referenciaFactura\\\" /></idFactura><valorFactura><xsl:value-of select=\\\"S:Envelope/S:Body/A:ResultadoConsulta/A:totalPagar\\\" /></valorFactura></factura></xsl:template></xsl:stylesheet>",
					"http://localhost:7070/w1-soap-svr/PagosServiceService",
					"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:template match=\"resultado\"><respuesta><idFactura><xsl:value-of select=\"idFactura\"/></idFactura><mensaje><xsl:value-of select=\"mensaje\"/></mensaje></respuesta></xsl:template></xsl:stylesheet>",
					"http://localhost:7070/w1-soap-svr/PagosServiceService",
					"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:template match=\"resultado\"><respuesta><idFactura><xsl:value-of select=\"idFactura\"/></idFactura><mensaje><xsl:value-of select=\"mensaje\"/></mensaje></respuesta></xsl:template></xsl:stylesheet>",
					false);
			
		}else{
			return new Convenio();
		}
	}
}
