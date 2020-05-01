package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;

public interface VOGanancias {
	/**
	 * @return cantidad de dinero
	 */
	public long getCantidad();

	/**
	 * @param cantidad - cantidad de dinero
	 */
	public void setCantidad(long cantidad);

	/**
	 * @return the fecha
	 */
	public Timestamp getFecha();

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Timestamp fecha);

	/**
	 * @return idOperador
	 */
	public long getOperador();

	/**
	 * @param idOperador - el id del operador
	 */
	public void setOperador(long idOperador);

	/**
	 * @return id
	 */
	public long getId();

	/**
	 * @param id - el id de las ganacias
	 */
	public void setId(long id);
}
