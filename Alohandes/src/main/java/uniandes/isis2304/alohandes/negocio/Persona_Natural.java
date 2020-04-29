package uniandes.isis2304.alohandes.negocio;

import java.time.LocalTime;

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
	public String getTipoPersona() {
		return tipo_persona;
	}

	/**
	 * @param tipoPersona - el nuevo tipo de persona natural 
	 */
	public void setTipoPersona(String tipoPersona) {
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

}
