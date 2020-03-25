package uniandes.isis2304.parranderos.test;

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
import uniandes.isis2304.alohandes.negocio.Cliente;
import uniandes.isis2304.alohandes.negocio.Oferta;
import uniandes.isis2304.alohandes.negocio.Reserva;
import uniandes.isis2304.alohandes.negocio.Vivienda;
import uniandes.isis2304.alohandes.negocio.Operador;

public class ReservaTest {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(ReservaTest.class.getName());
	
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD.json"; 
	
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private Alohandes pm;
	
	/** Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar. */
	private JsonObject tableConfig;
	
	/**
	 * Método que prueba las operaciones sobre la tabla reserva
	 * 1. Adicionar un reserva
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un reserva por su identificador
	 * 4. Borrar un reserva por su nombre
	 */
    @Test
	public void CRDReservaTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre reserva");
			tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
			pm = new Alohandes(tableConfig);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de reserva incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de reserva incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
    		Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
    		Vivienda vi = pm.adicionarCuarto("..", 10, op.getId(), true, true, "..", "..");
    		Oferta of = pm.adicionarOferta(1000, "MESES", vi.getId(), new Timestamp(2000, 1, 1, 1, 0, 0, 0), new Timestamp(2001, 1, 1, 1, 0, 0, 0));
    		Cliente us = pm.adicionarCliente("nom", "correo", "320", "100", "ESTUDIANTE");
			// Lectura de los tipos de bebida con la tabla vacía
			List <Reserva> lista = pm.darReservas();
			System.out.println("tamaño al comienzo" + lista.size());
			assertEquals ("No debe haber reservas creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un reserva adicionado
			
			Timestamp inicio1 = new Timestamp(2010, 1, 1, 1, 0, 0, 0);
			Timestamp fin1 = new Timestamp(2011, 1, 1, 1, 0, 0, 0);
			int duracion1 = 10;
			String periodoArrendamiento1 = "SEMESTRES";
			
			Reserva reserva1 = pm.adicionarReserva(inicio1, fin1, duracion1, periodoArrendamiento1, of.getId(), us.getId());
			lista = pm.darReservas();
			
			assertEquals ("Debe haber un reserva creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", reserva1, lista.get (0));

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			
			Timestamp inicio2 = new Timestamp(2011, 1, 1, 1, 0, 0, 0);
			Timestamp fin2 = new Timestamp(2012, 1, 1, 1, 0, 0, 0);
			int duracion2 = 9;
			String periodoArrendamiento2 = "MESES";

			Reserva reserva2 = pm.adicionarReserva(inicio2, fin2, duracion2, periodoArrendamiento2, of.getId(), us.getId());
			lista = pm.darReservas();
			assertEquals ("Debe haber dos tipos de bebida creados !!", 2, lista.size ());
			assertTrue ("El primer reserva adicionado debe estar en la tabla", reserva1.equals (lista.get (0)) || reserva1.equals (lista.get (1)));
			assertTrue ("El segundo reserva adicionado debe estar en la tabla", reserva2.equals (lista.get (0)) || reserva2.equals (lista.get (1)));

			// Prueba de eliminación de un reserva, dado su identificador
			long tbEliminados = pm.eliminarReservaPorId (reserva1.getId ());
			assertEquals ("Debe haberse eliminado un reserva !!", 1, tbEliminados);
			lista = pm.darReservas();
			assertEquals ("Debe haber un solo reserva !!", 1, lista.size ());
			assertFalse ("El primer reserva adicionado NO debe estar en la tabla", reserva1.equals (lista.get (0)));
			assertTrue ("El segundo reserva adicionado debe estar en la tabla", reserva2.equals (lista.get (0)));
			
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla reserva.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla reserva");
		}
		finally
		{
			pm.limpiarAlohandes();
    		pm.cerrarUnidadPersistencia ();    		
		}
	}
	
    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de reserva
     */
	@Test
	public void unicidadreservaTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del id del reserva");
			tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
			pm = new Alohandes(tableConfig);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de reserva incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de reserva incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			pm.limpiarAlohandes();

			Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
    		Vivienda vi = pm.adicionarCuarto("..", 10, op.getId(), true, true, "..", "..");
    		Oferta of = pm.adicionarOferta(1000, "MESES", vi.getId(), new Timestamp(2000, 1, 1, 1, 0, 0, 0), new Timestamp(2001, 1, 1, 1, 0, 0, 0));
    		Cliente us = pm.adicionarCliente("nom", "correo", "320", "100", "ESTUDIANTE");
						
			// Lectura de los tipos de bebida con la tabla vacía
			List <Reserva> lista = pm.darReservas();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());
			pm.modoPruebas();
			pm.asignarID(1000);
			
			Timestamp inicio1 = new Timestamp(2010, 1, 1, 1, 0, 0, 0);
			Timestamp fin1 = new Timestamp(2011, 1, 1, 1, 0, 0, 0);
			int duracion1 = 10;
			String periodoArrendamiento1 = "SEMESTRES";
			
			Reserva reserva1 = pm.adicionarReserva(inicio1, fin1, duracion1, periodoArrendamiento1, of.getId(), us.getId());
			lista = pm.darReservas();
			assertEquals ("Debe haber un reserva creado !!", 1, lista.size ());
			
			
			Timestamp inicio2 = new Timestamp(2011, 1, 1, 1, 0, 0, 0);
			Timestamp fin2 = new Timestamp(2012, 1, 1, 1, 0, 0, 0);
			int duracion2 = 9;
			String periodoArrendamiento2 = "MESES";

			Reserva reserva2 = pm.adicionarReserva(inicio2, fin2, duracion2, periodoArrendamiento2, of.getId(), us.getId());
			assertNull ("No puede adicionar dos reservas con el mismo id !!", reserva2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla reserva.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla reserva");
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
