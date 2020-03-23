package uniandes.isis2304.alohandes.negocio;

public class Esporadico extends Apartamento implements VOEsporadico{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * numero de noches al año que se ha utilizado
	 */
	private int nochesAño;
	
	/**
	 * id del seguro
	 */
	private long idSeguro;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
     * Constructor por defecto
     */
	public Esporadico() 
    {
    	super();
    	this.nochesAño = 0;
    	this.idSeguro = 0;
	}
	
	/**
	 * Constructor con valores
	 * @param area - el area del apartamento
	 * @param amoblado - booleano que representa si el apartamento esta amoblado
	 * @param numeroHabitaciones - el numero de habitaciones del apartamento
	 * @param direccion -  direccion de la vivienda
	 * @param cupos - cupos disponibles de la vivienda
	 * @param id - id de la vivienda
	 * @param idOperador - el operador dueño de la vivienda
	 * @param nochesAño - numero de noches del año
	 * @param idSeguro - id del seguro
	 */
	public Esporadico(long id, String direccion, int cupos, long idOperador, double area, boolean amoblado, 
			int numeroHabitaciones, int nochesAño, long idSeguro) {
		super(id, direccion, cupos, idOperador, area,amoblado,numeroHabitaciones);
		this.nochesAño = nochesAño;
		this.idSeguro = idSeguro;
	}

	/**
	 * @return nochesAño
	 */
	public int getNochesAño() {
		return nochesAño;
	}

	/**
	 * @param nochesAño - noches al año
	 */
	public void setNochesAño(int nochesAño) {
		this.nochesAño = nochesAño;
	}

	/**
	 * @return idSeguro
	 */
	public long getIdSeguro() {
		return idSeguro;
	}

	/**
	 * @param idSeguro - id del seguro
	 */
	public void setIdSeguro(long idSeguro) {
		this.idSeguro = idSeguro;
	}
	
}
