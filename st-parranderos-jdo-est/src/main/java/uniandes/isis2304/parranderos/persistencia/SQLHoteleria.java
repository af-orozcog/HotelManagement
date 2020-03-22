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

import java.time.LocalTime;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Habitacion;
import uniandes.isis2304.parranderos.negocio.Hoteleria;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLHoteleria extends SQLOperador
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
	public SQLHoteleria (PersistenciaAlohandes pa)
	{
		super(pa);
	}

	/**
	* Crea y ejecuta la sentencia SQL para adicionar un HOTELERIA a la base de datos de Alohandes
	* @param idHoteleria - 
	* @param nombre - 
	* @param email - 
	* @param numero - 
	* @param tipoHoteleria - 
	* @param horaApertura - 
	* @param horaCierre - 
	* @return El número de tuplas insertadas 
	*/
	public long adicionarHoteleria (PersistenceManager pm, long idHoteleria, String nombre, String email, String numero, String tipoHoteleria, LocalTime horaApertura, LocalTime horaCierre)
	{
		super.adicionarOperador(pm, idHoteleria, nombre, email, numero);
	   Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaHoteleria () + "(id, tipoHoteleria, horaApertura, horaCierre) values (? ,? ,? ,?)");
	   q.setParameters( idHoteleria, nombre, email, numero, tipoHoteleria, horaApertura, horaCierre );
	    return (long) q.executeUnique();
	}

	/**
	* Crea y ejecuta la sentencia SQL para eliminar un HOTELERIA de la base de datos de Alohandes, por su identificador
	* @param pm - El manejador de persistencia
	* @param idHoteleria - El identificador de la Hoteleria
	* @return EL número de tuplas eliminadas
	*/
	public long eliminarHoteleriaPorId (PersistenceManager pm, long idHoteleria)
	{
	    return super.eliminarOperadorPorId(pm, idHoteleria);
	}

	/**
	* Crea y ejecuta la sentencia SQL para encontrar la información de un HOTELERIA de la 
	* base de datos de Alohandes, por su identificador
	* @param pm - El manejador de persistencia
	* @param idHoteleria - El identificador de la Hoteleria
	* @return El objeto HOTELERIA que tiene el identificador dado
	*/
	public Hoteleria darHoteleriaPorId (PersistenceManager pm, long idHoteleria)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHoteleria () +" NATURAL JOIN "+ pa.darTablaOperador() +" WHERE id = ?");
		q.setResultClass(Hoteleria.class);
		q.setParameters(idHoteleria);
		return (Hoteleria) q.executeUnique();
	}


	/**
	* Crea y ejecuta la sentencia SQL para encontrar la información de LOS(AS) Hotelerias de la
	* base de datos de Alohandes
	* @param pm - El manejador de persistencia
	* @return Una lista de objetos Hoteleria
	*/
	public List<Hoteleria> darHotelerias (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHoteleria () +" NATURAL JOIN " + pa.darTablaOperador());
		q.setResultClass(Hoteleria.class);
		return (List<Hoteleria>) q.executeList();
	}
}
