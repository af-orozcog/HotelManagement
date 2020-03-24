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

package uniandes.isis2304.alohandes.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Seguro;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBEDOR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLSeguro 
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
	private PersistenciaAlohandes pa;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/** 
	* Constructor
	* @param pa - El Manejador de persistencia de la aplicación
	*/
	public SQLSeguro (PersistenciaAlohandes pa)
	{
	   this.pa = pa;
	}

	/**
	* Crea y ejecuta la sentencia SQL para adicionar un SEGURO a la base de datos de Alohandes
	* @param empresa - 
	* @param monto - 
	* @param inicioSeguro - 
	* @param finSeguro - 
	* @return El número de tuplas insertadas 
	*/
	public long adicionarSeguro (PersistenceManager pm, long idSeguro, String empresa, int monto, Timestamp inicioSeguro, Timestamp finSeguro)
	{
	   Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaSeguro () + "(id, empresa, monto, inicio_seguro, fin_seguro) values (? ,? ,? ,? ,?)");
	   q.setParameters(empresa, monto, inicioSeguro, finSeguro );
	    return (long) q.executeUnique();
	}

	/**
	* Crea y ejecuta la sentencia SQL para eliminar un SEGURO de la base de datos de Alohandes, por su identificador
	* @param pm - El manejador de persistencia
	* @param idSeguro - El identificador de la Seguro
	* @return EL número de tuplas eliminadas
	*/
	public long eliminarSeguroPorId (PersistenceManager pm, long idSeguro)
	{
	    Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaVivienda () + " WHERE id = ?");
	    q.setParameters(idSeguro);
	    return (long) q.executeUnique();
	}

	/**
	* Crea y ejecuta la sentencia SQL para encontrar la información de un SEGURO de la 
	* base de datos de Alohandes, por su identificador
	* @param pm - El manejador de persistencia
	* @param idSeguro - El identificador de la Seguro
	* @return El objeto SEGURO que tiene el identificador dado
	*/
	public Seguro darSeguroPorId (PersistenceManager pm, long idSeguro)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaSeguro () + " WHERE id = ?");
		q.setResultClass(Seguro.class);
		q.setParameters(idSeguro);
		return (Seguro) q.executeUnique();
	}


	/**
	* Crea y ejecuta la sentencia SQL para encontrar la información de LOS(AS) Seguros de la
	* base de datos de Alohandes
	* @param pm - El manejador de persistencia
	* @return Una lista de objetos Seguro
	*/
	public List<Seguro> darSeguros (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaSeguro ());
		q.setResultClass(Seguro.class);
		return (List<Seguro>) q.executeList();
	}




}
