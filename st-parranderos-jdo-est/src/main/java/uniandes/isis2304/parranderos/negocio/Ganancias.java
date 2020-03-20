package uniandes.isis2304.parranderos.negocio;

public class Ganancias {
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 * identificador de las ganancias
	 */
	private long id;
	
	/**
	 * la cantidad de dinero que se hizo
	 */
	private long cantidad;
	
	/**
	 * el mes del año de la ganacia
	 */
	private int mes;
	
	/**
	 * el año en el cual se dio la ganacia
	 */
	private int año;

	/**
	 * el id del operador 
	 */
	private long idOperador;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
	 * método constructor sin argumentos
	 */
	public Ganancias() {
		this.id = 0;
	}
	
	/**
	 * Constructor con valores
	 * @param cantidad - cantidad de dinero que se registro ese mes
	 * @param mes - mes del año
	 * @param año - el año de la ganacia
	 * @param idOperador - el id del operador
	 */
	public Ganancias(long id, long cantidad, int mes, int año, long idOperador) {
		this.id = id;
		this.cantidad = cantidad;
		this.mes = mes;
		this.año = año;
		this.idOperador = idOperador;
	}

	/**
	 * @return cantidad de dinero
	 */
	public long getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad - cantidad de dinero
	 */
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return mes de la ganancia
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * @param mes - mes de la ganancia
	 */
	public void setMes(int mes) {
		this.mes = mes;
	}

	/**
	 * @return el año de la ganacia
	 */
	public int getAño() {
		return año;
	}

	/**
	 * @param año - año de la ganacia
	 */
	public void setAño(int año) {
		this.año = año;
	}

	/**
	 * @return idOperador
	 */
	public long getIdOperador() {
		return idOperador;
	}

	/**
	 * @param idOperador - el id del operador
	 */
	public void setIdOperador(long idOperador) {
		this.idOperador = idOperador;
	}

	/**
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id - el id de las ganacias
	 */
	public void setId(long id) {
		this.id = id;
	}

}
