package co.edu.javeriana.modval.entities;

public class Respuesta {

	private String idFactura;
	private String mensaje;
	
    /**
     * Constructor
     */
    public Respuesta() {}
    
    /**
     * Constructor
     * @param idFactura
     * @param mensaje
     */
    public Respuesta(String idFactura, String mensaje) {
        this.idFactura = idFactura;
        this.mensaje = mensaje;
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
	public void setIdFactura(String idFactura) {
		this.idFactura = idFactura;
	}
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
