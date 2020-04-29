package uniandes.isis2304.alohandes.negocio;

import java.util.List;

public class Esporadico extends Apartamento implements VOEsporadico{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * numero de noches al año que se ha utilizado
	 */
	private int noches_anio;
	
	/**
	 * id del seguro
	 */
	private long seguro;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
     * Constructor por defecto
     */
	public Esporadico() 
    {
    	super();
    	this.noches_anio = 0;
    	this.seguro = 0;
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
	 * @param nochesAño - numero de noches del año
	 * @param idSeguro - id del seguro
	 */
	public Esporadico(long id, String direccion, int cupos, long idOperador, double area, int amoblado, 
			int numero_habitaciones, int noches_Anio, long idSeguro) {
		super(id, direccion, cupos, idOperador, area,amoblado,numero_habitaciones);
		this.noches_anio = noches_Anio;
		this.seguro = idSeguro;
	}

	/**
	 * @return nochesAño
	 */
	public int getNoches_Anio() {
		return noches_anio;
	}
	
	/**
	 * @param nochesAño - noches al año
	 */
	public void setNoches_anio(int noches_Anio) {
		this.noches_anio = noches_Anio;
	}

	/**
	 * @return idSeguro
	 */
	public long getSeguro() {
		return seguro;
	}

	/**
	 * @param idSeguro - id del seguro
	 */
	public void setSeguro(long idSeguro) {
		this.seguro = idSeguro;
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
	public int isAmoblado() {
		return amoblado;
	}

	/**
	 * @param amoblado - booleano que representa si esta amoblado
	 */
	public void setAmoblado(int amoblado) {
		this.amoblado = amoblado;
	}

	/**
	 * @return numeroHabitaciones
	 */
	public int getnumero_habitaciones() {
		return numero_habitaciones;
	}

	/**
	 * @param numeroHabitaciones - el numero de habitaciones del apartamento
	 */
	public void setNumero_habitaciones(int numero_habitaciones) {
		this.numero_habitaciones = numero_habitaciones;
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
