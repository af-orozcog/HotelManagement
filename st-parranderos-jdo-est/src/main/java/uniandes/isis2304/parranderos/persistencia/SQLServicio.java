/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Servicio;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBEDOR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLServicio 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaParranderos.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	protected PersistenciaAlohandes pa;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/** 
	* Constructor
	* @param pa - El Manejador de persistencia de la aplicación
	*/
	public SQLServicio (PersistenciaAlohandes pa)
	{
	   this.pa = pa;
	}

	/**
	* Crea y ejecuta la sentencia SQL para adicionar un SERVICIO a la base de datos de Alohandes
	* @param idServicio - 
	* @param nombre - 
	* @param costo - 
	* @return El número de tuplas insertadas 
	*/
	public long adicionarServicio (PersistenceManager pm, long idServicio, String nombre, long costo)
	{
	   Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaServicio () + "(id, nombre, costo) values (? ,? ,?)");
	   q.setParameters( idServicio, nombre, costo );
	    return (long) q.executeUnique();
	}

	/**
	* Crea y ejecuta la sentencia SQL para eliminar un SERVICIO de la base de datos de Alohandes, por su identificador
	* @param pm - El manejador de persistencia
	* @param idServicio - El identificador de la Servicio
	* @return EL número de tuplas eliminadas
	*/
	public long eliminarServicioPorId (PersistenceManager pm, long idServicio)
	{
	    Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaServicio () + " WHERE id = ?");
	    q.setParameters(idServicio);
	    return (long) q.executeUnique();
	}

	/**
	* Crea y ejecuta la sentencia SQL para encontrar la información de un SERVICIO de la 
	* base de datos de Alohandes, por su identificador
	* @param pm - El manejador de persistencia
	* @param idServicio - El identificador de la Servicio
	* @return El objeto SERVICIO que tiene el identificador dado
	*/
	public Servicio darServicioPorId (PersistenceManager pm, long idServicio)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaServicio () + " WHERE id = ?");
		q.setResultClass(Servicio.class);
		q.setParameters(idServicio);
		return (Servicio) q.executeUnique();
	}

	/**
	* Crea y ejecuta la sentencia SQL para encontrar la información de LOS(AS) Servicios de la
	* base de datos de Alohandes
	* @param pm - El manejador de persistencia
	* @return Una lista de objetos Servicio
	*/
	public List<Servicio> darServicios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaServicio ());
		q.setResultClass(Servicio.class);
		return (List<Servicio>) q.executeList();
	}
}
