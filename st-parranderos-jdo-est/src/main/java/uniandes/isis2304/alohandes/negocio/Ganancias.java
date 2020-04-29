package uniandes.isis2304.alohandes.negocio;

public class Ganancias implements VOGanancias{
	
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
	private int anio;

	/**
	 * el id del operador 
	 */
	private long operador;
	
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
	public Ganancias(long id, long cantidad, int mes, int anio, long idOperador) {
		this.id = id;
		this.cantidad = cantidad;
		this.mes = mes;
		this.anio = anio;
		this.operador = idOperador;
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
	public int getAnio() {
		return anio;
	}

	/**
	 * @param año - año de la ganacia
	 */
	public void setAnio(int anio) {
		this.anio = anio;
	}

	/**
	 * @return idOperador
	 */
	public long getIdOperador() {
		return operador;
	}

	/**
	 * @param idOperador - el id del operador
	 */
	public void setIdOperador(long idOperador) {
		this.operador = idOperador;
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
