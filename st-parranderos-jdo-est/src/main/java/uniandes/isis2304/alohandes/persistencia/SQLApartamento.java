/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Alohandes Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Apartamento;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLApartamento extends SQLVivienda
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaAlohandes.SQL;


	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/** 
	 * Constructor
	 * @param pa - El Manejador de persistencia de la aplicación
	 */
	public SQLApartamento (PersistenciaAlohandes pa)
	{
		super(pa);
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un APARTAMENTO a la base de datos de Alohandes
	 * @param area - 
	 * @param amoblado - 
	 * @param numeroHabitaciones - 
	 * @return El número de tuplas insertadas 
	 */
	public long adicionarApartamento (PersistenceManager pm, long idApartamento, String direccion, int cupos, long idOperador, double area, boolean amoblado, int numeroHabitaciones)
	{
		super.adicionarVivienda(pm, idApartamento, direccion, cupos, idOperador);
		Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaApartamento () + "(id, area, amoblado, numeroHabitaciones) values (? ,? ,? ,?)");
		q.setParameters(area, amoblado, numeroHabitaciones );
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un APARTAMENTO de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idApartamento - El identificador de la Apartamento
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarApartamentoPorId (PersistenceManager pm, long idApartamento)
	{
		return super.eliminarViviendaPorId(pm, idApartamento);
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un APARTAMENTO de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idApartamento - El identificador de la Apartamento
	 * @return El objeto APARTAMENTO que tiene el identificador dado
	 */
	public Apartamento darApartamentoPorId (PersistenceManager pm, long idApartamento)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaApartamento () + " NATURAL JOIN "+ pa.darTablaVivienda() 
		+"WHERE id = ?");
		q.setResultClass(Apartamento.class);
		q.setParameters(idApartamento);
		return (Apartamento) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS(AS) Apartamentos de la
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Apartamento
	 */
	public List<Apartamento> darApartamentos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaApartamento () + " NATURAL JOIN "+ pa.darTablaVivienda() );
		q.setResultClass(Apartamento.class);
		return (List<Apartamento>) q.executeList();
	}



}
