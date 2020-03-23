package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class Servicio implements VOServicio{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 *  id del servicio
	 */
	private long id;
	
	/**
	 * nombre a la que pertence el servicio
	 */
	private String nombre;
	
	/**
	 * costo del servicio
	 */
	private long costo;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	public Servicio() {
		this.id = 0;
		this.nombre  = "";
		this.costo = 0;
	}
	
	/**
	 * Constructor con valores
	 * @param id - el identificador unico
	 * @param nombre - la nombre del servicio
	 * @param costo - el costo del servicio
	 */
	public Servicio(long id, String nombre, long costo) {
		this.id = id;
		this.nombre = nombre;
		this.costo = costo;
	}

	/**
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id el id que se quiere poner
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre - la nombre que ofrece el servicio
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return costo
	 */
	public long getCosto() {
		return costo;
	}

	/**
	 * @param costo - el costo del servicio
	 */
	public void setCosto(long costo) {
		this.costo = costo;
	}
	
	
}
