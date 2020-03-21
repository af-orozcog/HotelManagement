package uniandes.isis2304.parranderos.negocio;

import java.security.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class Seguro {
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 *  id del seguro
	 */
	private long id;
	
	/**
	 * empresa a la que pertence el seguro
	 */
	private String empresa;
	
	/**
	 * monto del seguro
	 */
	private int monto;
	
	/**
	 * fecha de inicio del seguro
	 */
	private Timestamp inicioSeguro;
	
	/**
	 * fecha de finalización del seguro
	 */
	private Timestamp finSeguro;
	
	/**
	 * la lista de asegurados
	 */
	private List<Object []> asegurados;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	public Seguro() {
		this.id = 0;
		this.empresa  = "";
		this.monto = 0;
		this.inicioSeguro = null;
		this.finSeguro = null;
		this.asegurados = new LinkedList<Object []> ();
	}
	
	/**
	 * Constructor con valores
	 * @param id - el identificador unico
	 * @param empresa - la empresa del seguro
	 * @param monto - el monto del seguro
	 * @param inicioSeguro - la fecha de inicio del seguro
	 * @param finSeguro - la fecha de finalización del seguro
	 */
	public Seguro(long id, String empresa, int monto, Timestamp inicioSeguro, Timestamp finSeguro) {
		this.id = id;
		this.empresa = empresa;
		this.monto = monto;
		this.inicioSeguro = inicioSeguro;
		this.finSeguro = finSeguro;
		this.asegurados = new LinkedList<Object []> ();
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
	 * @return empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa - la empresa que ofrece el seguro
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return monto
	 */
	public int getMonto() {
		return monto;
	}

	/**
	 * @param monto - el monto del seguro
	 */
	public void setMonto(int monto) {
		this.monto = monto;
	}

	/**
	 * @return inicioSeguro
	 */
	public Timestamp getInicioSeguro() {
		return inicioSeguro;
	}

	/**
	 * @param inicioSeguro - fecha de inicio del seguro
	 */
	public void setInicioSeguro(Timestamp inicioSeguro) {
		this.inicioSeguro = inicioSeguro;
	}

	/**
	 * @return finSeguro
	 */
	public Timestamp getFinSeguro() {
		return finSeguro;
	}

	/**
	 * @param finSeguro - fecha de finalización del seguro
	 */
	public void setFinSeguro(Timestamp finSeguro) {
		this.finSeguro = finSeguro;
	}

	/**
	 * @return asegurados
	 */
	public List<Object[]> getAsegurados() {
		return asegurados;
	}

	/**
	 * @param asegurados - los asegurados
	 */
	public void setAsegurados(List<Object[]> asegurados) {
		this.asegurados = asegurados;
	}
	
}
