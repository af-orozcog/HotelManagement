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

public class REQCEX {

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
	public REQCEX (PersistenciaAlohandes pa)
	{										 
	   this.pa = pa;
	}

	/**
	 * M�todo que devuelve las ofertas por rangos de fechas
	 * @param pm
	 * @param ini
	 * @param fin
	 * @return
	 */
	public void extraOfertasHabilitadas(PersistenceManager pm){
	    
		long time_start = System.currentTimeMillis();
		
		long primero = 1;
	    while(primero < 2000) {
		Query q = pm.newQuery(SQL, ""
				+ "SELECT * FROM " + pa.darTablaOferta()
				+ " WHERE HABILITADA = 1 AND ID > ? AND ROWNUM <= 500"
				+" ORDER BY id"
				);
		q.setParameters(primero);
		q.setResultClass(Oferta.class);
		List<Oferta> ans = (List<Oferta>)q.executeList();
		if(ans.size() > 0)
			for (Oferta oferta : ans) {
				if(oferta.getHabilitada() == 1)
				System.out.println(oferta);
			}
		primero += 500;
		System.out.println("Van " + primero);
	    }   
	    
	    long time_end = System.currentTimeMillis();
	    System.out.println("Tom�:" +(time_end - time_start)+ " milisegundos");
	}
}
