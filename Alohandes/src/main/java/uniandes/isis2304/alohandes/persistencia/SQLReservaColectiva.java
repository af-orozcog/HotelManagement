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
import java.time.LocalDateTime;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Ganancias;
import uniandes.isis2304.alohandes.negocio.Operador;
import uniandes.isis2304.alohandes.negocio.Reserva;
import uniandes.isis2304.alohandes.negocio.ReservaColectiva;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBEDOR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLReservaColectiva 
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
	public SQLReservaColectiva (PersistenciaAlohandes pa)
	{
		this.pa = pa;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un RESERVA a la base de datos de Alohandes
	 * @param idReserva - 
	 * @param fechaRealizacion - 
	 * @param fin - 
	 * @param duracion - 
	 * @param cantidad - 
	 * @param idCliente - 
	 * @param idOferta - 
	 * @param idColectiva 
	 * @return El número de tuplas insertadas 
	 */
	public long adicionarReservaColectiva (PersistenceManager pm, long idReserva, Timestamp fechaRealizacion, int cantidad, long idCliente)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaReservaColectiva() + "(id, fecha_realizacion, cantidad, cliente) values (? ,? ,? ,?)");
		q.setParameters( idReserva, fechaRealizacion, cantidad, idCliente );
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un RESERVA de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idReserva - El identificador de la Reserva
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarReservaColectivaPorId (PersistenceManager pm, long idReserva)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaReservaColectiva() + " WHERE id = ?");
		q.setParameters(idReserva);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un RESERVA COLECTIVA de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idReserva - El identificador de la Reserva
	 * @return El objeto RESERVA que tiene el identificador dado
	 */
	public ReservaColectiva darReservaPorId (PersistenceManager pm, long idReserva)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaReservaColectiva () + " WHERE id = ?");
		q.setResultClass(Reserva.class);
		q.setParameters(idReserva);
		return (ReservaColectiva) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS(AS) Reservas colectivas de la
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos ReservaColectiva
	 */
	public List<ReservaColectiva> darReservasColectivas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaReservaColectiva());
		q.setResultClass(Reserva.class);
		return (List<ReservaColectiva>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la informacion de todas las Reservas colectivas de un
	 * cliente en la base de datos Alohandes
	 * @param pm - El manejador de persistencia
	 * @param idCliente Identificador del cliente
	 * @return Lista con todas las reservas del cliente buscado
	 */
	public List<Reserva> darReservasColectivasPorCliente(PersistenceManager pm, long idCliente) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaReservaColectiva () +" WHERE cliente = ?");
		q.setResultClass(Reserva.class);
		q.setParameters(idCliente);
		return (List<Reserva>) q.executeList();
	}
	
	public long disminuirCantidadColectiva(PersistenceManager pm, long idColectiva) {
		Integer cantidad = darReservaPorId(pm, idColectiva).getCantidad();
		cantidad--;
		Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaReservaColectiva() + " SET cantidad = ? WHERE id = ?");
		q.setParameters(cantidad, idColectiva);
		return (long) q.executeUnique();
	}


}
