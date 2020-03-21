package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class Oferta {
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 * el identificador de la oferta
	 */
	private long id;
	
	/**
	 * el precio que tiene la oferta
	 */
	private long precio;
	
	/**
	 * el periodo de la oferta (DIAS,MESES,SEMESTRES)
	 */
	private String periodo;
	
	/**
	 * las reservas que se le han hecho a la oferta
	 */
	private List<Object []> reservas;
	
	/**
	 * los servicios que tiene la oferta
	 */
	private List<Object []> servicios;
	
	/**
	 * la fecha de inicio de la oferta
	 */
	private Timestamp fechaInicio;
	
	/**
	 * fecha final de la oferta
	 */
	private Timestamp fechaFin;
	
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
	}

	/**
	 * Constructor con valores
	 * @param id - id de la oferta
	 * @param precio - precio de la oferta
	 * @param periodo - periodo de la oferta
	 * @param fechaInicio - fecha de inicio de la oferta
	 * @param fechaFin - fecha en la que se acaba la oferta
	 */
	public Oferta(long id, long precio, String periodo,
			Timestamp fechaInicio, Timestamp fechaFin) {
		super();
		this.id = id;
		this.precio = precio;
		this.periodo = periodo;
		this.reservas = new LinkedList<Object []> ();
		this.servicios = new LinkedList<Object []> ();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
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
	
	
}
