package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Cliente;
import uniandes.isis2304.alohandes.negocio.VOVivienda;
import uniandes.isis2304.alohandes.negocio.Vivienda;


public class REQC8 {
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
	public REQC8 (PersistenciaAlohandes pa)
	{
	   this.pa = pa;
	}

	
	/**
	 * 
	 * @param pm
	 * @return
	 */
	public List<Cliente> clientesFrecuentes(PersistenceManager pm, long idVivienda) {
			Query q = pm.newQuery(SQL, "SELECT *\r\n" + 
					"FROM CLIENTE\r\n" + 
					"WHERE id in \r\n" + 
					"(\r\n" + 
					"	SELECT cliente\r\n" + 
					"	FROM \r\n" + 
					"	(\r\n" + 
					"		SELECT re.cliente as cliente\r\n" + 
					"		FROM (RESERVA re INNER JOIN OFERTA o ON o.id = re.oferta)\r\n" + 
					"		WHERE o.vivienda = ?\r\n" + 
					"		GROUP BY re.cliente\r\n" + 
					"		HAVING COUNT(re.id) > 2\r\n" + 
					"	)\r\n" + 
					"	UNION\r\n" + 
					"	(\r\n" + 
					"		SELECT cliente as cliente\r\n" + 
					"		FROM(\r\n" + 
					"			SELECT re.cliente as cliente,TRUNC(re.fin) - TRUNC(re.inicio) as days\r\n" + 
					"			FROM (RESERVA re INNER JOIN OFERTA o ON o.id = re.oferta)\r\n" + 
					"			WHERE o.vivienda = ?\r\n" + 
					"			GROUP BY re.cliente,re.inicio,re.fin\r\n" + 
					"			)\r\n" + 
					"		GROUP BY cliente\r\n" + 
					"		HAVING SUM(days) > 14\r\n" + 
					"	)\r\n" + 
					")");
			q.setParameters(idVivienda,idVivienda);
			q.setResultClass(Cliente.class);
			return (List<Cliente>) q.executeList();
	}
}
