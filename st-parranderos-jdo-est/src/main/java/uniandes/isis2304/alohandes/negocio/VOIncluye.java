package uniandes.isis2304.alohandes.negocio;

public interface VOIncluye {
	/**
	 * @return El idOferta
	 */
	public long getIdOferta();

	/**
	 * @param idOferta - El nuevo identificador de oferta. Debe existir un oferta con dicho identificador
	 */
	public void setIdOferta(long idOferta);

	/**
	 * @return El idServicio
	 */
	public long getIdServicio();

	/**
	 * @param idServicio - El nuevo identificador de servicio. Debe existir una servicio con dicho identificador
	 */
	public void setIdServicio(long idServicio);

	/**
	 * @return Si el servicio esta incluido en el precio
	 */
	public boolean getIncluido();

	/**
	 * @param incluido - La nueva condicion del servicio con respecto a si esta incluido
	 */
	public void setIncluido(boolean incluido);
}
