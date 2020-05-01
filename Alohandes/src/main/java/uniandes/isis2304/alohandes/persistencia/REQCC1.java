package uniandes.isis2304.alohandes.persistencia;
import java.util.Calendar;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class REQCC1 {
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
	public REQCC1 (PersistenciaAlohandes pa)
	{
	   this.pa = pa;
	}

	
	/**
	 * 
	 * @param pm
	 * @return
	 */
	public String respuestaSemana(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT to_char(fecha, 'iw') as numero, SUM(cantidad) as monto FROM "
				+ ""
				+ ""
				+ "(" + pa.darTablaGanancias() + " o INNER JOIN "
				+ " GROUP BY to_char(fecha, 'iw') "
				+ "WHERE monto in "
				+ "("
				+ "SELECT MAX(suma) "
				+ "FROM ("
				+ "SELECT SUM(cantidad) as suma FROM "
				+ "" + pa.darTablaGanancias() + " GROUP BY to_char(fecha, 'iw') "
				+ ")"
				+ ")");
		q.setResultClass(Respuesta.class);
		Respuesta ans = (Respuesta)q.executeUnique();
		return "semana: " + ans.getNumero()+ " ganancias obtenidas:" + ans.getMonto();
	}
	
	/**
	 * 
	 * @param pm
	 * @return
	 */
	public String respuestaMes(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT to_char(fecha, 'MM') as numero, SUM(cantidad) as monto FROM "
				+ ""
				+ ""
				+ "" + pa.darTablaGanancias() + " GROUP BY to_char(fecha, 'MM') "
				+ "WHERE monto in "
				+ "("
				+ "SELECT MAX(suma) "
				+ "FROM ("
				+ "SELECT SUM(cantidad) as suma FROM "
				+ "" + pa.darTablaGanancias() + " GROUP BY to_char(fecha, 'MM') "
				+ ")"
				+ ")");
		q.setResultClass(Respuesta.class);
		Respuesta ans = (Respuesta)q.executeUnique();
		return "mes: " + ans.getNumero()+ " ganancias obtenidas:" + ans.getMonto();
	}
	
}
