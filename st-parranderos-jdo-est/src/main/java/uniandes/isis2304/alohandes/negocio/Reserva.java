package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Reserva {

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 * identificador de la vivienda
	 */
	private long id;

	/**
	 * inicio de la reserva
	 */
	private Timestamp inicio;
	
	/**
	 * duracion de la reserva
	 */
	private Timestamp fin;
	
	/**
	 * tipo de periodo de arrendamiento (DIAS, MESES, SEMESTRES)
	 */
	private String periodoArrendamiento;
	
	/**
	 * el identificador del usuario.
	 */
	private long idUsuario;
	
	/**
	 * el identificador de la oferta.
	 */
	private long idOferta;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	public Reserva() {
		this.inicio = null;
		this.fin = null;
		this.periodoArrendamiento = "";
		this.idUsuario = 0;
		this.idOferta = 0;
	}

	/**
	 * Constructor con valores
	 * @param inicio - el inico de la reserva
	 * @param duracion - duracion de la reserva
	 * @param periodoArrendamiento - String de arrendamiento de la reserva
	 * @param idUsuario - el usuario dueño de la reserva
	 * @param idOferta - la oferta de la reserva
	 */
	public Reserva(long id, Timestamp inicio, Timestamp fin, String periodoArrendamiento, long idUsuario,
			long idOferta) {
		super();
		this.id = id;
		this.inicio = inicio;
		this.fin = fin;
		this.periodoArrendamiento = periodoArrendamiento;
		this.idUsuario = idUsuario;
		this.idOferta = idOferta;
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
	 * @return inicio
	 */
	public Timestamp getInicio() {
		return inicio;
	}

	/**
	 * @param inicio - el inicio de la reserva
	 */
	public void setInicio(Timestamp inicio) {
		this.inicio = inicio;
	}

	/**
	 * @return duracion
	 */
	public Timestamp getFin() {
		return fin;
	}

	/**
	 * @param duracion - la duracion de la reserva
	 */
	public void setFin(Timestamp fin) {
		this.fin = fin;
	}

	/**
	 * @return periodoArrendamiento
	 */
	public String getPeriodoArrendamiento() {
		return periodoArrendamiento;
	}

	/**
	 * @param periodoArrendamiento - el String de arrendamiento de la reserva
	 */
	public void setPeriodoArrendamiento(String periodoArrendamiento) {
		this.periodoArrendamiento = periodoArrendamiento;
	}

	/**
	 * @return idUsuario
	 */
	public long getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario - el id del usuario de la reserva
	 */
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	/**
	 * @return idOferta
	 */
	public long getIdOferta() {
		return idOferta;
	}

	/**
	 * @param idOferta - el id de la oferta de la reserva
	 */
	public void setIdOferta(long idOferta) {
		this.idOferta = idOferta;
	}
	
}
