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

package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Vivienda;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLVivienda 
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
	public SQLVivienda (PersistenciaAlohandes pa)
	{
	   this.pa = pa;
	}

	/**
	* Crea y ejecuta la sentencia SQL para adicionar un VIVIENDA a la base de datos de Alohandes
	* @param idVivienda - 
	* @param direccion - 
	* @param cupos - 
	* @param idOperador - 
	* @return El número de tuplas insertadas 
	*/
	public long adicionarVivienda (PersistenceManager pm, long idVivienda, String direccion, int cupos, long idOperador)
	{
	   Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaVivienda () + "(id, direccion, cupos, idOperador) values (?, ,? ,? ,?)");
	   q.setParameters(idVivienda, direccion, cupos, idOperador );
	    return (long) q.executeUnique();
	}

	/**
	* Crea y ejecuta la sentencia SQL para eliminar un VIVIENDA de la base de datos de Alohandes, por su identificador
	* @param pm - El manejador de persistencia
	* @param idVivienda - El identificador de la Vivienda
	* @return EL número de tuplas eliminadas
	*/
	public long eliminarViviendaPorId (PersistenceManager pm, long idVivienda)
	{
	    Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaVivienda () + " WHERE id = ?");
	    q.setParameters(idVivienda);
	    return (long) q.executeUnique();
	}

	/**
	* Crea y ejecuta la sentencia SQL para encontrar la información de un VIVIENDA de la 
	* base de datos de Alohandes, por su identificador
	* @param pm - El manejador de persistencia
	* @param idVivienda - El identificador de la Vivienda
	* @return El objeto VIVIENDA que tiene el identificador dado
	*/
	public Vivienda darViviendaPorId (PersistenceManager pm, long idVivienda)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaVivienda () + " WHERE id = ?");
		q.setResultClass(Vivienda.class);
		q.setParameters(idVivienda);
		return (Vivienda) q.executeUnique();
	}


	/**
	* Crea y ejecuta la sentencia SQL para encontrar la información de LOS(AS) Viviendas de la
	* base de datos de Alohandes
	* @param pm - El manejador de persistencia
	* @return Una lista de objetos Vivienda
	*/
	public List<Vivienda> darViviendas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaVivienda ());
		q.setResultClass(Vivienda.class);
		return (List<Vivienda>) q.executeList();
	}
	
}
