package uniandes.isis2304.alohandes.negocio;

public class Apartamento extends Vivienda{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 * el area del apartamento
	 */
	private double area;
	
	/**
	 * booleano que representa si el apartamento esta amoblado
	 */
	private boolean amoblado;
	
	/**
	 * el numero de habitaciones del apartamento
	 */
	private int numeroHabitaciones;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	public Apartamento() {
		super();
		this.area = 0.0;
		this.amoblado = false;
		this.numeroHabitaciones = 0;
	}

	/**
	 * Constructor con valores
	 * @param area - el area del apartamento
	 * @param amoblado - booleano que representa si el apartamento esta amoblado
	 * @param numeroHabitaciones - el numero de habitaciones del apartamento
	 * @param direccion -  direccion de la vivienda
	 * @param cupos - cupos disponibles de la vivienda
	 * @param id - id de la vivienda
	 * @param idOperador - el operador dueño de la vivienda
	 */
	public Apartamento(long id, String direccion, int cupos, long idOperador, double area, boolean amoblado, int numeroHabitaciones) {
		super(id, direccion, cupos,idOperador);
		this.area = area;
		this.amoblado = amoblado;
		this.numeroHabitaciones = numeroHabitaciones;
	}

	/**
	 * @return area
	 */
	public double getArea() {
		return area;
	}

	/**
	 * @param area - el area del apartamento
	 */
	public void setArea(double area) {
		this.area = area;
	}

	/**
	 * @return amoblado
	 */
	public boolean isAmoblado() {
		return amoblado;
	}

	/**
	 * @param amoblado - booleano que representa si esta amoblado
	 */
	public void setAmoblado(boolean amoblado) {
		this.amoblado = amoblado;
	}

	/**
	 * @return numeroHabitaciones
	 */
	public int getNumeroHabitaciones() {
		return numeroHabitaciones;
	}

	/**
	 * @param numeroHabitaciones - el numero de habitaciones del apartamento
	 */
	public void setNumeroHabitaciones(int numeroHabitaciones) {
		this.numeroHabitaciones = numeroHabitaciones;
	}
}
