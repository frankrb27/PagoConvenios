package co.edu.javeriana.modval.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Factura {

    private String idFactura;
    private double valorFactura;
	
    /**
     * Constructor
     */
    public Factura() {}
    
    /**
     * Constructor
     * @param valorFactura
     */
    public Factura(double valorFactura) {
        this.valorFactura = valorFactura;
    }

    /**
     * Constructor
     * @param idFactura
     * @param valorFactura
     */
    public Factura(String idFactura, double valorFactura) {
        this.idFactura = idFactura;
        this.valorFactura = valorFactura;
    }    
    
    /**
	 * @return the idFactura
	 */
	public String getIdFactura() {
		return idFactura;
	}
	
	/**
	 * @param idFactura the idFactura to set
	 */
	@XmlElement
	public void setIdFactura(String idFactura) {
		this.idFactura = idFactura;
	}
	/**
	 * @return the valorFactura
	 */
	public double getValorFactura() {
		return valorFactura;
	}
	/**
	 * @param valorFactura the valorFactura to set
	 */
	@XmlElement
	public void setValorFactura(double valorFactura) {
		this.valorFactura = valorFactura;
	}
	
	
	public String toString(){
		return "idFactura: "+idFactura+","+"valorFactura: "+valorFactura;
	}
}
