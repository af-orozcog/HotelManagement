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

import uniandes.isis2304.alohandes.negocio.Habitacion;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLHabitacion extends SQLVivienda
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
	public SQLHabitacion (PersistenciaAlohandes pa)
	{
		super(pa);
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un HABITACION a la base de datos de Alohandes
	 * @param tipoHabitacion - 
	 * @param categoria - 
	 * @param capacidad - 
	 * @param numero - 
	 * @return El número de tuplas insertadas 
	 */
	public long adicionarHabitacion (PersistenceManager pm, long idHabitacion, String direccion, int cupos, long idOperador, 
			String tipoHabitacion, String categoria, int capacidad, int numero)
	{
		super.adicionarVivienda(pm, idHabitacion, direccion, cupos, idOperador);
		Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaHabitacion () + "(id, tipo_Habitacion, categoria, numero) values (? ,? ,? ,?)");
		q.setParameters(idHabitacion, tipoHabitacion, categoria, numero );
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un HABITACION de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idHabitacion - El identificador de la Habitacion
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarHabitacionPorId (PersistenceManager pm, long idHabitacion)
	{
		return super.eliminarViviendaPorId(pm, idHabitacion);
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un HABITACION de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idHabitacion - El identificador de la Habitacion
	 * @return El objeto HABITACION que tiene el identificador dado
	 */
	public Habitacion darHabitacionPorId (PersistenceManager pm, long idHabitacion)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHabitacion () + " NATURAL JOIN "+ pa.darTablaVivienda() 
		+"WHERE id = ?");
		q.setResultClass(Habitacion.class);
		q.setParameters(idHabitacion);
		return (Habitacion) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS(AS) Habitaciones de la
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Habitacion
	 */
	public List<Habitacion> darHabitaciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHabitacion () + " NATURAL JOIN "+ pa.darTablaVivienda() );
		q.setResultClass(Habitacion.class);
		return (List<Habitacion>) q.executeList();
	}

}
