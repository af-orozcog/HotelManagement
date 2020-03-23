package uniandes.isis2304.alohandes.negocio;

import java.util.LinkedList;
import java.util.List;

public class Operador extends Usuario{
	
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Las ganacias del operador
	 */
	private List<Object []> ganancias;
	
	/**
	 * las viviendas del operador
	 */
	private List<Object []> viviendas;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
     * Constructor por defecto
     */
	public Operador() 
    {
    	super();
    	ganancias = new LinkedList<Object []> ();
    	viviendas = new LinkedList<Object []> ();
	}
	
	/**
	 * Constructor con valores
	 * @param id - el id del usuario
	 * @param nombre - el nombre del usuario
	 * @param email - la dirección de correo del usuario
	 * @param numero - el numero telefonico del usuario
	 */
	public Operador(long id, String nombre, String email, String numero) {
		super(id,nombre,email,numero);
		ganancias = new LinkedList<Object []>();
		viviendas = new LinkedList<Object []>();
	}

	/**
	 * @return las viviendas del operador
	 */
	public List<Object[]> getViviendas() {
		return viviendas;
	}

	/**
	 * @param viviendas - la nueva lsita de ganacias del operador
	 */
	public void setViviendas(List<Object[]> viviendas) {
		this.viviendas = viviendas;
	}

	/**
	 * @return las ganacias del operador
	 */
	public List<Object[]> getGanancias() {
		return ganancias;
	}

	/**
	 * @param ganancias - la nueva lista de ganacias del operador
	 */
	public void setGanancias(List<Object[]> ganancias) {
		this.ganancias = ganancias;
	}
	
}
