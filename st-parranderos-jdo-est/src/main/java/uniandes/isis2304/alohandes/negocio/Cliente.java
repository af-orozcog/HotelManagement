package uniandes.isis2304.alohandes.negocio;

import java.time.LocalTime;

public class Cliente extends Operador implements VOCliente{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	/**
	 * tipo de cliente (PROFESOR, EMPLEADO, EGRESADO, ESTUDIANTE, PADRE, NO_RELACIONADO, VECINO)
	 */
	private String tipoCliente;

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
	public Cliente() {
		super();
		this.tipoCliente = "";
		this.documento = null;
	}

	/**
	 * Constructor con valores
	 * @param id - el id de la persona natural
	 * @param nombre - nombre de la persona natural
	 * @param email - la dirección de correo de la persona natural
	 * @param numero - el numero telefonico de la persona natural
	 * @param tipoCliente - el tipo de persona natural
	 * @param documento - documento de la persona natural
	 */
	public Cliente(long id, String nombre, String email, String numero, String tipoCliente,
			String documento) {
		super(id,nombre,email,numero);
		this.tipoCliente = tipoCliente;
		this.documento = documento;
	}

	/**
	 * @return tipoHoteleria
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente - el nuevo tipo de persona natural 
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
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