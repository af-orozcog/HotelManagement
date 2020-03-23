package uniandes.isis2304.alohandes.negocio;

import java.util.LinkedList;
import java.util.List;

public class Operador extends Usuario implements VOOperador{
	
	
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
	/**
	 * tipo de operador (HOTELERIA, VIVIENDA_UNIVERSITARIA, PERSONA_NATURAL)
	 */
	private String tipoOperador;
	
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
    	tipoOperador = "";
	}
	
	/**
	 * Constructor con valores
	 * @param id - el id del usuario
	 * @param nombre - el nombre del usuario
	 * @param email - la dirección de correo del usuario
	 * @param numero - el numero telefonico del usuario
	 * @param tipoOperador - el tipo de operador que es
	 */
	public Operador(long id, String nombre, String email, String numero, String tipoOperador) {
		super(id,nombre,email,numero);
		ganancias = new LinkedList<Object []>();
		viviendas = new LinkedList<Object []>();
		this.tipoOperador = tipoOperador;
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

	/**
	 * @return el tipo de operador
	 */
	public String getTipoOperador() {
		return tipoOperador;
	}

	/**
	 * @param tipoOperador - el nuevo tipo operador
	 */
	public void setTipoOperador(String tipoOperador) {
		this.tipoOperador = tipoOperador;
	}
	
}
