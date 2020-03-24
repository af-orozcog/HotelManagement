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

import uniandes.isis2304.alohandes.negocio.Esporadico;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLEsporadico extends SQLApartamento
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
	public SQLEsporadico (PersistenciaAlohandes pa)
	{
		super(pa);
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un ESPORADICO a la base de datos de Alohandes
	 * @param idEsporadico - 
	 * @param nochesAño - 
	 * @param idSeguro - 
	 * @return El número de tuplas insertadas 
	 */
	public long adicionarEsporadico (PersistenceManager pm, long idEsporadico, String direccion, int cupos, long idOperador, 
			double area, boolean amoblado, int numeroHabitaciones, int nochesAño, long idSeguro)
	{
		
		super.adicionarApartamento(pm, idEsporadico, direccion, cupos, idOperador, area, amoblado, numeroHabitaciones);
		Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaEsporadico () + "(id, area, numero_habitaciones, noches_año, seguro, amoblado) values (?, ?, ?, ?, ?,?)");
		q.setParameters(idEsporadico,area,numeroHabitaciones, nochesAño, idSeguro ,amoblado);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un ESPORADICO de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEsporadico - El identificador de la Esporadico
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarEsporadicoPorId (PersistenceManager pm, long idEsporadico)
	{
		return super.eliminarViviendaPorId(pm, idEsporadico);
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un ESPORADICO de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEsporadico - El identificador de la Esporadico
	 * @return El objeto ESPORADICO que tiene el identificador dado
	 */
	public Esporadico darEsporadicoPorId (PersistenceManager pm, long idEsporadico)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaEsporadico () + " NATURAL JOIN ("+ pa.darTablaVivienda() +" NATURAL JOIN " + pa.darTablaApartamento()+ ") "
		+"WHERE id = ?");
		q.setResultClass(Esporadico.class);
		q.setParameters(idEsporadico);
		return (Esporadico) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS(AS) Esporadicos de la
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Esporadico
	 */
	public List<Esporadico> darEsporadicos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaEsporadico () + " NATURAL JOIN ("+ pa.darTablaVivienda() +" NATURAL JOIN " + pa.darTablaApartamento()+ ") ");
		q.setResultClass(Esporadico.class);
		return (List<Esporadico>) q.executeList();
	}

}
