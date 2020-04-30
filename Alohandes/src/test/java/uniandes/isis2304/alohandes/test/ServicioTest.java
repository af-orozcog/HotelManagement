package uniandes.isis2304.alohandes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.alohandes.negocio.Alohandes;
import uniandes.isis2304.alohandes.negocio.Servicio;
import uniandes.isis2304.alohandes.negocio.Operador;

public class ServicioTest {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(ServicioTest.class.getName());
	
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD.json"; 
	
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private Alohandes pm;
	
	/** Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar. */
	private JsonObject tableConfig;
	
	/**
	 * Método que prueba las operaciones sobre la tabla servicio
	 * 1. Adicionar un servicio
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un servicio por su identificador
	 * 4. Borrar un servicio por su nombre
	 */
    @Test
	public void CRDServicioTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre servicio");
			tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
			pm = new Alohandes(tableConfig);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de servicio incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de servicio incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <Servicio> lista = pm.darServicios();
			System.out.println("tamaño al comienzo" + lista.size());
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un servicio adicionado
			
			String nombre1 = "nom1";
			long costo1 = 1000;
			Servicio servicio1 = pm.adicionarServicio(nombre1, costo1);
			lista = pm.darServicios();
			
			assertEquals ("Debe haber un servicio creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", servicio1.getNombre(), lista.get(0).getNombre());

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			String nombre2 = "nom2";
			long costo2 = 20200;
			Servicio servicio2 = pm.adicionarServicio(nombre2, costo2);
			
			lista = pm.darServicios();
			assertEquals ("Debe haber dos tipos de bebida creados !!", 2, lista.size ());
			assertTrue ("El primer servicio adicionado debe estar en la tabla", servicio1.getNombre().equals(lista.get(0).getNombre()) || servicio1.getNombre().equals (lista.get(1).getNombre()));
			assertTrue ("El segundo servicio adicionado debe estar en la tabla", servicio2.getNombre().equals(lista.get(0).getNombre()) || servicio2.getNombre().equals (lista.get(1).getNombre()));

			// Prueba de eliminación de un servicio, dado su identificador
			long tbEliminados = pm.eliminarServicioPorId (servicio1.getId ());
			assertEquals ("Debe haberse eliminado un servicio !!", 1, tbEliminados);
			lista = pm.darServicios();
			assertEquals ("Debe haber un solo servicio !!", 1, lista.size ());
			assertFalse ("El primer servicio adicionado NO debe estar en la tabla", servicio1.getNombre().equals(lista.get(0).getNombre()));
			assertTrue ("El segundo servicio adicionado debe estar en la tabla", servicio2.getNombre().equals(lista.get(0).getNombre()));
			
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla servicio.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla servicio");
		}
		finally
		{
			pm.limpiarAlohandes();
    		pm.cerrarUnidadPersistencia ();    		
		}
	}
	
    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de servicio
     */
	@Test
	public void unicidadservicioTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del id del servicio");
			tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
			pm = new Alohandes(tableConfig);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de servicio incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de servicio incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			pm.limpiarAlohandes();
			Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
			
			// Lectura de los tipos de bebida con la tabla vacía
			List <Servicio> lista = pm.darServicios();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());
			pm.modoPruebas();
			pm.asignarID(1000);

			String nombre1 = "nom1";
			long costo1 = 1000;
			Servicio servicio1 = pm.adicionarServicio(nombre1, costo1);
			
			lista = pm.darServicios();
			assertEquals ("Debe haber un servicio creado !!", 1, lista.size ());
			
			
			String nombre2 = "nom2";
			long costo2 = 20200;
			Servicio servicio2 = pm.adicionarServicio(nombre2, costo2);

			assertNull ("No puede adicionar dos servicios con el mismo id !!", servicio2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla servicio.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla servicio");
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
