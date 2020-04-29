package uniandes.isis2304.alohandes.persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Oferta;

public class REQC2 {
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
	public REQC2(PersistenciaAlohandes pa)
	{
	   this.pa = pa;
	}

	/**
	 * 
	 * @param pm
	 * @return
	 */
	public List<Oferta> ofertasPopulares(PersistenceManager pm){
		ArrayList<Oferta> answer = new ArrayList<Oferta>();
		List<Long> see = idOfertasPopulares(pm);
		Iterator<Long> it = see.iterator();
		while(it.hasNext()) {
			answer.add(pa.darOfertaPorId(it.next()));
		}
		return answer;
	}
	
	/**
	 * 
	 * @param pm
	 * @return
	 */
	public List<Long> idOfertasPopulares(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT oferta FROM "
												+ "(SELECT oferta, COUNT(id) as conteo"
												+ " FROM " + pa.darTablaReserva()
												+ " GROUP BY oferta"
												+ " ORDER BY conteo DESC"
												+ ")" + 
									 " WHERE ROWNUM < 21");
		q.setResultClass(Long.class);
		return (List<Long>) q.executeList();
	}
}
