package uniandes.isis2304.alohandes.negocio;

public interface VOServicio {
	/**
	 * @return id
	 */
	public long getId();

	/**
	 * @param id el id que se quiere poner
	 */
	public void setId(long id);

	/**
	 * @return nombre
	 */
	public String getNombre();

	/**
	 * @param nombre - la nombre que ofrece el servicio
	 */
	public void setNombre(String nombre);

	/**
	 * @return costo
	 */
	public long getCosto();

	/**
	 * @param costo - el costo del servicio
	 */
	public void setCosto(long costo);
}
