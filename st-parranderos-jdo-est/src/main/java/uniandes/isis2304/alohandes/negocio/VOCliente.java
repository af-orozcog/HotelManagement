package uniandes.isis2304.alohandes.negocio;

public interface VOCliente {
	/**
	 * @return tipoHoteleria
	 */
	public String getTipoCliente();

	/**
	 * @param tipoCliente - el nuevo tipo de persona natural 
	 */
	public void setTipoCliente(String tipoCliente);

	/**
	 * @return documento
	 */
	public String getDocumento();

	/**
	 * @param documento - el nuevo documento
	 */
	public void setDocumento(String documento);
}
