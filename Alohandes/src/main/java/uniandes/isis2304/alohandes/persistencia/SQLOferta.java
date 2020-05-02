/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogot谩	- Colombia)
 * Departamento	de	Ingenier铆a	de	Sistemas	y	Computaci贸n
 * Licenciado	bajo	el	esquema	Academic Free License versi贸n 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germ谩n Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jim茅nez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.alohandes.persistencia;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Ganancias;
import uniandes.isis2304.alohandes.negocio.Oferta;
import uniandes.isis2304.alohandes.negocio.Operador;
import uniandes.isis2304.alohandes.negocio.Oferta;

/**
 * Clase que encapsula los m茅todos que hacen acceso a la base de datos para el concepto BEBEDOR de Parranderos
 * N贸tese que es una clase que es s贸lo conocida en el paquete de persistencia
 * 
 * @author Germ谩n Bravo
 */
class SQLOferta 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra ac谩 para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaAlohandes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicaci贸n
	 */
	protected PersistenciaAlohandes pa;

	/* ****************************************************************
	 * 			M茅todos
	 *****************************************************************/

	/** 
	* Constructor
	* @param pa - El Manejador de persistencia de la aplicaci贸n
	*/
	public SQLOferta (PersistenciaAlohandes pa)
	{
	   this.pa = pa;
	}

	/**
	* Crea y ejecuta la sentencia SQL para adicionar un OFERTA a la base de datos de Alohandes
	* @param idOferta - 
	* @param precio - 
	* @param periodo - 
	* @param idVivienda - 
	* @param fechaInicio - 
	* @param fechaFin - 
	* @return El n煤mero de tuplas insertadas 
	*/
	public long adicionarOferta (PersistenceManager pm, long idOferta, long precio, String periodo, long idVivienda, Timestamp fechaInicio, Timestamp fechaFin)
	{
	   Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaOferta () + "(id, precio, periodo, vivienda, fechainicio, fechafin, habilitada) values (? ,? ,? ,? ,? ,? ,?)");
	   q.setParameters( idOferta, precio, periodo, idVivienda, fechaInicio, fechaFin, 1 );
	    return (long) q.executeUnique();
	}

	/**
	* Crea y ejecuta la sentencia SQL para eliminar un OFERTA de la base de datos de Alohandes, por su identificador
	* @param pm - El manejador de persistencia
	* @param idOferta - El identificador de la Oferta
	* @return EL n煤mero de tuplas eliminadas
	*/
	public long eliminarOfertaPorId (PersistenceManager pm, long idOferta)
	{
	    Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOferta () + " WHERE id = ?");
	    q.setParameters(idOferta);
	    return (long) q.executeUnique();
	}

	/**
	* Crea y ejecuta la sentencia SQL para encontrar la informaci贸n de un OFERTA de la 
	* base de datos de Alohandes, por su identificador
	* @param pm - El manejador de persistencia
	* @param idOferta - El identificador de la Oferta
	* @return El objeto OFERTA que tiene el identificador dado
	*/
	public Oferta darOfertaPorId (PersistenceManager pm, long idOferta)
	{
		System.out.println("cual es el re puto ID " + idOferta);
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta () + " WHERE id = ?");
		List<Oferta> ofe = darOfertas(pm);
		q.setResultClass(Oferta.class);
		q.setParameters(idOferta);
		Oferta what = (Oferta)q.executeUnique();
		//if(what == null) System.out.println("WTDDDDFDFDFDD");
		return what;
	}


	/**
	* Crea y ejecuta la sentencia SQL para encontrar la informaci贸n de LOS(AS) Ofertas de la
	* base de datos de Alohandes
	* @param pm - El manejador de persistencia
	* @return Una lista de objetos Oferta
	*/
	public List<Oferta> darOfertas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta ());
		q.setResultClass(Oferta.class);
		return (List<Oferta>) q.executeList();
	}

	public List<Oferta> darOfertasConServicios(PersistenceManager pm, ArrayList<String> lista) {
		
		List<Oferta> ofertas = darOfertas(pm);
		for (String servicio : lista) {
			Query q = pm.newQuery(SQL, "SELECT DISTINCT o.id, o.precio, o.periodo, o.vivienda, o.fechainicio, o.fechafin "
					+ "FROM " + pa.darTablaOferta ()+" o, " + pa.darTablaIncluye() + " i, " + pa.darTablaServicio() + "s "
					+ "WHERE o.id = i.oferta AND i.servicio = s.id AND s.nombre <> ?"
					+ " AND fechaInicio > ? AND fechaFin < ? AND habilitado = 1 AND o.habilitada = 0 AND o.id NOT IN"
					+ "( SELECT o.id FROM "+ pa.darTablaOferta() + " o, " + pa.darTablaReserva()+ " r"
					+" WHERE o.id = r.oferta AND ((r.inicio < ?  AND r.inicio > ?) OR (r.inicio > ? AND r.fin < ?))"
					+ ")");
			q.setResultClass(Oferta.class);
			q.setParameters(servicio);
			List<Oferta> eliminar = q.executeList();
			for (Oferta el : eliminar) {
				ofertas.remove(el);
			}
		}
		
		return ofertas;
	}
	
