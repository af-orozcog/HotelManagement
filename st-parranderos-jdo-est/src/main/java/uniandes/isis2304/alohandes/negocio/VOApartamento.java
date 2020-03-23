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
	public boolean isAmoblado();

	/**
	 * @param amoblado - booleano que representa si esta amoblado
	 */
	public void setAmoblado(boolean amoblado);

	/**
	 * @return numeroHabitaciones
	 */
	public int getNumeroHabitaciones();

	/**
	 * @param numeroHabitaciones - el numero de habitaciones del apartamento
	 */
	public void setNumeroHabitaciones(int numeroHabitaciones);
}
