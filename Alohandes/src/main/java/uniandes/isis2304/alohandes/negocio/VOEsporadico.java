package uniandes.isis2304.alohandes.negocio;

public interface VOEsporadico {
	/**
	 * @return nochesAño
	 */
	public int getNoches_Anio();

	/**
	 * @param nochesAño - noches al año
	 */
	public void setNoches_anio(int noches_Anio);

	/**
	 * @return idSeguro
	 */
	public long getSeguro();

	/**
	 * @param idSeguro - id del seguro
	 */
	public void setSeguro(long idSeguro);
}
