package co.edu.javeriana.modval.entities;

public class Convenio {

	private String idConvenio;
	private String urlConsulta;
	private String templateConsulta;
	private String urlPago;
	private String templatePago;
	private String urlCompensacion;
	private String templateCompensacion;
	private boolean isREST;
	
    /**
     * Constructor
     */
    public Convenio() {}
   
    /**
     * Constructor
     * @param idConvenio
     * @param urlConsulta
     * @param templateConsulta
     * @param urlPago
     * @param templatePago
     * @param urlCompensacion
     * @param templateCompensacion
     * @param isREST
     */
    public Convenio(String idConvenio, String urlConsulta, String templateConsulta, String urlPago, String templatePago, String urlCompensacion, String templateCompensacion, boolean isREST) {
        this.idConvenio = idConvenio;
        this.urlConsulta = urlConsulta;
        this.templateConsulta = templateConsulta;
        this.urlPago = urlPago;
        this.templatePago = templatePago;
        this.urlCompensacion = urlCompensacion;
        this.templateCompensacion = templateCompensacion;
        this.isREST = isREST;
    }

	/**
	 * @return the idConvenio
	 */
	public String getIdConvenio() {
		return idConvenio;
	}

	/**
	 * @param idConvenio the idConvenio to set
	 */
	public void setIdConvenio(String idConvenio) {
		this.idConvenio = idConvenio;
	}

	/**
	 * @return the urlConsulta
	 */
	public String getUrlConsulta() {
		return urlConsulta;
	}

	/**
	 * @param urlConsulta the urlConsulta to set
	 */
	public void setUrlConsulta(String urlConsulta) {
		this.urlConsulta = urlConsulta;
	}

	/**
	 * @return the templateConsulta
	 */
	public String getTemplateConsulta() {
		return templateConsulta;
	}

	/**
	 * @param templateConsulta the templateConsulta to set
	 */
	public void setTemplateConsulta(String templateConsulta) {
		this.templateConsulta = templateConsulta;
	}

	/**
	 * @return the urlPago
	 */
	public String getUrlPago() {
		return urlPago;
	}

	/**
	 * @param urlPago the urlPago to set
	 */
	public void setUrlPago(String urlPago) {
		this.urlPago = urlPago;
	}

	/**
	 * @return the templatePago
	 */
	public String getTemplatePago() {
		return templatePago;
	}

	/**
	 * @param templatePago the templatePago to set
	 */
	public void setTemplatePago(String templatePago) {
		this.templatePago = templatePago;
	}

	/**
	 * @return the urlCompensacion
	 */
	public String getUrlCompensacion() {
		return urlCompensacion;
	}

	/**
	 * @param urlCompensacion the urlCompensacion to set
	 */
	public void setUrlCompensacion(String urlCompensacion) {
		this.urlCompensacion = urlCompensacion;
	}

	/**
	 * @return the templateCompensacion
	 */
	public String getTemplateCompensacion() {
		return templateCompensacion;
	}

	/**
	 * @param templateCompensacion the templateCompensacion to set
	 */
	public void setTemplateCompensacion(String templateCompensacion) {
		this.templateCompensacion = templateCompensacion;
	}

	/**
	 * @return the isREST
	 */
	public boolean isREST() {
		return isREST;
	}

	/**
	 * @param isREST the isREST to set
	 */
	public void setREST(boolean isREST) {
		this.isREST = isREST;
	}
	
}