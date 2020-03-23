package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;

public class Hoteleria extends Operador implements VOHoteleria{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 * tipo de hoteleria (HOSTAL, HOTEL)
	 */
	private String tipoHoteleria;
	
	/**
	 * hora de apertura del hotel
	 */
	private Timestamp horaApertura;
	
	/**
	 * hora de cierre del hotel
	 */
	private Timestamp horaCierre;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
     * Constructor por defecto
     */
	public Hoteleria() {
		super();
		this.tipoHoteleria = "";
		this.horaApertura = null;
		this.horaCierre = null;
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
		this.tipoHoteleria = tipoHoteleria;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
	}

	/**
	 * @return tipoHoteleria
	 */
	public String getTipoHoteleria() {
		return tipoHoteleria;
	}

	/**
	 * @param tipoHoteleria - el nuevo tipo de hoteleria 
	 */
	public void setTipoHoteleria(String tipoHoteleria) {
		this.tipoHoteleria = tipoHoteleria;
	}

	/**
	 * @return horaApertura
	 */
	public Timestamp getHoraApertura() {
		return horaApertura;
	}

	/**
	 * @param horaApertura - la nueva hora de apertura
	 */
	public void setHoraApertura(Timestamp horaApertura) {
		this.horaApertura = horaApertura;
	}

	/**
	 * @return horaCierre
	 */
	public Timestamp getHoraCierre() {
		return horaCierre;
	}

	/**
	 * @param horaCierre - la nueva hora de cierre
	 */
	public void setHoraCierre(Timestamp horaCierre) {
		this.horaCierre = horaCierre;
	}
	
}
