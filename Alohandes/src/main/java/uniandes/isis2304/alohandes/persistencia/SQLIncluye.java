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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.junit.internal.runners.model.EachTestNotifier;

import oracle.net.aso.i;
import oracle.sql.DATE;
import uniandes.isis2304.alohandes.negocio.Incluye;
import uniandes.isis2304.alohandes.negocio.Oferta;

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
	public long adicionarIncluye (PersistenceManager pm, long idServicio, long idOferta, int incluido) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaIncluye () + "(servicio, oferta, incluido) values (?, ?, ?)");
		q.setParameters(idOferta, idServicio, incluido);
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

	public List<Long> darOfertasConServicios(PersistenceManager pm, ArrayList<String> lista, DATE inicio, DATE fin) {
		Query q = pm.newQuery(SQL, "SELECT i.oferta, s.nombre"
				+ " FROM " + pa.darTablaIncluye() + " i, " + pa.darTablaServicio() + " s, " + pa.darTablaOferta() + " o"
				+ " WHERE i.servicio = s.id AND i.oferta = o.id AND o.habilitada = 1"
				+ " AND o.fechaInicio <= ? AND o.fechaFin >= ? AND o.id NOT IN" 
				+ " ("
				+ " SELECT o.id FROM "+ pa.darTablaOferta() + " o, " + pa.darTablaReserva()+ " r"
				+ " WHERE o.id = r.oferta AND ((r.inicio >= ?  AND r.inicio <= ?) OR (r.inicio <= ? AND r.fin >= ?))"
				+ " )"
				+ " ORDER BY i.oferta");
		q.setParameters( inicio,fin,inicio,fin,inicio,inicio);
		List<Object[]> ans = q.executeList();

		List<Long> ofertas = new LinkedList<Long>();

		if(ans == null || ans.size() == 0)
			return null;
		Long act = ((BigDecimal) ans.get(0)[0]).longValue();
		boolean valid = true;
		List<String> servicios = new LinkedList<String>();

		for (Object[] objects: ans) {
			Long read = ((BigDecimal) objects[0]).longValue();
			if(read == act) 
				servicios.add( objects[1].toString() );
			else {
				if(lista != null)
					for (String servicio : lista) {
						if(!listaContieneString(servicio, servicios)) {
							valid = false;
							break;
						}
					}
				if(valid)
					ofertas.add(read);
				act = read;
				servicios = new LinkedList<String>();
				servicios.add( objects[1].toString() );
			}
		}
		if(lista !=null)
			for (String servicio : lista) {
				if(!listaContieneString(servicio, servicios)) {
					valid = false;
					break;
				}
			}
		if(valid)
			ofertas.add(act);
		return ofertas;
	}

	public List<Long> darOfertasConServiciosYTipo(PersistenceManager pm, ArrayList<String> lista, String tipo, String periodo, DATE inicio, DATE fin) {
		Query q = pm.newQuery(SQL, "SELECT i.oferta, s.nombre"
				+ " FROM " + pa.darTablaIncluye() + " i, " + pa.darTablaServicio() + " s, " + pa.darTablaOferta() + " o, " + pa.darTablaVivienda() + " v"
				+ " WHERE i.servicio = s.id AND i.oferta = o.id AND o.vivienda = v.id AND o.habilitada = 1"
				+ " AND v.tipo = ? AND o.periodo = ? AND o.fechaInicio <= ? AND o.fechaFin >= ? AND o.id NOT IN" 
				+ " ("
				+ " SELECT o.id FROM "+ pa.darTablaOferta() + " o, " + pa.darTablaReserva()+ " r"
				+ " WHERE o.id = r.oferta AND ((r.inicio >= ?  AND r.inicio <= ?) OR (r.inicio <= ? AND r.fin >= ?))"
				+ " )"
				+ " ORDER BY i.oferta");
		q.setParameters(tipo, periodo, inicio,fin,inicio,fin,inicio,inicio);
		List<Object[]> ans = q.executeList();

		List<Long> ofertas = new LinkedList<Long>();

		
		Long act = ((BigDecimal) ans.get(0)[0]).longValue();
		boolean valid = true;
		List<String> servicios = new LinkedList<String>();

		for (Object[] objects: ans) {
			Long read = ((BigDecimal) objects[0]).longValue();
			if(read == act) 
				servicios.add( objects[1].toString() );
			else {
				if(lista != null)
					for (String servicio : lista) {
						if(!listaContieneString(servicio, servicios)) {
							valid = false;
							break;
						}
					}
				if(valid)
					ofertas.add(read);
				act = read;
				servicios = new LinkedList<String>();
				servicios.add( objects[1].toString() );
			}
		}
		if(lista !=null)
			for (String servicio : lista) {
				if(!listaContieneString(servicio, servicios)) {
					valid = false;
					break;
				}
			}
		if(valid)
			ofertas.add(act);
		return ofertas;
	}
	
	public List<Oferta> darOfertasConServiciosYTipo(PersistenceManager pm, String tipo, String periodo, DATE inicio, DATE fin) {
		Query q = pm.newQuery(SQL, "SELECT o.id, o.PRECIO, o.PERIODO, o.VIVIENDA, o.FECHAINICIO, o.FECHAFIN, o.HABILITADA"
				+ " FROM " + pa.darTablaOferta() + " o, " + pa.darTablaVivienda() + " v"
				+ " WHERE o.vivienda = v.id AND o.habilitada = 1"
				+ " AND v.tipo = ? AND o.periodo = ? AND o.fechaInicio <= ? AND o.fechaFin >= ? AND o.id NOT IN" 
				+ " ("
				+ " SELECT o.id FROM "+ pa.darTablaOferta() + " o, " + pa.darTablaReserva()+ " r"
				+ " WHERE o.id = r.oferta AND ((r.inicio >= ?  AND r.inicio <= ?) OR (r.inicio <= ? AND r.fin >= ?))"
				+ " )"
				+ " ORDER BY o.id");
		q.setParameters(tipo, periodo, inicio,fin,inicio,fin,inicio,inicio);
		q.setClass(Oferta.class);
		List<Oferta> ofertas = q.executeList();
		return ofertas;
	}

	
	
	private boolean listaContieneString (String buscado, List<String> servicios) {
		for (String string : servicios)
			if(string.contains(buscado))
				return true;
		return false;
	}
}
