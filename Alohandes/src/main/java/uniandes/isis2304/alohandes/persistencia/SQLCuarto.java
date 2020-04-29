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

import uniandes.isis2304.alohandes.negocio.Apartamento;
import uniandes.isis2304.alohandes.negocio.Cuarto;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLCuarto extends SQLVivienda
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
	public SQLCuarto (PersistenciaAlohandes pa)
	{
		super(pa);
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un CUARTO a la base de datos de Alohandes
	 * @param bañoPrivado - 
	 * @param cuartoPrivado - 
	 * @param esquema - 
	 * @param menaje - 
	 * @return El número de tuplas insertadas 
	 */
	public long adicionarCuarto (PersistenceManager pm, long idCuarto, String direccion, int cupos, long idOperador, int banio_privado, int cuarto_privado, String esquema, String menaje)
	{
		super.adicionarVivienda(pm, idCuarto, direccion, cupos, idOperador);
		Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaCuarto () + "(id, banio_privado, cuarto_privado, esquema, menaje) values (? ,? ,? ,? ,?)");
		q.setParameters(idCuarto, banio_privado, cuarto_privado, esquema, menaje );
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un CUARTO de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idCuarto - El identificador de la Cuarto
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarCuartoPorId (PersistenceManager pm, long idCuarto)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaCuarto() + " WHERE id = ?");
	    q.setParameters(idCuarto);
	    q.executeUnique();
		return super.eliminarViviendaPorId(pm, idCuarto);
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un CUARTO de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idCuarto - El identificador de la Cuarto
	 * @return El objeto CUARTO que tiene el identificador dado
	 */
	public Cuarto darCuartoPorId (PersistenceManager pm, long idCuarto)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaCuarto () + " NATURAL JOIN "+ pa.darTablaVivienda() 
		+"WHERE id = ?");
		q.setResultClass(Cuarto.class);
		q.setParameters(idCuarto);
		return (Cuarto) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS(AS) Cuartos de la
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Cuarto
	 */
	public List<Cuarto> darCuartos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaCuarto () + " NATURAL JOIN "+ pa.darTablaVivienda() );
		q.setResultClass(Cuarto.class);
		return (List<Cuarto>) q.executeList();
	}



}
