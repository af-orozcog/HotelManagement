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
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Cliente;

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
	* @param documento -
	* @param tipoCliente
	* @return El número de tuplas insertadas 
	*/
	public long adicionarCliente (PersistenceManager pm, long idCliente, String nombre, String email, String numero, String documento, String tipoCliente)
	{
	   Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaCliente () + "(id, nombre, email, numero, documento, tipo_cliente) values (? ,? ,? ,? ,? ,?)");
	   q.setParameters( idCliente, nombre, email, numero, documento, tipoCliente );
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
	* Crea y ejecuta la sentencia SQL para encontrar la información de un CLIENTE de la 
	* base de datos de Alohandes, por su nombre
	* @param pm - El manejador de persistencia
	* @param nombreCliente - El identificador de la Cliente
	* @return El objeto CLIENTE que tiene el nombre dado
	*/
	public Cliente darClientePorNombre (PersistenceManager pm, String nombreCliente)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaCliente () + " WHERE nombre = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(nombreCliente);
		return (Cliente) q.executeUnique();
	}

	/**
	* Crea y ejecuta la sentencia SQL para encontrar la información de LOS(AS) Clientees de la
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
