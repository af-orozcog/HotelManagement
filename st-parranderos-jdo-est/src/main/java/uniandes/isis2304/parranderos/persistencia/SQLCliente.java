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

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Bebedor;
import uniandes.isis2304.parranderos.negocio.Cliente;
import uniandes.isis2304.parranderos.negocio.Usuario;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBEDOR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLCliente 
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
	private PersistenciaAlohandes pa;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/** 
	* Constructor
	* @param pa - El Manejador de persistencia de la aplicación
	*/
	public SQLCliente (PersistenciaAlohandes pa)
	{
	   this.pa = pa;
	}

	/**
	* Crea y ejecuta la sentencia SQL para adicionar un CLIENTE a la base de datos de Alohandes
	* @param idCliente - 
	* @param nombre - 
	* @param email - 
	* @param numero - 
	* @param tipoCliente - 
	* @param documento - 
	* @return El número de tuplas insertadas 
	*/
	public long adicionarCliente (PersistenceManager pm, long idCliente, String nombre, String email, String numero, String tipoCliente, String documento)
	{
	   Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaCliente () + "(id, nombre, email, numero, tipoCliente, documento) values (? ,? ,? ,? ,? ,?)");
	   q.setParameters( idCliente, nombre, email, numero, tipoCliente, documento );
	    return (long) q.executeUnique();
	}

	/**
	* Crea y ejecuta la sentencia SQL para eliminar un CLIENTE de la base de datos de Alohandes, por su identificador
	* @param pm - El manejador de persistencia
	* @param idCliente - El identificador de la Cliente
	* @return EL número de tuplas eliminadas
	*/
	public long eliminarClientePorId (PersistenceManager pm, long idCliente)
	{
	    Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaCliente () + " WHERE id = ?");
	    q.setParameters(idCliente);
	    return (long) q.executeUnique();
	}

	/**
	* Crea y ejecuta la sentencia SQL para encontrar la información de un CLIENTE de la 
	* base de datos de Alohandes, por su identificador
	* @param pm - El manejador de persistencia
	* @param idCliente - El identificador de la Cliente
	* @return El objeto CLIENTE que tiene el identificador dado
	*/
	public Cliente darClientePorId (PersistenceManager pm, long idCliente)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaCliente () + " WHERE id = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(idCliente);
		return (Cliente) q.executeUnique();
	}


	/**
	* Crea y ejecuta la sentencia SQL para encontrar la información de LOS(AS) Clientes de la
	* base de datos de Alohandes
	* @param pm - El manejador de persistencia
	* @return Una lista de objetos Cliente
	*/
	public List<Cliente> darClientes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaCliente ());
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}


}
