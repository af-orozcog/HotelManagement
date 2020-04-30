package uniandes.isis2304.alohandes.negocio;

public interface VOHabitacion {
	
	/**
	 * 
	 * @return
	 */
	public String getTipo_habitacion();

	/**
	 * @param tipoHabitacion - el tipo de habitacion
	 */
	public void setTipo_habitacion(String tipoHabitacion);

	/**
	 * @return the categoria
	 */
	public String getCategoria();

	/**
	 * @param categoria - la categoria de la habitacion
	 */
	public void setCategoria(String categoria);

	/**
	 * @return capacidad
	 */
	public int getCapacidad();

	/**
	 * @param capacidad - la capacidad de la habitacion
	 */
	public void setCapacidad(int capacidad);

	/**
	 * @return numero
	 */
	public int getNumero();

	/**
	 * @param numero - el numero de la habitacion
	 */
	public void setNumero(int numero);
}
