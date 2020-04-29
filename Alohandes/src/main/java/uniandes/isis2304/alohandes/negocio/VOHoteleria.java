package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;

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
	public Timestamp getHora_apertura();

	/**
	 * @param horaApertura - la nueva hora de apertura
	 */
	public void setHora_apertura(Timestamp horaApertura);

	/**
	 * @return horaCierre
	 */
	public Timestamp getHora_cierre();

	/**
	 * @param horaCierre - la nueva hora de cierre
	 */
	public void setHora_cierre(Timestamp horaCierre);
}
