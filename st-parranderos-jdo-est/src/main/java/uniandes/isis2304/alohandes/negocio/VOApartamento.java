package uniandes.isis2304.alohandes.negocio;

public interface VOApartamento {
	/**
	 * @return area
	 */
	public double getArea();

	/**
	 * @param area - el area del apartamento
	 */
	public void setArea(double area);

	/**
	 * @return amoblado
	 */
	public int isAmoblado();

	/**
	 * @param amoblado - booleano que representa si esta amoblado
	 */
	public void setAmoblado(int amoblado);

	/**
	 * @return numeroHabitaciones
	 */
	public int getnumero_habitaciones();

	/**
	 * @param numeroHabitaciones - el numero de habitaciones del apartamento
	 */
	public void setNumero_habitaciones(int numero_habitaciones);
}
