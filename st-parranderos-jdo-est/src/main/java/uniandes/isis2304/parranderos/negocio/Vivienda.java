package uniandes.isis2304.parranderos.negocio;

import java.util.LinkedList;
import java.util.List;

public class Vivienda {

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * identificador de la vivienda
	 */
	private long id;
	
	/**
	 * la direccion de la vivienda
	 */
	private String direccion;
	
	/**
	 * los cupos de la vivienda
	 */
	private int cupos;
	
	/**
	 * las ofertas de la vivienda
	 */
	private List<Object []> ofertas;
	
	/**
	 * el identificador del operador
	 */
	private long idOperador;
	
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
		this.idOperador = 0;
	}

	/**
	 * Constructor con valores
	 * @param direccion -  direccion de la vivienda
	 * @param cupos - cupos disponibles de la vivienda
	 * @param idOperador - el operador dueño de la vivienda
	 */
	public Vivienda(long id, String direccion, int cupos, long idOperador) {
		this.id = id;
		this.direccion = direccion;
		this.cupos = cupos;
		this.ofertas = new LinkedList<Object []> ();
		this.idOperador = idOperador;
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
	public long getIdOperador() {
		return idOperador;
	}

	/**
	 * @param idOperador - el identificador del operador
	 */
	public void setIdOperador(long idOperador) {
		this.idOperador = idOperador;
	}
	
	
}
