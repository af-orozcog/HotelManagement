package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class Oferta {
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
<<<<<<< HEAD
	 * id de la oferta
=======
	 * el identificador de la oferta
>>>>>>> 6bd8015097fe6edaa7cea5b60a28b27daa027006
	 */
	private long id;
	
	/**
<<<<<<< HEAD
	 * precio de la oferta
=======
	 * el precio que tiene la oferta
>>>>>>> 6bd8015097fe6edaa7cea5b60a28b27daa027006
	 */
	private long precio;
	
	/**
<<<<<<< HEAD
	 * tipo de periodo de la oferta (DIAS, MESES, SEMESTRES)
=======
	 * el periodo de la oferta (DIAS,MESES,SEMESTRES)
>>>>>>> 6bd8015097fe6edaa7cea5b60a28b27daa027006
	 */
	private String periodo;
	
	/**
<<<<<<< HEAD
	 * reservas de la oferta
=======
	 * las reservas que se le han hecho a la oferta
>>>>>>> 6bd8015097fe6edaa7cea5b60a28b27daa027006
	 */
	private List<Object []> reservas;
	
	/**
<<<<<<< HEAD
	 * servicios que ofrece la oferta
=======
	 * los servicios que tiene la oferta
>>>>>>> 6bd8015097fe6edaa7cea5b60a28b27daa027006
	 */
	private List<Object []> servicios;
	
	/**
<<<<<<< HEAD
	 * vivienda que ofrece la oferta
	 */
	private long idVivienda;
=======
	 * la fecha de inicio de la oferta
	 */
	private Timestamp fechaInicio;
	
	/**
	 * fecha final de la oferta
	 */
	private Timestamp fechaFin;
>>>>>>> 6bd8015097fe6edaa7cea5b60a28b27daa027006
	
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
<<<<<<< HEAD
	 * @param id
	 * @param precio
	 * @param periodo
	 * @param reservas
	 * @param servicios
	 * @param idVivienda
	 */
	public Oferta(long id, long precio, String periodo, long idVivienda) {
=======
	 * @param id - id de la oferta
	 * @param precio - precio de la oferta
	 * @param periodo - periodo de la oferta
	 * @param fechaInicio - fecha de inicio de la oferta
	 * @param fechaFin - fecha en la que se acaba la oferta
	 */
	public Oferta(long id, long precio, String periodo,
			Timestamp fechaInicio, Timestamp fechaFin) {
		super();
>>>>>>> 6bd8015097fe6edaa7cea5b60a28b27daa027006
		this.id = id;
		this.precio = precio;
		this.periodo = periodo;
		this.reservas = new LinkedList<Object []> ();
		this.servicios = new LinkedList<Object []> ();
<<<<<<< HEAD
		this.idVivienda = idVivienda;
=======
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
>>>>>>> 6bd8015097fe6edaa7cea5b60a28b27daa027006
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
