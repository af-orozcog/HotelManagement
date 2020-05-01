package uniandes.isis2304.alohandes.negocio;

import java.util.LinkedList;
import java.util.List;

public class Vivienda implements VOVivienda{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * identificador de la vivienda
	 */
	protected long id;
	
	/**
	 * la direccion de la vivienda
	 */
	protected String direccion;
	
	/**
	 * los cupos de la vivienda
	 */
	protected int cupos;
	
	/**
	 * las ofertas de la vivienda
	 */
	protected List<Object []> ofertas;
	
	/**
	 * el identificador del operador
	 */
	protected long operador;
	
	
	/**
	 * El tipo de vivienda;
	 */
	protected String tipo;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	public Vivienda() {
		this.direccion = "";
		this.cupos = 0;
		ofertas = new LinkedList<Object []> ();
		this.operador = 0;
	}

	/**
	 * Constructor con valores
	 * @param direccion -  direccion de la vivienda
	 * @param cupos - cupos disponibles de la vivienda
	 * @param idOperador - el operador dueño de la vivienda
	 */
	public Vivienda(long id, String direccion, int cupos, long idOperador, String tipo) {
		this.id = id;
		this.direccion = direccion;
		this.cupos = cupos;
		this.ofertas = new LinkedList<Object []> ();
		this.operador = idOperador;
		this.tipo = tipo;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
