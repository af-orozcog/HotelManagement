package uniandes.isis2304.parranderos.test;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import uniandes.isis2304.alohandes.negocio.ViviendaUniversitaria;
import uniandes.isis2304.alohandes.negocio.Operador;
import uniandes.isis2304.alohandes.persistencia.PersistenciaAlohandes;

public class VivienvaUniversitariaTest {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(VivienvaUniversitariaTest.class.getName());
	
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private PersistenciaAlohandes pm;
	
	/**
	 * Método que prueba las operaciones sobre la tabla ViviendaUniversitaria
	 * 1. Adicionar un ViviendaUniversitaria
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un ViviendaUniversitaria por su identificador
	 * 4. Borrar un ViviendaUniversitaria por su nombre
	 */
    @Test
	public void CRDViviendaUniversitariaTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre ViviendaUniversitaria");
			pm = PersistenciaAlohandes.getInstance ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de ViviendaUniversitaria incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de ViviendaUniversitaria incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
    		Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
			// Lectura de los tipos de bebida con la tabla vacía
			List <ViviendaUniversitaria> lista = pm.darViviendasUniversitarias();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un ViviendaUniversitaria adicionado
			String nombre1 = "nose";
			String email1 = "nose";
			String numero1 = "nose";
			ViviendaUniversitaria ViviendaUniversitaria1 = pm.adicionarViviendaUniversitaria(nombre1, email1, numero1);
			lista = pm.darViviendasUniversitarias();
			
			assertEquals ("Debe haber un ViviendaUniversitaria creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", ViviendaUniversitaria1, lista.get (0));

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			String nombre2 = "nose";
			String email2 = "nose";
			String numero2 = "nose";
			ViviendaUniversitaria ViviendaUniversitaria2 = pm.adicionarViviendaUniversitaria(nombre2, email2, numero2);
			lista = pm.darViviendasUniversitarias();
			assertEquals ("Debe haber dos tipos de bebida creados !!", 2, lista.size ());
			assertTrue ("El primer ViviendaUniversitaria adicionado debe estar en la tabla", ViviendaUniversitaria1.equals (lista.get (0)) || ViviendaUniversitaria1.equals (lista.get (1)));
			assertTrue ("El segundo ViviendaUniversitaria adicionado debe estar en la tabla", ViviendaUniversitaria2.equals (lista.get (0)) || ViviendaUniversitaria2.equals (lista.get (1)));

			// Prueba de eliminación de un ViviendaUniversitaria, dado su identificador
			long tbEliminados = pm.eliminarViviendaUniversitariaPorId (ViviendaUniversitaria1.getId ());
			assertEquals ("Debe haberse eliminado un ViviendaUniversitaria !!", 1, tbEliminados);
			lista = pm.darViviendasUniversitarias();
			assertEquals ("Debe haber un solo ViviendaUniversitaria !!", 1, lista.size ());
			assertFalse ("El primer ViviendaUniversitaria adicionado NO debe estar en la tabla", ViviendaUniversitaria1.equals (lista.get (0)));
			assertTrue ("El segundo ViviendaUniversitaria adicionado debe estar en la tabla", ViviendaUniversitaria2.equals (lista.get (0)));
			
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla ViviendaUniversitaria.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla ViviendaUniversitaria");
		}
		finally
		{
			pm.limpiarAlohandes();
    		pm.cerrarUnidadPersistencia ();    		
		}
	}
	
    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de ViviendaUniversitaria
     */
	@Test
	public void unicidadViviendaUniversitariaTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del id del ViviendaUniversitaria");
			pm = PersistenciaAlohandes.getInstance ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de ViviendaUniversitaria incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de ViviendaUniversitaria incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
			
			// Lectura de los tipos de bebida con la tabla vacía
			List <ViviendaUniversitaria> lista = pm.darViviendasUniversitarias();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());
			pm.modoPruebas();
			pm.asignarID(1000);
			String nombre1 = "nose";
			String email1 = "nose";
			String numero1 = "nose";
			ViviendaUniversitaria ViviendaUniversitaria1 = pm.adicionarViviendaUniversitaria(nombre1, email1, numero1);
			lista = pm.darViviendasUniversitarias();
			assertEquals ("Debe haber un ViviendaUniversitaria creado !!", 1, lista.size ());
			
			String nombre2 = "nose";
			String email2 = "nose";
			String numero2 = "nose";
			ViviendaUniversitaria ViviendaUniversitaria2 = pm.adicionarViviendaUniversitaria(nombre2, email2, numero2);
			lista = pm.darViviendasUniversitarias();
			assertNull ("No puede adicionar dos ViviendaUniversitarias con el mismo id !!", ViviendaUniversitaria2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla ViviendaUniversitaria.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla ViviendaUniversitaria");
		}    				
		finally
		{
			pm.limpiarAlohandes();
    		pm.cerrarUnidadPersistencia ();    		
		}
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
}
