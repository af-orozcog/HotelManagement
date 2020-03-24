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

import uniandes.isis2304.alohandes.negocio.Ganancias;
import uniandes.isis2304.alohandes.negocio.Operador;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBEDOR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLGanancias 
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
	public SQLGanancias (PersistenciaAlohandes pa)
	{
		this.pa = pa;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un GANANCIAS a la base de datos de Alohandes
	 * @param idGanancias - 
	 * @param cantidad - 
	 * @param mes - 
	 * @param año - 
	 * @param idOperador - 
	 * @return El número de tuplas insertadas 
	 */
	public long adicionarGanancias (PersistenceManager pm, long idGanancias, long cantidad, int mes, int año, long idOperador)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaGanancias () + "(id, cantidad, mes, año, operador) values (? ,? ,? ,? ,?)");
		q.setParameters( idGanancias, cantidad, mes, año, idOperador );
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un GANANCIAS de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idGanancias - El identificador de la Ganancias
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarGananciasPorId (PersistenceManager pm, long idGanancias)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaGanancias () + " WHERE id = ?");
		q.setParameters(idGanancias);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un GANANCIAS de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idGanancias - El identificador de la Ganancias
	 * @return El objeto GANANCIAS que tiene el identificador dado
	 */
	public Ganancias darGananciasPorId (PersistenceManager pm, long idGanancias)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaGanancias () + " WHERE id = ?");
		q.setResultClass(Ganancias.class);
		q.setParameters(idGanancias);
		return (Ganancias) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS(AS) Ganancias de la
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Ganancias
	 */
	public List<Ganancias> darGanancias (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaGanancias ());
		q.setResultClass(Ganancias.class);
		return (List<Ganancias>) q.executeList();
	}


}
