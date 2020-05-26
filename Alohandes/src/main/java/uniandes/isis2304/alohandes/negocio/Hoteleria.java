package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;
import java.util.List;

import oracle.sql.TIMESTAMP;

public class Hoteleria extends Operador implements VOHoteleria{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 * tipo de hoteleria (HOSTAL, HOTEL)
	 */
	private String tipo_hoteleria;
	
	/**
	 * hora de apertura del hotel
	 */
	private String hora_apertura;
	
	/**
	 * hora de cierre del hotel
	 */
	private String hora_cierre;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
     * Constructor por defecto
     */
	public Hoteleria() {
		super();
		this.tipo_hoteleria = "";
		this.hora_apertura = null;
		this.hora_cierre = null;
	}
	
	/**
	 * Constructor con valores
	 * @param id - el id de la hoteleria
	 * @param nombre - nombre de la hoteleria
	 * @param email - la dirección de correo del usuario
	 * @param numero - el numero telefonico del usuario
	 * @param tipoHoteleria - el tipo de hotel
	 * @param horaApertura - la hora de apertura
	 * @param horaCierre - la hora de cierre
	 */
	public Hoteleria(long id, String nombre, String email, String numero, String tipoHoteleria,
			String horaApertura, String horaCierre) {
		super(id,nombre,email,numero,"HOTELERIA");
		this.tipo_hoteleria = tipoHoteleria;
		this.hora_apertura = horaApertura;
		this.hora_cierre = horaCierre;
	}

	/**
	 * @return tipoHoteleria
	 */
	public String getTipo_hoteleria() {
		return tipo_hoteleria;
	}

	/**
	 * @param tipoHoteleria - el nuevo tipo de hoteleria 
	 */
	public void setTipo_hoteleria(String tipoHoteleria) {
		this.tipo_hoteleria = tipoHoteleria;
	}

	/**
	 * @return horaApertura
	 */
	public String getHora_apertura() {
		return hora_apertura;
	}

	/**
	 * @param horaApertura - la nueva hora de apertura
	 */
	public void setHora_apertura(String horaApertura) {
		this.hora_apertura = horaApertura;
	}

	/**
	 * @return horaCierre
	 */
	public String getHora_cierre() {
		return hora_cierre;
	}

	/**
	 * @param horaCierre - la nueva hora de cierre
	 */
	public void setHora_cierre(String horaCierre) {
		this.hora_cierre = horaCierre;
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
	public String getTipo_operador() {
		return tipo_operador;
	}

	/**
	 * @param tipoOperador - el nuevo tipo operador
	 */
	public void setTipo_operador(String tipo_operador) {
		this.tipo_operador = tipo_operador;
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
