package uniandes.isis2304.alohandes.negocio;

import java.util.List;

public interface VOVivienda {
	
	/**
	 * 
	 * @return
	 */
	public String getDireccion();
	
	/**
	 * 
	 * @return
	 */
	public long getId();
	
	/**
	 * 
	 * @param id
	 */
	public void setId(long id);

	/**
	 * @param direccion - la direccion de la vivienda
	 */
	public void setDireccion(String direccion);

	/**
	 * @return cupos
	 */
	public int getCupos();

	/**
	 * @param cupos - los cupos de la vivienda
	 */
	public void setCupos(int cupos);

	/**
	 * @return ofertas 
	 */
	public List<Object[]> getOfertas();

	/**
	 * @param ofertas - las ofertas de la vivienda
	 */
	public void setOfertas(List<Object[]> ofertas);

	/**
	 * @return idOperador
	 */
	public long getIdOperador();

	/**
	 * @param idOperador - el identificador del operador
	 */
	public void setIdOperador(long idOperador);
}
