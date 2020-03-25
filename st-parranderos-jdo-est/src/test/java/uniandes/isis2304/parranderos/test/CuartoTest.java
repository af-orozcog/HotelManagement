package uniandes.isis2304.parranderos.test;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.alohandes.negocio.Alohandes;
import uniandes.isis2304.alohandes.negocio.Cuarto;
import uniandes.isis2304.alohandes.negocio.Operador;
import uniandes.isis2304.alohandes.persistencia.PersistenciaAlohandes;

public class CuartoTest {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(CuartoTest.class.getName());
	
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD.json"; 
	
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private Alohandes pm;
	
	/** Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar. */
	private JsonObject tableConfig;
	
	
	/**
	 * Método que prueba las operaciones sobre la tabla cuarto
	 * 1. Adicionar un cuarto
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un cuarto por su identificador
	 * 4. Borrar un cuarto por su nombre
	 */
    @Test
	public void CRDCuartoTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre apartamento");
			tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
			pm = new Alohandes(tableConfig);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de cuarto incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de cuarto incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
    		pm.limpiarAlohandes();
    		pm.desactivarModoPruebas();
    		Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
			// Lectura de los tipos de bebida con la tabla vacía
			List <Cuarto> lista = pm.darCuartos();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un cuarto adicionado
			String direccioncuarto1 = "calle23C#69C-20";
			int cuposcuarto1 = 4;
			String tipocuarto1 = "ESTANDAR";
			String esquema1 = "no hay";
			String menaje1 = "no hay menaje";
			Cuarto cuarto1 = pm.adicionarCuarto(direccioncuarto1, cuposcuarto1, op.getId(), true, true, esquema1, menaje1);
			if(cuarto1 == null)System.out.println("why!!!!!!!!!!!!!!");
			System.out.println(cuarto1.getCupos());
			lista = pm.darCuartos();
			System.out.println("tamaño es " + lista.size());
			assertEquals ("Debe haber un cuarto creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", cuarto1, lista.get (0));

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			String direccioncuarto2 = "calle23C#69C-20";
			int cuposcuarto2 = 4;
			String tipocuarto2 = "ESTANDAR";
			String esquema2 = "no hay";
			String menaje2 = "no hay menaje";
			Cuarto cuarto2 = pm.adicionarCuarto(direccioncuarto2, cuposcuarto2, op.getId(), true, true, esquema2, menaje2);
			lista = pm.darCuartos();
			assertEquals ("Debe haber dos tipos de bebida creados !!", 2, lista.size ());
			assertTrue ("El primer cuarto adicionado debe estar en la tabla", cuarto1.equals (lista.get (0)) || cuarto1.equals (lista.get (1)));
			assertTrue ("El segundo cuarto adicionado debe estar en la tabla", cuarto2.equals (lista.get (0)) || cuarto2.equals (lista.get (1)));

			// Prueba de eliminación de un cuarto, dado su identificador
			long tbEliminados = pm.eliminarCuartoPorId (cuarto1.getId ());
			assertEquals ("Debe haberse eliminado un cuarto !!", 1, tbEliminados);
			lista = pm.darCuartos();
			assertEquals ("Debe haber un solo cuarto !!", 1, lista.size ());
			assertFalse ("El primer cuarto adicionado NO debe estar en la tabla", cuarto1.equals (lista.get (0)));
			assertTrue ("El segundo cuarto adicionado debe estar en la tabla", cuarto2.equals (lista.get (0)));
			
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla cuarto.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla cuarto");
		}
		finally
		{
			pm.limpiarAlohandes();
    		pm.cerrarUnidadPersistencia ();    		
		}
	}
	
    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de cuarto
     */
	@Test
	public void unicidadCuartoTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre apartamento");
			tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
			pm = new Alohandes(tableConfig);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de cuarto incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de cuarto incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
			
			// Lectura de los tipos de bebida con la tabla vacía
			List <Cuarto> lista = pm.darCuartos();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());
			pm.modoPruebas();
			pm.asignarID(1000);
			String direccioncuarto1 = "calle23C#69C-20";
			int cuposcuarto1 = 4;
			String tipocuarto1 = "ESTANDAR";
			String esquema1 = "no hay";
			String menaje1 = "no hay menaje";
			Cuarto cuarto1 = pm.adicionarCuarto(direccioncuarto1, cuposcuarto1, op.getId(), true, true, esquema1, menaje1);
			lista = pm.darCuartos();
			assertEquals ("Debe haber un cuarto creado !!", 1, lista.size ());
			
			
			String direccioncuarto2 = "calle23C#69C-20";
			int cuposcuarto2 = 4;
			String tipocuarto2 = "ESTANDAR";
			String esquema2 = "no hay";
			String menaje2 = "no hay menaje";
			Cuarto cuarto2 = pm.adicionarCuarto(direccioncuarto2, cuposcuarto2, op.getId(), true, true, esquema2, menaje2);
			lista = pm.darCuartos();
			assertNull ("No puede adicionar dos cuartos con el mismo id !!", cuarto2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla cuarto.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla cuarto");
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
