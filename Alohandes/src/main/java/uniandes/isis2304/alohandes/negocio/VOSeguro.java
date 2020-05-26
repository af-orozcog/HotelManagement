package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;
import java.util.List;

import oracle.sql.DATE;

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
	public DATE getInicio_seguro();

	/**
	 * @param inicioSeguro - fecha de inicio del seguro
	 */
	public void setInicio_seguro(DATE inicioSeguro);

	/**
	 * @return finSeguro
	 */
	public DATE getFin_seguro();

	/**
	 * @param finSeguro - fecha de finalización del seguro
	 */
	public void setFin_seguro(DATE finSeguro);

	/**
	 * @return asegurados
	 */
	public List<Object[]> getAsegurados();

	/**
	 * @param asegurados - los asegurados
	 */
	public void setAsegurados(List<Object[]> asegurados);
}
