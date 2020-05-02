package uniandes.isis2304.alohandes.persistencia;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Hoteleria;
import uniandes.isis2304.alohandes.negocio.Oferta;

public class REQC5 {

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
	public REQC5 (PersistenciaAlohandes pa)
	{
	   this.pa = pa;
	}

	public List<Oferta> ofertasConPocaDemanda(PersistenceManager pm){
		Timestamp hoy = new Timestamp(System.currentTimeMillis());
		int a�o = hoy.getYear();
		int mes = hoy.getMonth();
		Query q = pm.newQuery(SQL, "SELECT FROM " + pa.darTablaOferta() + "WHERE id NOT IN " +"("
				+ "SELECT o.id,o.precio, o.periodo,o.vivienda,o.fechaInicio,o.fechaFin FROM "
				+ pa.darTablaOferta() + " o, " + pa.darTablaReserva()+ " r"
				+" WHERE "
				+ ")");
		q.setParameters(idOperador,year);
		q.setResultClass(Oferta.class);
		return (List<Oferta>) q.executeList();
	}
}
