package uniandes.isis2304.alohandes.negocio;

public interface VOGanancias {
	/**
	 * @return cantidad de dinero
	 */
	public long getCantidad();

	/**
	 * @param cantidad - cantidad de dinero
	 */
	public void setCantidad(long cantidad);

	/**
	 * @return mes de la ganancia
	 */
	public int getMes();

	/**
	 * @param mes - mes de la ganancia
	 */
	public void setMes(int mes);

	/**
	 * @return el año de la ganacia
	 */
	public int getAño();

	/**
	 * @param año - año de la ganacia
	 */
	public void setAño(int año);

	/**
	 * @return idOperador
	 */
	public long getIdOperador();

	/**
	 * @param idOperador - el id del operador
	 */
	public void setIdOperador(long idOperador);

	/**
	 * @return id
	 */
	public long getId();

	/**
	 * @param id - el id de las ganacias
	 */
	public void setId(long id);
}
