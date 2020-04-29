package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;

public class Hoteleria extends Operador implements VOHoteleria{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 * tipo de hoteleria (HOSTAL, HOTEL)
	 */
	private String tipo_hoteleria;
	
	/**
	 * hora de apertura del hotel
	 */
	private Timestamp hora_apertura;
	
	/**
	 * hora de cierre del hotel
	 */
	private Timestamp hora_cierre;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
     * Constructor por defecto
     */
	public Hoteleria() {
		super();
		this.tipo_hoteleria = "";
		this.hora_apertura = null;
		this.hora_cierre = null;
	}
	
	/**
	 * Constructor con valores
	 * @param id - el id de la hoteleria
	 * @param nombre - nombre de la hoteleria
	 * @param email - la dirección de correo del usuario
	 * @param numero - el numero telefonico del usuario
	 * @param tipoHoteleria - el tipo de hotel
	 * @param horaApertura - la hora de apertura
	 * @param horaCierre - la hora de cierre
	 */
	public Hoteleria(long id, String nombre, String email, String numero, String tipoHoteleria,
			Timestamp horaApertura, Timestamp horaCierre) {
		super(id,nombre,email,numero,"HOTELERIA");
		this.tipo_hoteleria = tipoHoteleria;
		this.hora_apertura = horaApertura;
		this.hora_cierre = horaCierre;
	}

	/**
	 * @return tipoHoteleria
	 */
	public String getTipoHoteleria() {
		return tipo_hoteleria;
	}

	/**
	 * @param tipoHoteleria - el nuevo tipo de hoteleria 
	 */
	public void setTipoHoteleria(String tipoHoteleria) {
		this.tipo_hoteleria = tipoHoteleria;
	}

	/**
	 * @return horaApertura
	 */
	public Timestamp getHoraApertura() {
		return hora_apertura;
	}

	/**
	 * @param horaApertura - la nueva hora de apertura
	 */
	public void setHoraApertura(Timestamp horaApertura) {
		this.hora_apertura = horaApertura;
	}

	/**
	 * @return horaCierre
	 */
	public Timestamp getHoraCierre() {
		return hora_cierre;
	}

	/**
	 * @param horaCierre - la nueva hora de cierre
	 */
	public void setHoraCierre(Timestamp horaCierre) {
		this.hora_cierre = horaCierre;
	}
	
}