public List<Oferta> darOfertasConServiciosYTipo(PersistenceManager pm, ArrayList<String> lista, String tipo, String periodo) {
		
		List<Oferta> ofertas = darOfertas(pm);
		for (String servicio : lista) {
			Query q = pm.newQuery(SQL, "SELECT DISTINCT o.id, o.precio, o.periodo, o.vivienda, o.fechainicio, o.fechafin "
					+ "o.habilitada FROM " + pa.darTablaOferta ()+" o, " + pa.darTablaIncluye() + " i, " + pa.darTablaServicio() + "s, " + pa.darTablaVivienda() + "v, " + pa.darTablaOperador() + "op "
					+ "WHERE o.id = i.oferta AND i.servicio = s.id AND o.habilitada = 0 AND s.nombre <> ? AND op.tipo_operador <> ? AND op.periodo <> ?"
					+ " AND fechaInicio > ? AND fechaFin < ? AND habilitado = 1 AND o.id NOT IN"
					+ "( SELECT o.id FROM "+ pa.darTablaOferta() + " o, " + pa.darTablaReserva()+ " r"
					+" WHERE o.id = r.oferta AND ((r.inicio < ?  AND r.inicio > ?) OR (r.inicio > ? AND r.fin < ?))"
					+ ")");
			q.setResultClass(Oferta.class);
			q.setParameters(servicio, tipo, periodo );
			List<Oferta> eliminar = q.executeList();
			for (Oferta el : eliminar) {
				ofertas.remove(el);
			}
		}
		return ofertas;
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informacion de todas las Ofertas de un
	 * operador en la base de datos Alohandes
	 * @param pm - El manejador de persistencia
	 * @param idOperador Identificador del operador
	 * @return Lista con todas las ofertas del operador buscado
	 */
	public List<Oferta> darOfertasPorOperador(PersistenceManager pm, long idOperador) {
		Query q = pm.newQuery(SQL, "SELECT o.id,o.precio, o.periodo,o.vivienda,o.fechaInicio,o.fechaFin  FROM " + pa.darTablaOferta () +" o, "+ pa.darTablaVivienda() +" v "
				+ " WHERE o.vivienda = v.id AND v.operador = ?");
		q.setResultClass(Oferta.class);
		q.setParameters(idOperador);
		return (List<Oferta>) q.executeList();
	}

	/**
	 * M閠odo que devuelve las ofertas por rangos de fechas
	 * @param pm
	 * @param inicio
	 * @param fin
	 * @return
	 */
	public Oferta darOfertasPorRangoFechaDisponibles(PersistenceManager pm, Timestamp inicio, Timestamp fin){
		Query q = pm.newQuery(SQL, ""
				+ "SELECT * FROM " + pa.darTablaOferta()
				+ " WHERE fechaInicio <= ? AND fechaFin >= ? AND habilitado = 1 AND id NOT IN"
				+ "("
				+ "SELECT o.id FROM "
				+ pa.darTablaOferta() + " o, " + pa.darTablaReserva()+ " r"
				+" WHERE o.id = r.oferta AND ((r.inicio >= ?  AND r.inicio <= ?) OR (r.inicio <= ? AND r.fin >= ?))"
				+ ")"
				);
		q.setParameters(inicio,fin,inicio,fin,inicio,inicio);
		List<Oferta> ans = (List<Oferta>)q.executeList();
		if(ans.size() > 0)
			return ans.get(0);
		return null;
	}
	
	/**
	 * M閠odo que cambia el atributo de habilitado de una oferta
	 * @param pm
	 * @param idOferta
	 * @return
	 */
	public long deshabilitarOferta(PersistenceManager pm, long idOferta) {
		Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaOferta () + " "
				+ "SET habilitada = 0"
				+ " WHERE id = ?");
		q.setParameters(idOferta);
	    return (long) q.executeUnique();
	}
	
	/**
	 * M閠odo que cambia el atributo de habilitado de una oferta
	 * @param pm
	 * @param idOferta
	 * @return
	 */
	public long habilitarOferta(PersistenceManager pm, long idOferta) {
		Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaOferta () + " "
				+ "SET habilitada = 1"
				+ " WHERE id = ?");
		q.setParameters(idOferta);
	    return (long) q.executeUnique();
	}
}
