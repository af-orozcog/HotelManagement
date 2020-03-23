package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;

public interface VOReserva {
	/**
	 * @return id
	 */
	public long getId();

	/**
	 * @param id - el id de la reserva
	 */
	public void setId(long id);

	/**
	 * @return inicio
	 */
	public Timestamp getInicio();

	/**
	 * @param inicio - el inicio de la reserva
	 */
	public void setInicio(Timestamp inicio);

	/**
	 * @return duracion
	 */
	public Timestamp getFin();

	/**
	 * @param duracion - la duracion de la reserva
	 */
	public void setFin(Timestamp fin);

	/**
	 * @return periodoArrendamiento
	 */
	public String getPeriodoArrendamiento();

	/**
	 * @param periodoArrendamiento - el String de arrendamiento de la reserva
	 */
	public void setPeriodoArrendamiento(String periodoArrendamiento);

	/**
	 * @return idUsuario
	 */
	public long getIdUsuario();

	/**
	 * @param idUsuario - el id del usuario de la reserva
	 */
	public void setIdUsuario(long idUsuario);
	
	/**
	 * @return idOferta
	 */
	public long getIdOferta();

	/**
	 * @param idOferta - el id de la oferta de la reserva
	 */
	public void setIdOferta(long idOferta);
}
