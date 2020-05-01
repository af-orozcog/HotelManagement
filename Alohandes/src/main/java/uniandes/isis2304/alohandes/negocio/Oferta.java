package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class Oferta implements VOOferta{
	
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
	 * el periodo de la oferta (DIAS,SEMANAS,MESES,SEMESTRES)
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
	 * vivienda que ofrece la oferta
	 */
	private long vivienda;

	/**
	 * la fecha de inicio de la oferta
	 */
	private Timestamp fechainicio;
	
	/**
	 * fecha final de la oferta
	 */
	private Timestamp fechafin;
	
	/**
	 * si la oferta esta habilitada
	 */
	private Integer habilitada;
	
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
		this.vivienda = 0;
		this.habilitada = 1;
	}

	/**
	 * Constructor con valores
	 * @param id - id de la oferta
	 * @param precio - precio de la oferta
	 * @param periodo - periodo de la oferta
	 * @param fechaInicio - fecha de inicio de la oferta
	 * @param fechaFin - fecha en la que se acaba la oferta
	 * @param vivienda - id de la vivienda de la oferta
	 */
	public Oferta(long id, long precio, String periodo,
			Timestamp fechaInicio, Timestamp fechaFin, long vivienda) {
		super();
		this.id = id;
		this.precio = precio;
		this.periodo = periodo;
		this.reservas = new LinkedList<Object []> ();
		this.servicios = new LinkedList<Object []> ();
		this.vivienda = vivienda;
		this.fechainicio = fechaInicio;
		this.fechafin = fechaFin;
		this.habilitada = 1;
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
	public long getVivienda() {
		return vivienda;
	}
	
	/**
	 * @param idVivienda - id de la vivienda de la oferta
	 */
	public void setVivienda(long vivienda) {
		this.vivienda = vivienda;
	}

	
	/**
	 * @return fechaInicio
	 */
	public Timestamp getFechainicio() {
		return fechainicio;
	}

	/**
	 * @param fechaInicio - fecha de inicio de la oferta
	 */
	public void setFechainicio(Timestamp fechainicio) {
		this.fechainicio = fechainicio;
	}

	/**
	 * @return fechaFin
	 */
	public Timestamp getFechafin() {
		return fechafin;
	}

	/**
	 * @param fechaFin - fecha de fin de la oferta
	 */
	public void setFechafin(Timestamp fechafin) {
		this.fechafin = fechafin;
	}
	
	/**
	 * @return habilitada
	 */
	public Integer getHabilitada() {
		return habilitada;
	}
	
	/**
	 * @param habilitada - si la oferta est� habilitada
	 */
	public void setHabilitada(Integer habilitada) {
		this.habilitada = habilitada;
	}
	
}
