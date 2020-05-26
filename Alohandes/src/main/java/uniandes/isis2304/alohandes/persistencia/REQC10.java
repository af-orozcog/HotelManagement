package uniandes.isis2304.alohandes.persistencia;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import oracle.sql.DATE;
import uniandes.isis2304.alohandes.negocio.Cliente;
import uniandes.isis2304.alohandes.negocio.Hoteleria;
import uniandes.isis2304.alohandes.negocio.Oferta;
import uniandes.isis2304.alohandes.negocio.Vivienda;

public class REQC10 {

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
	public REQC10 (PersistenciaAlohandes pa)
	{										 
	   this.pa = pa;
	}

	public List<Cliente> darClientesReservaFechas(PersistenceManager pm, DATE inicio, DATE fin, long tipo, String ad){
		Query q = pm.newQuery(SQL, "SELECT DISTINCT c.id, c.nombre, c.email, c.numero, c.documento, c.tipo_cliente "
				+ "FROM " + pa.darTablaCliente() + " c, " + pa.darTablaReserva() + " r, " + pa.darTablaOferta() + " o "
				+ "WHERE c.id = r.cliente AND o.id = r.oferta AND o.id = ? AND "
				+ "r.inicio BETWEEN ? AND ? AND "
				+ "r.fin BETWEEN ? AND ? " + ad);
		q.setResultClass(Cliente.class);
		q.setParameters(tipo, inicio, fin, inicio, fin);
		return (List<Cliente>) q.executeList();
	}
}
