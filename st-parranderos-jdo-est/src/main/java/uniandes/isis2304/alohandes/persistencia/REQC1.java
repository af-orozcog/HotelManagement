package uniandes.isis2304.alohandes.persistencia;

import java.util.Calendar;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class REQC1 {
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
	public REQC1 (PersistenciaAlohandes pa)
	{
	   this.pa = pa;
	}

	/**
	 * regresa las ganancias el año actual
	 * @param pm
	 * @param idOperador
	 * @return
	 */
	public long gananciasAñoActual(PersistenceManager pm,long idOperador) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		Query q = pm.newQuery(SQL, "SELECT  SUM(cantidad) FROM" + pa.darTablaGanancias() + "WHERE id = ? AND año = ?");
		q.setParameters(idOperador,year);
		q.setResultClass(Long.class);
		return (long)q.executeUnique();
	}
	
	/**
	 * regresa las ganacias del año corrido
	 * @param pm
	 * @param idOperador
	 * @return
	 */
	public long gananciasAñoCorrido(PersistenceManager pm,long idOperador) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		if(month == 12) 
			return gananciasAñoActual(pm, idOperador);
		int ar[] = new int[12];
		ar[0] = month;
		for(int i = 1; i < 12;++i) {
			ar[i] = ar[i-1]-1;
			if(ar[i] == 0) ar[i]+= 12;
		}
		Query q = pm.newQuery(SQL, "SELECT SUM(cantidad) FROM" + pa.darTablaGanancias() + "WHERE id = ? AND ((año = ? AND mes >= ?) OR (año = ? AND mes <= ?))");
		q.setParameters(year-1,ar[11],year,ar[0]);
		q.setResultClass(Long.class);
		return (long)q.executeUnique();
	}
}
