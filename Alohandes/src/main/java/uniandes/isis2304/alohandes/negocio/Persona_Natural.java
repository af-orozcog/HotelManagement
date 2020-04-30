package uniandes.isis2304.alohandes.negocio;

import java.time.LocalTime;
import java.util.List;

public class Persona_Natural extends Operador implements VOPersona_Natural{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	/**
	 * tipo de persona natural (PROFESOR, EMPLEADO, EGRESADO, ESTUDIANTE, PADRE, NO_RELACIONADO, VECINO)
	 */
	private String tipo_persona;

	/**
	 * documento de la persona natural
	 */
	private String documento;


	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	/**
	 * Constructor por defecto
	 */
	public Persona_Natural() {
		super();
		this.tipo_persona = null;
		this.documento = null;
	}

	/**
	 * Constructor con valores
	 * @param id - el id de la persona natural
	 * @param nombre - nombre de la persona natural
	 * @param email - la dirección de correo de la persona natural
	 * @param numero - el numero telefonico de la persona natural
	 * @param tipoPersona - el tipo de persona natural
	 * @param documento - documento de la persona natural
	 */
	public Persona_Natural(long id, String nombre, String email, String numero, String tipoPersona,
			String documento) {
		super(id,nombre,email,numero,"PERSONA_NATURAL");
		this.tipo_persona = tipoPersona;
		this.documento = documento;
	}

	/**
	 * @return tipoHoteleria
	 */
	public String getTipo_persona() {
		return tipo_persona;
	}

	/**
	 * @param tipoPersona - el nuevo tipo de persona natural 
	 */
	public void setTipo_persona(String tipoPersona) {
		this.tipo_persona = tipoPersona;
	}

	/**
	 * @return documento
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * @param documento - el nuevo documento
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
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
