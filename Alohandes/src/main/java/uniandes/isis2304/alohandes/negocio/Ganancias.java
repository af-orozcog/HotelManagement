package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;

import oracle.sql.DATE;

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
	 * 
	 */
	private DATE fecha;

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
		this.cantidad = 0;
		this.fecha = null;
		this.operador = 0;
	}
	
	/**
	 * Constructor con valores
	 * @param cantidad - cantidad de dinero que se registro ese mes
	 * @param mes - mes del año
	 * @param año - el año de la ganacia
	 * @param idOperador - el id del operador
	 */
	public Ganancias(long id, long cantidad, DATE fecha, long operador) {
		this.id = id;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.operador = operador;
	}

	
	
	
	
	/**
	 * @return the fecha
	 */
	public DATE getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(DATE fecha) {
		this.fecha = fecha;
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
	 * @return idOperador
	 */
	public long getOperador() {
		return operador;
	}

	/**
	 * @param idOperador - el id del operador
	 */
	public void setOperador(long idOperador) {
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
