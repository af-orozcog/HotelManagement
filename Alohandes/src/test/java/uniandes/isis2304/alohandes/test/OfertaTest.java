package uniandes.isis2304.alohandes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import oracle.sql.DATE;
import uniandes.isis2304.alohandes.negocio.Alohandes;
import uniandes.isis2304.alohandes.negocio.Oferta;
import uniandes.isis2304.alohandes.negocio.Oferta;
import uniandes.isis2304.alohandes.negocio.Operador;
import uniandes.isis2304.alohandes.negocio.Vivienda;
import uniandes.isis2304.alohandes.persistencia.PersistenciaAlohandes;

public class OfertaTest {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(OfertaTest.class.getName());
	
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD.json"; 
	
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private Alohandes pm;
	
	/** Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar. */
	private JsonObject tableConfig;
	
	/**
	 * Método que prueba las operaciones sobre la tabla oferta
	 * 1. Adicionar un oferta
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un oferta por su identificador
	 * 4. Borrar un oferta por su nombre
	 */
    @Test
	public void CRDOfertaTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre oferta");
			tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
			pm = new Alohandes(tableConfig);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de oferta incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de oferta incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
    		Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
    		Vivienda vi = pm.adicionarCuarto("..", 10, op.getId(), 1, 1, "..", "..");
    		// Lectura de los tipos de bebida con la tabla vacía
			List <Oferta> lista = pm.darOfertas();
			System.out.println("tamAnio al comienzo" + lista.size());
			assertEquals ("No debe haber ofertas creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un oferta adicionado
			
			long precio1 = 1000;
			String periodo1 = "MESES";
			Oferta oferta1 = pm.adicionarOferta(precio1, periodo1, vi.getId(), new DATE(new Timestamp(2000, 1, 1, 1, 0, 0, 0)), new DATE(new Timestamp(2001, 10, 23, 5, 0, 0, 0)));
			lista = pm.darOfertas();
			
			assertEquals ("Debe haber un oferta creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", oferta1.getPeriodo(), lista.get(0).getPeriodo());

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			long precio2 = 11000;
			String periodo2 = "SEMESTRES";
			Oferta oferta2 = pm.adicionarOferta(precio1, periodo1, vi.getId(), new DATE(new Timestamp(2000, 1, 1, 1, 0, 0, 0)),new DATE(new Timestamp(2001, 10, 23, 5, 0, 0, 0)));

			lista = pm.darOfertas();
			assertEquals ("Debe haber dos tipos de bebida creados !!", 2, lista.size ());
			assertTrue ("El primer oferta adicionado debe estar en la tabla", oferta1.getPeriodo().equals(lista.get(0).getPeriodo()) || oferta1.getPeriodo().equals (lista.get(1).getPeriodo()));
			assertTrue ("El segundo oferta adicionado debe estar en la tabla", oferta2.getPeriodo().equals (lista.get (0).getPeriodo()) || oferta2.getPeriodo().equals (lista.get(1).getPeriodo()));

			// Prueba de eliminación de un oferta, dado su identificador
			long tbEliminados = pm.eliminarOfertaPorId (oferta1.getId ());
			assertEquals ("Debe haberse eliminado un oferta !!", 1, tbEliminados);
			lista = pm.darOfertas();
			assertEquals ("Debe haber un solo oferta !!", 1, lista.size ());
			assertFalse ("El primer oferta adicionado NO debe estar en la tabla", oferta1.getId() == lista.get(0).getId());
			assertTrue ("El segundo oferta adicionado debe estar en la tabla", oferta2.getPeriodo().equals (lista.get(0).getPeriodo()));
			
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla oferta.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla oferta");
		}
		finally
		{
			pm.limpiarAlohandes();
    		pm.cerrarUnidadPersistencia ();    		
		}
	}
	
    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de oferta
     */
	@Test
	public void unicidadofertaTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del id del oferta");
			tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
			pm = new Alohandes(tableConfig);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de oferta incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de oferta incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			pm.limpiarAlohandes();
			Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
    		Vivienda vi = pm.adicionarCuarto("..", 10, op.getId(), 1, 1, "..", "..");
    		
			// Lectura de los tipos de bebida con la tabla vacía
			List <Oferta> lista = pm.darOfertas();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());
			pm.modoPruebas();
			pm.asignarID(1000);
			
			long precio1 = 1000;
			String periodo1 = "MESES";
			Oferta oferta1 = pm.adicionarOferta(precio1, periodo1, vi.getId(), new DATE(new Timestamp(2000, 1, 1, 1, 0, 0, 0)), new DATE(new Timestamp(2001, 10, 23, 5, 0, 0, 0)));
			lista = pm.darOfertas();
			assertEquals ("Debe haber un oferta creado !!", 1, lista.size ());
			
			
			long precio2 = 11000;
			String periodo2 = "SEMESTRES";
			Oferta oferta2 = pm.adicionarOferta(precio1, periodo1, vi.getId(), new DATE(new Timestamp(2000, 1, 1, 1, 0, 0, 0)), new DATE(new Timestamp(2001, 10, 23, 5, 0, 0, 0)));

			assertNull ("No puede adicionar dos ofertas con el mismo id !!", oferta2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla oferta.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla oferta");
		}    				
		finally
		{
			pm.limpiarAlohandes();
    		pm.cerrarUnidadPersistencia ();    		
		}
	}
	
	
	/**
	 * Lee datos de configuraciÃ³n para la aplicaciÃ³, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuraciÃ³n deseada
	 * @param archConfig - Archivo Json que contiene la configuraciÃ³n
	 * @return Un objeto JSON con la configuraciÃ³n del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontrÃ³ un archivo de configuraciÃ³n vÃ¡lido: " + tipo);
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontrÃ³ un archivo de configuraciÃ³n vÃ¡lido");			
			JOptionPane.showMessageDialog(null, "No se encontrÃ³ un archivo de configuraciÃ³n de interfaz vÃ¡lido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}
	
}
