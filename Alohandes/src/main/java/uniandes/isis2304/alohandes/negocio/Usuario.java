package uniandes.isis2304.alohandes.negocio;

public class Usuario {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO del usuario
	 */
	protected long id;
	
	/**
	 * el nombre del usuario
	 */
	protected String nombre;
	
	/**
	 * la dirección de correo del usuario
	 */
	protected String email;
	
	/**
	 * el numero telefonico del usuario
	 */
	protected String numero;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Usuario() 
    {
    	this.id = 0;
		this.nombre = "";
		this.email = "";
		this.numero = "";
	}
	
	
	/**
	 * Constructor con valores
	 * @param id - el id del usuario
	 * @param nombre - el nombre del usuario
	 * @param email - la dirección de correo del usuario
	 * @param numero - el numero telefonico del usuario
	 */
	public Usuario(long id, String nombre, String email, String numero) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.numero = numero;
	}


	/**
	 * @return el id del usuario
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id - el nuevo id del usuario
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return el nombre del usuario
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre - el nuevo nombre del usuario
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return la dirección de correo del usuario
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email - la nueva dirección de correo del usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return el numero del usuario
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero - el nuevo numero del usuario
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
}
