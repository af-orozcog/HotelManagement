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
	public String getPeriodo_arrendamiento();

	/**
	 * @param periodoArrendamiento - el String de arrendamiento de la reserva
	 */
	public void setPeriodo_arrendamiento(String periodo_arrendamiento);

	/**
	 * @return idUsuario
	 */
	public long getCliente();

	/**
	 * @param idUsuario - el id del usuario de la reserva
	 */
	public void setCliente(long cliente);
	
	/**
	 * @return idOferta
	 */
	public long getOferta();

	/**
	 * @param idOferta - el id de la oferta de la reserva
	 */
	public void setOferta(long Oferta);
	
	/**
	 * @return reservaColectiva
	 */
	public long getColectiva();

	/**
	 * @param colectiva the colectiva to set
	 */
	public void setColectiva(long colectiva);
}
