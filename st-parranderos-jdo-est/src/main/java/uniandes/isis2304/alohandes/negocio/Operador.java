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
	private String tipo_Operador;
	
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
    	tipo_Operador = "";
	}
	
	/**
	 * Constructor con valores
	 * @param id - el id del usuario
	 * @param nombre - el nombre del usuario
	 * @param email - la dirección de correo del usuario
	 * @param numero - el numero telefonico del usuario
	 * @param tipoOperador - el tipo de operador que es
	 */
	public Operador(long id, String nombre, String email, String numero, String tipo_Operador) {
		super(id,nombre,email,numero);
		ganancias = new LinkedList<Object []>();
		viviendas = new LinkedList<Object []>();
		this.tipo_Operador = tipo_Operador;
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
	public String getTipo_Operador() {
		return tipo_Operador;
	}

	/**
	 * @param tipoOperador - el nuevo tipo operador
	 */
	public void setTipo_Operador(String tipo_Operador) {
		this.tipo_Operador = tipo_Operador;
	}
	
	@Override
	public long getId() {
		return id;
	}

	/**
	 * @param id - el nuevo id del usuario
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return el nombre del usuario
	 */
	@Override
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre - el nuevo nombre del usuario
	 */
	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return la dirección de correo del usuario
	 */
	@Override
	public String getEmail() {
		return email;
	}

	/**
	 * @param email - la nueva dirección de correo del usuario
	 */
	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return el numero del usuario
	 */
	@Override
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero - el nuevo numero del usuario
	 */
	@Override
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
}
