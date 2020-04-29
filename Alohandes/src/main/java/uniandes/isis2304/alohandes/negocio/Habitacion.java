package uniandes.isis2304.alohandes.negocio;

import java.util.List;

public class Habitacion extends Vivienda implements VOHabitacion{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * el tipo de habitacion (ESTANDAR,SEMISUITE,SUITE)
	 */
	private String tipo_habitacion;
	
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
		this.tipo_habitacion = "";
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
		this.tipo_habitacion = tipoHabitacion;
		this.categoria = categoria;
		this.capacidad = capacidad;
		this.numero = numero;
	}

	/**
	 * @return tipoHabitacion
	 */
	public String getTipo_habitacion() {
		return tipo_habitacion;
	}

	/**
	 * @param tipoHabitacion - el tipo de habitacion
	 */
	public void setTipo_habitacion(String tipoHabitacion) {
		this.tipo_habitacion = tipoHabitacion;
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
	
	/**
	 * @return direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id - el id de la vivienda
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param direccion - la direccion de la vivienda
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return cupos
	 */
	public int getCupos() {
		return cupos;
	}

	/**
	 * @param cupos - los cupos de la vivienda
	 */
	public void setCupos(int cupos) {
		this.cupos = cupos;
	}

	/**
	 * @return ofertas 
	 */
	public List<Object[]> getOfertas() {
		return ofertas;
	}

	/**
	 * @param ofertas - las ofertas de la vivienda
	 */
	public void setOfertas(List<Object[]> ofertas) {
		this.ofertas = ofertas;
	}

	/**
	 * @return idOperador
	 */
	public long getOperador() {
		return operador;
	}

	/**
	 * @param idOperador - el identificador del operador
	 */
	public void setOperador(long Operador) {
		this.operador = Operador;
	}
	
}
