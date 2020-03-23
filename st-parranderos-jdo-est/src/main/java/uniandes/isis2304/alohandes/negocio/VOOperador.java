package uniandes.isis2304.alohandes.negocio;

import java.util.List;

public interface VOOperador {
	/**
	 * @return las viviendas del operador
	 */
	public List<Object[]> getViviendas();

	/**
	 * @param viviendas - la nueva lsita de ganacias del operador
	 */
	public void setViviendas(List<Object[]> viviendas);

	/**
	 * @return las ganacias del operador
	 */
	public List<Object[]> getGanancias();

	/**
	 * @param ganancias - la nueva lista de ganacias del operador
	 */
	public void setGanancias(List<Object[]> ganancias);
}
