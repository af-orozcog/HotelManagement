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

import uniandes.isis2304.alohandes.negocio.Incluye;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto SIRVEN de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLIncluye 
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
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLIncluye (PersistenciaAlohandes pa)
	{
		this.pa = pa;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un SIRVEN a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idServicio - El identificador del servicio
	 * @param idOferta - El identificador de la oferta
	 * @param incluido - si el servicio está incluido en el precio de la oferta
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarIncluye (PersistenceManager pm, long idServicio, long idOferta, boolean incluido) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaIncluye () + "(servicio, oferta, incluido) values (?, ?, ?)");
        q.setParameters(idServicio, idOferta, incluido);
        return (long)q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN SIRVEN de la base de datos de Parranderos, por sus identificador
	 * @param pm - El manejador de persistencia
	 * @param idServicio - El identificador del servicio
	 * @param idOferta - El identificador de la oferta
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarIncluye (PersistenceManager pm, long idServicio, long idOferta) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaIncluye () + " WHERE servicio = ? AND oferta = ?");
        q.setParameters(idServicio, idOferta);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los SIRVEN de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos SIRVEN
	 */
	public List<Incluye> darIncluye (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaIncluye ());
		q.setResultClass(Incluye.class);
		return (List<Incluye>) q.execute();
	}
 
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar el identificador y el número de ofertas que inlcuye los servicioes de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de parejas de objetos, el primer elemento de cada pareja representa el identificador de un servicio,
	 * 	el segundo elemento representa el número de ofertas que sirve (Una oferta que se sirve en dos horarios cuenta dos veces)
	 */
	public List<Object []> darServiciosYCantidadOfertasIncluye (PersistenceManager pm)
	{
        String sql = "SELECT servicio, count (*) as numOfertas";
        sql += " FROM " + pa.darTablaIncluye ();
       	sql	+= " GROUP BY servicio";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}

}
