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

import uniandes.isis2304.alohandes.negocio.Alohandes;
import uniandes.isis2304.alohandes.negocio.Incluye;
import uniandes.isis2304.alohandes.negocio.Oferta;
import uniandes.isis2304.alohandes.negocio.Operador;
import uniandes.isis2304.alohandes.negocio.Servicio;
import uniandes.isis2304.alohandes.negocio.Vivienda;

public class IncluyeTest {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(IncluyeTest.class.getName());
	
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD.json"; 
	
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private Alohandes pm;
	
	/** Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar. */
	private JsonObject tableConfig;
	
	/**
	 * Método que prueba las operaciones sobre la tabla incluye
	 * 1. Adicionar un incluye
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un incluye por su identificador
	 * 4. Borrar un incluye por su nombre
	 */
    @Test
	public void CRDIncluyeTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre incluye");
			tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
			pm = new Alohandes(tableConfig);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de incluye incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de incluye incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
    		Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
    		Vivienda vi = pm.adicionarVivienda("..", 10, op.getId());
			    		
    		// Lectura de los tipos de bebida con la tabla vacía
    		Oferta of1 = pm.adicionarOferta(500, "SEMESTRES", vi.getId(), new Timestamp(2000, 1, 1, 0, 0, 0, 0), new Timestamp(2001, 12, 30, 0, 0, 0, 0),1);
    		Servicio se1 = pm.adicionarServicio("Ser1", 100);
    	
			List <Incluye> lista = pm.darIncluye();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un incluye adicionado
			
			
			Incluye incluye1 = pm.adicionarIncluye(se1.getId(),of1.getId(),1);
			lista = pm.darIncluye();
			
			assertEquals ("Debe haber un incluye creado !!", 1, lista.size ());
			System.out.println("VALORES "+ incluye1.getOferta() + " " + lista.get(0).getOferta() + " "+ of1.getId() + " " + se1.getId());
			assertTrue ("El objeto creado y el traido de la BD deben ser iguales !!", incluye1.getOferta() == lista.get(0).getOferta());

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			
			Oferta of2 = pm.adicionarOferta(1500, "MESES", vi.getId(), new Timestamp(2001, 1, 1, 0, 0, 0, 0), new Timestamp(2002, 12, 30, 0, 0, 0, 0),1);
    		Servicio se2 = pm.adicionarServicio("Ser2", 1100);
			
			Incluye incluye2 = pm.adicionarIncluye(se2.getId(),of2.getId(), 0);
			lista = pm.darIncluye();
			assertEquals ("Debe haber dos tipos de bebida creados !!", 2, lista.size ());
			assertTrue ("El primer incluye adicionado debe estar en la tabla", incluye1.getOferta() == lista.get (0).getOferta() || incluye1.getOferta() == lista.get (1).getOferta());
			assertTrue ("El segundo incluye adicionado debe estar en la tabla", incluye2.getOferta() == lista.get (0).getOferta() || incluye2.getOferta() == lista.get(1).getOferta());

			// Prueba de eliminación de un incluye, dado su identificador
			long tbEliminados = pm.eliminarIncluyePorId(incluye1.getServicio(), incluye1.getOferta());
			assertEquals ("Debe haberse eliminado un incluye !!", 1, tbEliminados);
			lista = pm.darIncluye();
			assertEquals ("Debe haber un solo incluye !!", 1, lista.size ());
			assertFalse ("El primer incluye adicionado NO debe estar en la tabla", incluye1.getOferta() == lista.get(0).getOferta());
			assertTrue ("El segundo incluye adicionado debe estar en la tabla", incluye2.getOferta() == lista.get (0).getOferta());
			
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla incluye.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla incluye");
		}
		finally
		{
			pm.limpiarAlohandes();
    		pm.cerrarUnidadPersistencia ();    		
		}
	}
	
    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de incluye
     */
	@Test
	public void unicidadincluyeTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del id del incluye");
			tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
			pm = new Alohandes(tableConfig);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de incluye incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de incluye incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			pm.limpiarAlohandes();
			Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
    		Vivienda vi = pm.adicionarVivienda("..", 10, op.getId());
			    		
    		// Lectura de los tipos de bebida con la tabla vacía
    		Oferta of1 = pm.adicionarOferta(500, "SEMESTRES", vi.getId(), new Timestamp(2000, 1, 1, 0, 0, 0, 0), new Timestamp(2001, 12, 30, 0, 0, 0, 0),1);
    		Servicio se1 = pm.adicionarServicio("Ser1", 100);
    		
			List <Incluye> lista = pm.darIncluye();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());
			pm.modoPruebas();
			pm.asignarID(1000);

			Incluye incluye1 = pm.adicionarIncluye(se1.getId(),of1.getId(),1);
			lista = pm.darIncluye();
			assertEquals ("Debe haber un incluye creado !!", 1, lista.size ());
			
			Oferta of2 = pm.adicionarOferta(1500, "MESES", vi.getId(), new Timestamp(2001, 1, 1, 0, 0, 0, 0), new Timestamp(2002, 12, 30, 0, 0, 0, 0),1);
    		Servicio se2 = pm.adicionarServicio("Ser2", 1100);
			
			Incluye incluye2 = pm.adicionarIncluye(se2.getId(),of2.getId(), 0);
			assertNull ("No puede adicionar dos incluyes con el mismo id !!", incluye2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla incluye.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla incluye");
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
