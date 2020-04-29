package uniandes.isis2304.alohandes.negocio;

public interface VOCliente {
	/**
	 * @return tipoHoteleria
	 */
	public String getTipo_cliente();

	/**
	 * @param tipoCliente - el nuevo tipo de persona natural 
	 */
	public void setTipo_cliente(String tipoCliente);

	/**
	 * @return documento
	 */
	public String getDocumento();

	/**
	 * @param documento - el nuevo documento
	 */
	public void setDocumento(String documento);
}
