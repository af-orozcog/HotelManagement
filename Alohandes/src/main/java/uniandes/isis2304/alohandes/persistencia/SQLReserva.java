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
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Reserva;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBEDOR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLReserva 
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
	public SQLReserva (PersistenciaAlohandes pa)
	{
		this.pa = pa;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un RESERVA a la base de datos de Alohandes
	 * @param idReserva - 
	 * @param inicio - 
	 * @param fin - 
	 * @param duracion - 
	 * @param periodoArrendamiento - 
	 * @param idCliente - 
	 * @param idOferta - 
	 * @param idColectiva 
	 * @return El número de tuplas insertadas 
	 */
	public long adicionarReserva (PersistenceManager pm, long idReserva, Timestamp inicio, Timestamp fin, int duracion, String periodoArrendamiento, long idCliente, long idOferta, long idColectiva)
	{
		if(idColectiva == -1L) {
			Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaReserva () + "(id, inicio, fin, periodo_arrendamiento, cliente, oferta) values (? ,? ,? ,? ,? ,?)");
			q.setParameters( idReserva, inicio, fin, periodoArrendamiento, idCliente, idOferta );
			return (long) q.executeUnique();
		}
		else
		{
			Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaReserva () + "(id, inicio, fin, periodo_arrendamiento, cliente, oferta, colectiva) values (? ,? ,? ,? ,? ,?, ?)");
			q.setParameters( idReserva, inicio, fin, periodoArrendamiento, idCliente, idOferta, idColectiva );
			return (long) q.executeUnique();
		}
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un RESERVA de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idReserva - El identificador de la Reserva
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarReservaPorId (PersistenceManager pm, long idReserva)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaReserva() + " WHERE id = ?");
		q.setParameters(idReserva);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un RESERVA de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idReserva - El identificador de la Reserva
	 * @return El objeto RESERVA que tiene el identificador dado
	 */
	public Reserva darReservaPorId (PersistenceManager pm, long idReserva)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaReserva () + " WHERE id = ?");
		q.setResultClass(Reserva.class);
		q.setParameters(idReserva);
		return (Reserva) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS(AS) Reservas de la
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Reserva
	 */
	public List<Reserva> darReservas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaReserva ());
		q.setResultClass(Reserva.class);
		return (List<Reserva>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informacion de todas las Reservas de un
	 * cliente en la base de datos Alohandes
	 * @param pm - El manejador de persistencia
	 * @param idCliente Identificador del cliente
	 * @return Lista con todas las reservas del cliente buscado
	 */
	public List<Reserva> darReservasPorCliente(PersistenceManager pm, long idCliente) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaReserva () +" WHERE cliente = ?");
		q.setResultClass(Reserva.class);
		q.setParameters(idCliente);
		return (List<Reserva>) q.executeList();
	}


}
