package uniandes.isis2304.parranderos.negocio;

public class Ganancias {
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
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

	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
	 * método constructor sin argumentos
	 */
	public Ganancias() {
		
	}
	
	/**
	 * Constructor con valores
	 * @param cantidad - cantidad de dinero que se registro ese mes
	 * @param mes - mes del año
	 * @param año - el año de la ganacia
	 */
	public Ganancias(long cantidad, int mes, int año) {
		this.cantidad = cantidad;
		this.mes = mes;
		this.año = año;
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
	
}
