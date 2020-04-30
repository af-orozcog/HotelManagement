package uniandes.isis2304.alohandes.negocio;

public interface VOPersona_Natural {
	/**
	 * @return tipoHoteleria
	 */
	public String getTipo_persona();

	/**
	 * @param tipoPersona - el nuevo tipo de persona natural 
	 */
	public void setTipo_persona(String tipoPersona);

	/**
	 * @return documento
	 */
	public String getDocumento();

	/**
	 * @param documento - el nuevo documento
	 */
	public void setDocumento(String documento);
}
