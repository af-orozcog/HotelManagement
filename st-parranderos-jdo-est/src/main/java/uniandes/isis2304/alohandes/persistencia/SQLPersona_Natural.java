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

import java.time.LocalTime;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Habitacion;
import uniandes.isis2304.alohandes.negocio.Persona_Natural;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLPersona_Natural extends SQLOperador
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
	public SQLPersona_Natural (PersistenciaAlohandes pa)
	{
		super(pa);
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un PERSONA_NATURAL a la base de datos de Alohandes
	 * @param idPersona_Natural - 
	 * @param nombre - 
	 * @param email - 
	 * @param numero - 
	 * @param documento - 
	 * @param tipoPersona - 
	 * @return El número de tuplas insertadas 
	 */
	public long adicionarPersona_Natural (PersistenceManager pm, long idPersona_Natural, String nombre, String email, String numero, String documento, String tipoPersona)
	{
		super.adicionarOperador(pm, idPersona_Natural, nombre, email, numero,"PERSONA_NATURAL");
		Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaPersona_Natural () + "(id, documento, tipo_persona) values (? ,? ,?)");
		q.setParameters( idPersona_Natural, documento, tipoPersona );
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un PERSONA_NATURAL de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPersona_Natural - El identificador de la Persona_Natural
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarPersona_NaturalPorId (PersistenceManager pm, long idPersona_Natural)
	{
		return super.eliminarOperadorPorId(pm, idPersona_Natural);
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un PERSONA_NATURAL de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPersona_Natural - El identificador de la Persona_Natural
	 * @return El objeto PERSONA_NATURAL que tiene el identificador dado
	 */
	public Persona_Natural darPersona_NaturalPorId (PersistenceManager pm, long idPersona_Natural)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaPersona_Natural () +" NATURAL JOIN " + pa.darTablaOperador() + " WHERE id = ?");
		q.setResultClass(Persona_Natural.class);
		q.setParameters(idPersona_Natural);
		return (Persona_Natural) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS(AS) Persona_Naturals de la
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Persona_Natural
	 */
	public List<Persona_Natural> darPersona_Naturales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaPersona_Natural () + " NATURAL JOIN " + pa.darTablaOperador());
		q.setResultClass(Persona_Natural.class);
		return (List<Persona_Natural>) q.executeList();
	}

}
