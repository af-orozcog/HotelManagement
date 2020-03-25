package uniandes.isis2304.alohandes.negocio;

public interface VOCuarto {
	/**
	 * @return bañoPrivado
	 */
	public int isBañoPrivado();


	/**
	 * @param bañoPrivado - booleano del baño
	 */
	public void setBaño_Privado(int baño_privado);

	/**
	 * @return cuartoPrivado
	 */
	public int isCuarto_Privado();


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
