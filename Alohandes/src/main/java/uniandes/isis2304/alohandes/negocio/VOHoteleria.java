package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;

import oracle.sql.TIMESTAMP;

public interface VOHoteleria {
	/**
	 * @return tipoHoteleria
	 */
	public String getTipo_hoteleria();

	/**
	 * @param tipoHoteleria - el nuevo tipo de hoteleria 
	 */
	public void setTipo_hoteleria(String tipoHoteleria);

	/**
	 * @return horaApertura
	 */
	public TIMESTAMP getHora_apertura();

	/**
	 * @param horaApertura - la nueva hora de apertura
	 */
	public void setHora_apertura(TIMESTAMP horaApertura);

	/**
	 * @return horaCierre
	 */
	public TIMESTAMP getHora_cierre();

	/**
	 * @param horaCierre - la nueva hora de cierre
	 */
	public void setHora_cierre(TIMESTAMP horaCierre);
}
