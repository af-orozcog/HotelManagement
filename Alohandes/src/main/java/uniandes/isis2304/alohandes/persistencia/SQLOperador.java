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
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Operador;
import uniandes.isis2304.alohandes.negocio.ViviendaUniversitaria;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBEDOR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLOperador 
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
	public SQLOperador (PersistenciaAlohandes pa)
	{
	   this.pa = pa;
	}

	/**
	* Crea y ejecuta la sentencia SQL para adicionar un OPERADOR a la base de datos de Alohandes
	* @param idOperador - 
	* @param nombre - 
	* @param email - 
	* @param numero - 
	* @return El número de tuplas insertadas 
	*/
	public long adicionarOperador (PersistenceManager pm, long idOperador, String nombre, String email, String numero, String tipoOperador)
	{
	   Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaOperador () + "(id, nombre, email, numero, tipo_operador) values (? ,? ,? ,? ,?)");
	   q.setParameters( idOperador, nombre, email, numero,tipoOperador);
	    return (long) q.executeUnique();
	}

	/**
	* Crea y ejecuta la sentencia SQL para eliminar un OPERADOR de la base de datos de Alohandes, por su identificador
	* @param pm - El manejador de persistencia
	* @param idOperador - El identificador de la Operador
	* @return EL número de tuplas eliminadas
	*/
	public long eliminarOperadorPorId (PersistenceManager pm, long idOperador)
	{
	    Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOperador () + " WHERE id = ?");
	    q.setParameters(idOperador);
	    return (long) q.executeUnique();
	}

	/**
	* Crea y ejecuta la sentencia SQL para encontrar la información de un OPERADOR de la 
	* base de datos de Alohandes, por su identificador
	* @param pm - El manejador de persistencia
	* @param idOperador - El identificador de la Operador
	* @return El objeto OPERADOR que tiene el identificador dado
	*/
	public Operador darOperadorPorId (PersistenceManager pm, long idOperador)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOperador () + " WHERE id = ?");
		q.setResultClass(Operador.class);
		q.setParameters(idOperador);
		return (Operador) q.executeUnique();
	}


	/**
	* Crea y ejecuta la sentencia SQL para encontrar la información de LOS(AS) Operadores de la
	* base de datos de Alohandes
	* @param pm - El manejador de persistencia
	* @return Una lista de objetos Operador
	*/
	public List<Operador> darOperadores (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOperador ());
		q.setResultClass(Operador.class);
		return (List<Operador>) q.executeList();
	}
	
	/**
	* Crea y ejecuta la sentencia SQL para encontrar la información de LOS(AS) ViviendasUniversitarias de la
	* base de datos de Alohandes
	* @param pm - El manejador de persistencia
	* @return Una lista de objetos Operador
	*/
	public List<ViviendaUniversitaria> darViviendasUniversitarias (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOperador () + " WHERE tipo_operador = 'VIVIENDA_UNIVERSITARIA' ");
		q.setResultClass(Operador.class);
		List<Object[]> objects = q.executeList();
		List<ViviendaUniversitaria> viviendas = new LinkedList<ViviendaUniversitaria>();
		for (Object[] viv : objects) {
			long id = ((BigDecimal) viv[0]).longValue();
			String nombre = viv[1].toString();
			String email = viv[2].toString();
			String numero = viv[3].toString();
			viviendas.add(new ViviendaUniversitaria(id, nombre, email, numero));
		}
		return viviendas;
	}
	
	/**
	* Crea y ejecuta la sentencia SQL para encontrar la información de un OPERADOR de la 
	* base de datos de Alohandes, por su identificador
	* @param pm - El manejador de persistencia
	* @param idOperador - El identificador de la Operador
	* @return El objeto OPERADOR que tiene el identificador dado
	*/
	public Operador darOperadorPorNombre (PersistenceManager pm, String nombre, String tipoOperador)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOperador () + " WHERE nombre = ? AND tipo_operador = ?");
		q.setParameters(nombre,tipoOperador);
		q.setResultClass(Operador.class);
		return (Operador) q.executeUnique();
	}

}
