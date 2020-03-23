package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;
import java.util.List;

public interface VOSeguro {
	/**
	 * @return id
	 */
	public long getId();

	/**
	 * @param id el id que se quiere poner
	 */
	public void setId(long id);

	/**
	 * @return empresa
	 */
	public String getEmpresa();

	/**
	 * @param empresa - la empresa que ofrece el seguro
	 */
	public void setEmpresa(String empresa);

	/**
	 * @return monto
	 */
	public int getMonto();

	/**
	 * @param monto - el monto del seguro
	 */
	public void setMonto(int monto);

	/**
	 * @return inicioSeguro
	 */
	public Timestamp getInicioSeguro();

	/**
	 * @param inicioSeguro - fecha de inicio del seguro
	 */
	public void setInicioSeguro(Timestamp inicioSeguro);

	/**
	 * @return finSeguro
	 */
	public Timestamp getFinSeguro();

	/**
	 * @param finSeguro - fecha de finalización del seguro
	 */
	public void setFinSeguro(Timestamp finSeguro);

	/**
	 * @return asegurados
	 */
	public List<Object[]> getAsegurados();

	/**
	 * @param asegurados - los asegurados
	 */
	public void setAsegurados(List<Object[]> asegurados);
}
