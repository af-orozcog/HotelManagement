package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;

public interface VOHoteleria {
	/**
	 * @return tipoHoteleria
	 */
	public String getTipoHoteleria();

	/**
	 * @param tipoHoteleria - el nuevo tipo de hoteleria 
	 */
	public void setTipoHoteleria(String tipoHoteleria);

	/**
	 * @return horaApertura
	 */
	public Timestamp getHoraApertura();

	/**
	 * @param horaApertura - la nueva hora de apertura
	 */
	public void setHoraApertura(Timestamp horaApertura);

	/**
	 * @return horaCierre
	 */
	public Timestamp getHoraCierre();

	/**
	 * @param horaCierre - la nueva hora de cierre
	 */
	public void setHoraCierre(Timestamp horaCierre);
}
