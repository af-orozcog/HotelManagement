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

	
	String months[] = {"01","02","03","04","05","06","07","08","09","10","11","12"};
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Clase auxiliar
	 */
	
	public class Respuesta{
		
		private Integer numero;
		
		private Long monto;
		
		public Respuesta(Integer numero,Long monto) {
			this.numero = numero;
			this.monto = monto;
		}
		
		public void setNumero(Integer numero) {
			this.numero = numero;
		}
		
		public Integer getNumero() {
			return numero;
		}
		
		public void setMonto(Long monto) {
			this.monto = monto;
		}
		
		public Long getMonto() {
			return monto;
		}
	}
	
	
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
		Respuesta def = null;
		for(int i = 1; i < 53;++i) {
			Query q = pm.newQuery(SQL, "SELECT to_char(fecha, 'iw') as numero, SUM(cantidad) as monto FROM "
					+ ""
					+ ""
					+ "( " + pa.darTablaGanancias() + " g INNER JOIN " + pa.darTablaVivienda() + " v ON g.operador = v.opeador ) " 
					+ " WHERE v.tipo = ?  AND to_char(g.fecha, 'iw') = ? "
					+ " GROUP BY to_char(g.fecha, 'iw') "
					);
			q.setParameters(tipoAlojamiento,i);
			q.setResultClass(Respuesta.class);
			Respuesta ans = (Respuesta)q.executeUnique();
			if(ans != null) {
				if(def == null) def = ans;
				else if(def.getMonto() < ans.getMonto()) def = ans;
			}
		}
		return "semana: " + def.getNumero() + " ganancias de esa semana: " + def.getMonto();
	}
	
	/**
	 * 
	 * @param pm
	 * @return
	 */
	public String respuestaMesGanacias(PersistenceManager pm, String tipoAlojamiento) {
		Respuesta def = null;
		for(int i = 0; i < 12;++i) {
			Query q = pm.newQuery(SQL, "SELECT to_char(fecha, 'MM') as numero, SUM(cantidad) as monto FROM "
					+ ""
					+ ""
					+ "( " + pa.darTablaGanancias() + " g INNER JOIN " + pa.darTablaVivienda() + " v ON g.operador = v.opeador ) " 
					+ "WHERE v.tipo = ?  AND to_char(g.fecha, 'MM') = ? "
					+ " GROUP BY to_char(g.fecha, 'MM') "
					);
			q.setParameters(tipoAlojamiento,months[i]);
			q.setResultClass(Respuesta.class);
			Respuesta ans = (Respuesta)q.executeUnique();
			if(ans != null) {
				if(def == null) def = ans;
				else if(def.getMonto() < ans.getMonto()) def = ans;
			}
		}
		return "mes: " + def.getNumero() + " ganancias de ese mes: " + def.getMonto();
	}
	
	/**
	 * 
	 * @param pm
	 * @return
	 */
	public String respuestaSemanaMayorDemanda(PersistenceManager pm, String tipoAlojamiento) {
		Respuesta def = null;
		for(int i = 1; i < 53;++i) {
			Query q = pm.newQuery(SQL, "SELECT to_char(fecha, 'iw') as numero, SUM(re.id) as monto FROM "
					+ ""
					+ ""
					+ "((RESERVA re INNER JOIN OFERTA o ON re.oferta = o.id) aux1 INNER JOIN VIVIENDA vi ON vi.id = aux1.vivienda)" 
					+ "WHERE vi.tipo = ?  AND TO_NUMBER(to_char(re.inicio, 'iw')) <= ? AND ? <= TO_NUMBER(to_char(re.inicio, 'iw')) "
					);
			q.setParameters(tipoAlojamiento,i);
			q.setResultClass(Respuesta.class);
			Respuesta ans = (Respuesta)q.executeUnique();
			if(ans != null) {
				if(def == null) def = ans;
				else if(def.getMonto() < ans.getMonto()) def = ans;
			}
		}
		return "semana: " + def.getNumero() + " demanda de esa semana: " + def.getMonto();
	}
	
	/**
	 * 
	 * @param pm
	 * @return
	 */
	public String respuestaMesMayorDemanda(PersistenceManager pm, String tipoAlojamiento) {
		Respuesta def = null;
		for(int i = 1; i < 13;++i) {
			Query q = pm.newQuery(SQL, "SELECT to_char(fecha, 'MM') as numero, SUM(*) as monto FROM "
					+ ""
					+ ""
					+ "((RESERVA re INNER JOIN OFERTA o ON re.oferta = o.id) aux1 INNER JOIN VIVIENDA vi ON vi.id = aux1.vivienda)" 
					+ "WHERE v.tipo = ?  AND to_number(to_char(re.fecha, 'MM')) <= ? AND ? <= to_number(to_char(re.fecha, 'MM'))"
					);
			q.setParameters(tipoAlojamiento,i);
			q.setResultClass(Respuesta.class);
			Respuesta ans = (Respuesta)q.executeUnique();
			if(ans != null) {
				if(def == null) def = ans;
				else if(def.getMonto() < ans.getMonto()) def = ans;
			}
		}
		return "mes: " + def.getNumero() + " demanda de ese mes: " + def.getMonto();
	}
	
	/**
	 * 
	 * @param pm
	 * @return
	 */
	public String respuestaSemanaMenorDemanda(PersistenceManager pm, String tipoAlojamiento) {
		Respuesta def = null;
		for(int i = 1; i < 53;++i) {
			Query q = pm.newQuery(SQL, "SELECT to_char(fecha, 'iw') as numero, SUM(re.id) as monto FROM "
					+ ""
					+ ""
					+ "((RESERVA re INNER JOIN OFERTA o ON re.oferta = o.id) aux1 INNER JOIN VIVIENDA vi ON vi.id = aux1.vivienda)" 
					+ "WHERE vi.tipo = ?  AND TO_NUMBER(to_char(re.inicio, 'iw')) <= ? AND ? <= TO_NUMBER(to_char(re.inicio, 'iw')) "
					);
			q.setParameters(tipoAlojamiento,i);
			q.setResultClass(Respuesta.class);
			Respuesta ans = (Respuesta)q.executeUnique();
			if(ans != null) {
				if(def == null) def = ans;
				else if(def.getMonto() > ans.getMonto()) def = ans;
			}
		}
		return "semana: " + def.getNumero() + " demanda de esa semana: " + def.getMonto();
	}
	
	/**
	 * 
	 * @param pm
	 * @return
	 */
	public String respuestaMesMenorDemanda(PersistenceManager pm, String tipoAlojamiento) {
		Respuesta def = null;
		for(int i = 1; i < 13;++i) {
			Query q = pm.newQuery(SQL, "SELECT to_char(fecha, 'MM') as numero, SUM(*) as monto FROM "
					+ ""
					+ ""
					+ "((RESERVA re INNER JOIN OFERTA o ON re.oferta = o.id) aux1 INNER JOIN VIVIENDA vi ON vi.id = aux1.vivienda)" 
					+ "WHERE v.tipo = ?  AND to_number(to_char(re.fecha, 'MM')) <= ? AND ? <= to_number(to_char(re.fecha, 'MM'))"
					);
			q.setParameters(tipoAlojamiento,i);
			q.setParameters(tipoAlojamiento,months[i]);
			q.setResultClass(Respuesta.class);
			Respuesta ans = (Respuesta)q.executeUnique();
			if(ans != null) {
				if(def == null) def = ans;
				else if(def.getMonto() > ans.getMonto()) def = ans;
			}
		}
		return "mes: " + def.getNumero() + " demanda de ese mes: " + def.getMonto();
	}
	
}
