package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;
import java.util.List;

public interface VOReservaColectiva {

	/**
	 * @return cantidad
	 */
	public Integer getCantidad();

	/**
	 * @param cantidad - cantidad de la reserva colectiva
	 */
	public void setCantidad(Integer cantidad);

	/**
	 * @return fechaRealizacion
	 */
	public Timestamp getFechaRealizacion();
	
	/**
	 * @param fechaRealizacion - fecha de realizacion de la reserva
	 */
	public void setFechaRealizacion(Timestamp fechaRealizacion);
	
	/**
	 * @return idCliente
	 */
	public long getCliente();
	
	/**
	 * @param idCliente - el id del cliente de la reserva
	 */
	public void setCliente(long cliente);
	
	/**
	 * @return reservas
	 */
	public List<Object[]> getReservas();

	/**
	 * @param reservas - las reservas de la colectiva
	 */
	public void setReservas(List<Object[]> reservas);
}
