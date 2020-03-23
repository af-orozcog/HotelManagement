package uniandes.isis2304.alohandes.negocio;

public interface VOCuarto {
	/**
	 * @return bañoPrivado
	 */
	public boolean isBañoPrivado();


	/**
	 * @param bañoPrivado - booleano del baño
	 */
	public void setBañoPrivado(boolean bañoPrivado);


	/**
	 * @return cuartoPrivado
	 */
	public boolean isCuartoPrivado();


	/**
	 * @param cuartoPrivado - booleano del cuarto privado
	 */
	public void setCuartoPrivado(boolean cuartoPrivado);


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
