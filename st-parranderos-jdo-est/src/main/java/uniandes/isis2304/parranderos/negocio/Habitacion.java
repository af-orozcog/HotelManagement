package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public class Habitacion extends Vivienda{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * el tipo de habitacion (ESTANDAR,SEMISUITE,SUITE)
	 */
	private String tipoHabitacion;
	
	/**
	 * la categoria de la habitación
	 */
	private String categoria;
	
	/**
	 * la capacidad de la habitacion
	 */
	private int capacidad;
	
	/**
	 * el numero de la habitacion
	 */
	private int numero;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	public Habitacion() {
		super();
		this.tipoHabitacion = "";
		this.categoria = "";
		this.numero = 0;
		this.capacidad = 0;
	}

	/**
	 * Constructor con valores
	 * @param tipoHabitacion -  el tipo de habitacion (ESTANDAR,SEMISUITE,SUITE)
	 * @param categoria - la categoria de la habitación
	 * @param capacidad - la capacidad de la habitacion
	 * @param numero - el numero de la habitacion
	 * @param direccion -  direccion de la vivienda
	 * @param cupos - cupos disponibles de la vivienda
	 * @param idOperador - el operador dueño de la vivienda
	 */
	public Habitacion(long id, String direccion, int cupos, long idOperador, String tipoHabitacion, String categoria, int capacidad, int numero) {
		super(id, direccion, cupos,idOperador);
		this.tipoHabitacion = tipoHabitacion;
		this.categoria = categoria;
		this.capacidad = capacidad;
		this.numero = numero;
	}

	/**
	 * @return tipoHabitacion
	 */
	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	/**
	 * @param tipoHabitacion - el tipo de habitacion
	 */
	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria - la categoria de la habitacion
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return capacidad
	 */
	public int getCapacidad() {
		return capacidad;
	}

	/**
	 * @param capacidad - la capacidad de la habitacion
	 */
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	/**
	 * @return numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param numero - el numero de la habitacion
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
	
}
