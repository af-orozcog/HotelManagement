package uniandes.isis2304.parranderos.negocio;

public class Cuarto extends Vivienda{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 * booleano para saber si el baño es privado
	 */
	private boolean bañoPrivado;
	
	/**
	 * booleano para saber si el cuarto es privado
	 */
	private boolean cuartoPrivado;
	
	/**
	 * el esquema del cuarto
	 */
	private String esquema;
	
	/**
	 * el menaje del cuarto
	 */
	private String menaje;
	
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	public Cuarto() {
		super();
		this.bañoPrivado = false;
		this.cuartoPrivado = false;
		this.esquema = "";
		this.menaje = "";
	}


	/**
	 * Constructor con valores 
	 * @param bañoPrivado - booleano para saber si el baño es privado
	 * @param cuartoPrivado - booleano para saber si el cuarto es privado
	 * @param esquema - el esquema del cuarto
	 * @param menaje - el menaje del cuarto
	 * @param direccion -  direccion de la vivienda
	 * @param cupos - cupos disponibles de la vivienda
	 * @param idOperador - el operador dueño de la vivienda
	 */
	public Cuarto(long id, String direccion, int cupos, long idOperador, boolean bañoPrivado, boolean cuartoPrivado, String esquema, String menaje) {
		super(id, direccion, cupos,idOperador);
		this.bañoPrivado = bañoPrivado;
		this.cuartoPrivado = cuartoPrivado;
		this.esquema = esquema;
		this.menaje = menaje;
	}


	/**
	 * @return bañoPrivado
	 */
	public boolean isBañoPrivado() {
		return bañoPrivado;
	}


	/**
	 * @param bañoPrivado - booleano del baño
	 */
	public void setBañoPrivado(boolean bañoPrivado) {
		this.bañoPrivado = bañoPrivado;
	}


	/**
	 * @return cuartoPrivado
	 */
	public boolean isCuartoPrivado() {
		return cuartoPrivado;
	}


	/**
	 * @param cuartoPrivado - booleano del cuarto privado
	 */
	public void setCuartoPrivado(boolean cuartoPrivado) {
		this.cuartoPrivado = cuartoPrivado;
	}


	/**
	 * @return esquema
	 */
	public String getEsquema() {
		return esquema;
	}


	/**
	 * @param esquema - el esquema del cuarto
	 */
	public void setEsquema(String esquema) {
		this.esquema = esquema;
	}


	/**
	 * @return menaje
	 */
	public String getMenaje() {
		return menaje;
	}


	/**
	 * @param menaje - el menaje del cuarto
	 */
	public void setMenaje(String menaje) {
		this.menaje = menaje;
	}
	
}
