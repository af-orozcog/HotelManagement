package uniandes.isis2304.alohandes.persistencia;
import java.util.Calendar;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class REFC7 {
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
	public REFC7 (PersistenciaAlohandes pa)
	{
	   this.pa = pa;
	}

	
	/**
	 * 
	 * @param pm
	 * @return
	 */
	public String respuestaSemanaGanancias(PersistenceManager pm, String tipoAlojamiento) {
		long va = 0;
		int semana = 0;
		for(int i = 1; i < 53;++i) {
			Query q = pm.newQuery(SQL, "SELECT to_char(fecha, 'iw') as numero, SUM(cantidad) as monto FROM "
					+ ""
					+ ""
					+ "( " + pa.darTablaGanancias() + " g INNER JOIN " + pa.darTablaVivienda() + " v ON g.operador = v.opeador ) " 
					+ " WHERE v.tipo = ?  AND to_number(to_char(g.fecha, 'iw')) = ? "
					+ " GROUP BY to_char(g.fecha, 'iw') "
					);
			q.setParameters(tipoAlojamiento,i);
			q.setResultClass(Long.class);
			Long ans = (Long)q.executeUnique();
			if(ans == null) ans = new Long(0);
			if(va < ans) {
				ans = va;
				semana = i;
			}
		}
		
		return "semana: " + semana + " ganancias de esa semana: " + va;
	}
	
	/**
	 * 
	 * @param pm
	 * @return
	 */
	public String respuestaMesGanacias(PersistenceManager pm, String tipoAlojamiento) {
		long va = 0;
		int mes = 0;
		for(int i = 1; i < 13;++i) {
			Query q = pm.newQuery(SQL, "SELECT SUM(cantidad) as monto FROM "
					+ ""
					+ ""
					+ "( " + pa.darTablaGanancias() + " g INNER JOIN " + pa.darTablaVivienda() + " v ON g.operador = v.opeador ) " 
					+ "WHERE v.tipo = ?  AND to_number(to_char(g.fecha, 'MM')) = ? "
					+ " GROUP BY to_char(g.fecha, 'MM') "
					);
			q.setParameters(tipoAlojamiento,i);
			q.setResultClass(Long.class);
			Long ans = (Long)q.executeUnique();
			if(ans == null) ans = new Long(0);
			if(ans > va) {
				va = ans;
				mes = i;
			}
		}
		return "mes: " + mes + " ganancias de ese mes: " + va;
	}
	
	/**
	 * 
	 * @param pm
	 * @return
	 */
	public String respuestaSemanaMayorDemanda(PersistenceManager pm, String tipoAlojamiento) {
		long va = 0;
		int semana = 0;
		for(int i = 1; i < 53;++i) {
			Query q = pm.newQuery(SQL, "SELECT COUNT(re.id) as monto FROM "
					+ ""
					+ ""
					+ "((RESERVA re INNER JOIN OFERTA o ON re.oferta = o.id) aux1 INNER JOIN VIVIENDA vi ON vi.id = aux1.vivienda)" 
					+ "WHERE vi.tipo = ?  AND TO_NUMBER(to_char(re.inicio, 'iw')) <= ? AND ? <= TO_NUMBER(to_char(re.inicio, 'iw')) "
					);
			q.setParameters(tipoAlojamiento,i);
			q.setResultClass(Long.class);
			Long ans = (Long)q.executeUnique();
			if(ans == null) ans = new Long(0);
			if(va < ans) {
				ans = va;
				semana = i;
			}
		}
		
		return "semana: " + semana + " demanda de esa semana: " + va;
	}
	
	/**
	 * 
	 * @param pm
	 * @return
	 */
	public String respuestaMesMayorDemanda(PersistenceManager pm, String tipoAlojamiento) {
		long va = 0;
		int mes = 0;
		for(int i = 1; i < 13;++i) {
			Query q = pm.newQuery(SQL, "SELECT COUNT(re.id) as monto FROM "
					+ ""
					+ ""
					+ "((RESERVA re INNER JOIN OFERTA o ON re.oferta = o.id) aux1 INNER JOIN VIVIENDA vi ON vi.id = aux1.vivienda) " 
					+ "WHERE v.tipo = ?  AND to_number(to_char(re.fecha, 'MM')) <= ? AND ? <= to_number(to_char(re.fecha, 'MM'))"
					);
			q.setParameters(tipoAlojamiento,i);
			q.setResultClass(Long.class);
			Long ans = (Long)q.executeUnique();
			if(ans == null) ans = new Long(0);
			if(va < ans) {
				ans = va;
				mes = i;
			}
		}
		return "mes: " + mes + " demanda de ese mes: " + va;
	}
	
	/**
	 * 
	 * @param pm
	 * @return
	 */
	public String respuestaSemanaMenorDemanda(PersistenceManager pm, String tipoAlojamiento) {
		long va = 10000000000L;
		int semana = 0;
		for(int i = 1; i < 53;++i) {
			Query q = pm.newQuery(SQL, "SELECT COUNT(re.id) as monto FROM "
					+ ""
					+ ""
					+ "((RESERVA re INNER JOIN OFERTA o ON re.oferta = o.id) aux1 INNER JOIN VIVIENDA vi ON vi.id = aux1.vivienda) " 
					+ "WHERE vi.tipo = ?  AND TO_NUMBER(to_char(re.inicio, 'iw')) <= ? AND ? <= TO_NUMBER(to_char(re.inicio, 'iw')) "
					);
			q.setParameters(tipoAlojamiento,i);
			q.setResultClass(Long.class);
			Long ans = (Long)q.executeUnique();
			if(ans == null) ans = new Long(0);
			if(va > ans) {
				ans = va;
				semana = i;
			}
		}
		
		return "semana: " + semana + " demanda de esa semana: " + va;
	}
	
	/**
	 * 
	 * @param pm
	 * @return
	 */
	public String respuestaMesMenorDemanda(PersistenceManager pm, String tipoAlojamiento) {
		long va = 10000000000L;
		int mes = 0;
		for(int i = 1; i < 13;++i) {
			Query q = pm.newQuery(SQL, "SELECT COUNT(re.id) as monto FROM "
					+ ""
					+ ""
					+ "((RESERVA re INNER JOIN OFERTA o ON re.oferta = o.id) aux1 INNER JOIN VIVIENDA vi ON vi.id = aux1.vivienda) " 
					+ "WHERE v.tipo = ?  AND to_number(to_char(re.fecha, 'MM')) <= ? AND ? <= to_number(to_char(re.fecha, 'MM'))"
					);
			q.setParameters(tipoAlojamiento,i);
			q.setResultClass(Long.class);
			Long ans = (Long)q.executeUnique();
			if(ans == null) ans = new Long(0);
			if(va > ans) {
				ans = va;
				mes = i;
			}
		}
		return "mes: " + mes + " demanda de ese mes: " + va;
	}
	
}
