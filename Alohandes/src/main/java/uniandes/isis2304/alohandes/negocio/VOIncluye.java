package uniandes.isis2304.alohandes.negocio;

public interface VOIncluye {
	/**
	 * @return El idOferta
	 */
	public long getOferta();

	/**
	 * @param idOferta - El nuevo identificador de oferta. Debe existir un oferta con dicho identificador
	 */
	public void setOferta(long idOferta);

	/**
	 * @return El idServicio
	 */
	public long getServicio();

	/**
	 * @param idServicio - El nuevo identificador de servicio. Debe existir una servicio con dicho identificador
	 */
	public void setServicio(long idServicio);

	/**
	 * @return Si el servicio esta incluido en el precio
	 */
	public int getIncluido();

	/**
	 * @param incluido - La nueva condicion del servicio con respecto a si esta incluido
	 */
	public void setIncluido(int incluido);
}
