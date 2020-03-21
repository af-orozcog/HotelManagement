package uniandes.isis2304.parranderos.negocio;

import java.util.LinkedList;
import java.util.List;

public class Oferta {
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 * id de la oferta
	 */
	private long id;
	
	/**
	 * precio de la oferta
	 */
	private long precio;
	
	/**
	 * tipo de periodo de la oferta (DIAS, MESES, SEMESTRES)
	 */
	private String periodo;
	
	/**
	 * reservas de la oferta
	 */
	private List<Object []> reservas;
	
	/**
	 * servicios que ofrece la oferta
	 */
	private List<Object []> servicios;
	
	/**
	 * vivienda que ofrece la oferta
	 */
	private long idVivienda;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	public Oferta() {
		this.id = 0;
		this.precio = 0;
		this.periodo = "";
		this.reservas = new LinkedList<Object []> ();
		this.servicios = new LinkedList<Object []> ();
		this.idVivienda = 0;
	}

	/**
	 * Constructor con valores
	 * @param id
	 * @param precio
	 * @param periodo
	 * @param reservas
	 * @param servicios
	 * @param idVivienda
	 */
	public Oferta(long id, long precio, String periodo, long idVivienda) {
		this.id = id;
		this.precio = precio;
		this.periodo = periodo;
		this.reservas = new LinkedList<Object []> ();
		this.servicios = new LinkedList<Object []> ();
		this.idVivienda = idVivienda;
	}
	

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the precio
	 */
	public long getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(long precio) {
		this.precio = precio;
	}

	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return the reservas
	 */
	public List<Object[]> getReservas() {
		return reservas;
	}

	/**
	 * @param reservas the reservas to set
	 */
	public void setReservas(List<Object[]> reservas) {
		this.reservas = reservas;
	}

	/**
	 * @return the servicios
	 */
	public List<Object[]> getServicios() {
		return servicios;
	}

	/**
	 * @param servicios the servicios to set
	 */
	public void setServicios(List<Object[]> servicios) {
		this.servicios = servicios;
	}
	
	/**
	 * @return idVivienda
	 */
	public long getIdVivienda() {
		return idVivienda;
	}
	
	/**
	 * @param idVivienda - id de la vivienda de la oferta
	 */
	public void setIdVivienda(long idVivienda) {
		this.idVivienda = idVivienda;
	}
	
	
}
