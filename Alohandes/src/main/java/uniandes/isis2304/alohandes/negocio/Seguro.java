package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import oracle.sql.TIMESTAMP;

public class Seguro implements VOSeguro{
	
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
	private TIMESTAMP inicio_seguro;
	
	/**
	 * fecha de finalización del seguro
	 */
	private TIMESTAMP fin_seguro;
	
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
		this.inicio_seguro = null;
		this.fin_seguro = null;
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
	public Seguro(long id, String empresa, int monto, TIMESTAMP inicioSeguro, TIMESTAMP finSeguro) {
		this.id = id;
		this.empresa = empresa;
		this.monto = monto;
		this.inicio_seguro = inicioSeguro;
		this.fin_seguro = finSeguro;
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
	public TIMESTAMP getInicio_seguro() {
		return inicio_seguro;
	}

	/**
	 * @param inicioSeguro - fecha de inicio del seguro
	 */
	public void setInicio_seguro(TIMESTAMP inicioSeguro) {
		this.inicio_seguro = inicioSeguro;
	}

	/**
	 * @return finSeguro
	 */
	public TIMESTAMP getFin_seguro() {
		return fin_seguro;
	}

	/**
	 * @param finSeguro - fecha de finalización del seguro
	 */
	public void setFin_seguro(TIMESTAMP finSeguro) {
		this.fin_seguro = finSeguro;
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
