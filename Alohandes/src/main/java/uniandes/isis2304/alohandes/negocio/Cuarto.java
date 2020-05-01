package uniandes.isis2304.alohandes.negocio;

import java.util.List;

public class Cuarto extends Vivienda implements VOCuarto{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 * booleano para saber si el baño es privado
	 */
	private int banio_privado;
	
	/**
	 * booleano para saber si el cuarto es privado
	 */
	private int cuarto_privado;
	
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
		this.banio_privado = 0;
		this.cuarto_privado = 0;
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
	public Cuarto(long id, String direccion, int cupos, long idOperador, int banio_privado, int cuarto_privado, String esquema, String menaje) {
		super(id, direccion, cupos,idOperador,"CUARTO");
		this.banio_privado = banio_privado;
		this.cuarto_privado = cuarto_privado;
		this.esquema = esquema;
		this.menaje = menaje;
	}

	
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return bañoPrivado
	 */
	public int isBanio_Privado() {
		return banio_privado;
	}

	
	/**
	 * @param bañoPrivado - booleano del baño
	 */
	public void setBanio_privado(int banio_privado) {
		this.banio_privado = banio_privado;
	}


	/**
	 * @return cuartoPrivado
	 */
	public int isCuarto_privado() {
		return cuarto_privado;
	}


	/**
	 * @param cuartoPrivado - booleano del cuarto privado
	 */
	public void setCuarto_privado(int cuarto_privado) {
		this.cuarto_privado = cuarto_privado;
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
	
	/**
	 * @return direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id - el id de la vivienda
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param direccion - la direccion de la vivienda
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return cupos
	 */
	public int getCupos() {
		return cupos;
	}

	/**
	 * @param cupos - los cupos de la vivienda
	 */
	public void setCupos(int cupos) {
		this.cupos = cupos;
	}

	/**
	 * @return ofertas 
	 */
	public List<Object[]> getOfertas() {
		return ofertas;
	}

	/**
	 * @param ofertas - las ofertas de la vivienda
	 */
	public void setOfertas(List<Object[]> ofertas) {
		this.ofertas = ofertas;
	}

	/**
	 * @return idOperador
	 */
	public long getOperador() {
		return operador;
	}

	/**
	 * @param idOperador - el identificador del operador
	 */
	public void setOperador(long Operador) {
		this.operador = Operador;
	}
	
}
