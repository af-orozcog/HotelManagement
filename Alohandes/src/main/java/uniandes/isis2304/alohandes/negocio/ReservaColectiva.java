package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class ReservaColectiva implements VOReservaColectiva{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 * identificador de la vivienda
	 */
	private long id;

	/**
	 * cantidad de reservas
	 */
	private Integer cantidad;
	
	/**
	 * fecha realización de la reserva colectiva
	 */
	private Timestamp fechaRealizacion;
	
	/**
	 * el identificador del usuario.
	 */
	private long cliente;
	
	/**
	 * reservas de la colectiva
	 */
	private List<Object[]> reservas;
	
	/* ****************************************************************
	 * 			MÃ©todos 
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	public ReservaColectiva() {
		this.cantidad = 1;
		this.fechaRealizacion = null;
		this.cliente = 0;
		this.reservas = new LinkedList<Object[]>();
	}

	/**
	 * Constructor con valores
	 * @param cantidad - cantidad de reservas
	 * @param fechaRealizacion - fecha de realización de la reserva
	 * @param idCliente - el cliente dueño de la reserva
	 */
	public ReservaColectiva(long id, Integer cantidad, Timestamp fechaRealizacion, long idCliente) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.fechaRealizacion = fechaRealizacion;
		this.cliente = idCliente;
	}

	/**
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id - el id de la reserva
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad - cantidad de la reserva colectiva
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return fechaRealizacion
	 */
	public Timestamp getFechaRealizacion() {
		return fechaRealizacion;
	}

	/**
	 * @param fechaRealizacion - fecha de realizacion de la reserva
	 */
	public void setFechaRealizacion(Timestamp fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	/**
	 * @return idCliente
	 */
	public long getCliente() {
		return cliente;
	}

	/**
	 * @param idCliente - el id del cliente de la reserva
	 */
	public void setCliente(long cliente) {
		this.cliente = cliente;
	}
	
	/**
	 * @return reservas
	 */
	public List<Object[]> getReservas() {
		return reservas;
	}

	/**
	 * @param reservas - las reservas de la colectiva
	 */
	public void setReservas(List<Object[]> reservas) {
		this.reservas = reservas;
	}
	
}
