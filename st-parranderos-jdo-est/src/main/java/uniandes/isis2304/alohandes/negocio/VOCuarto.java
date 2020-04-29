package uniandes.isis2304.alohandes.negocio;

public interface VOCuarto {
	/**
	 * @return bañoPrivado
	 */
	public int isBanio_Privado();


	/**
	 * @param bañoPrivado - booleano del baño
	 */
	public void setBanio_privado(int banio_privado);

	/**
	 * @return cuartoPrivado
	 */
	public int isCuarto_privado();


	/**
	 * @param cuartoPrivado - booleano del cuarto privado
	 */
	public void setCuarto_privado(int cuarto_privado) ;


	/**
	 * @return esquema
	 */
	public String getEsquema();


	/**
	 * @param esquema - el esquema del cuarto
	 */
	public void setEsquema(String esquema);


	/**
	 * @return menaje
	 */
	public String getMenaje();


	/**
	 * @param menaje - el menaje del cuarto
	 */
	public void setMenaje(String menaje);
}
