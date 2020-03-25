package uniandes.isis2304.parranderos.test;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import uniandes.isis2304.alohandes.negocio.Ganancias;
import uniandes.isis2304.alohandes.negocio.Operador;
import uniandes.isis2304.alohandes.persistencia.PersistenciaAlohandes;

public class GananciasTest {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(GananciasTest.class.getName());
	
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private PersistenciaAlohandes pm;
	
	/**
	 * Método que prueba las operaciones sobre la tabla ganancias
	 * 1. Adicionar un ganancias
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un ganancias por su identificador
	 * 4. Borrar un ganancias por su nombre
	 */
    @Test
	public void CRDGananciaTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre ganancias");
			pm = PersistenciaAlohandes.getInstance ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de ganancias incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de ganancias incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
    		Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
			// Lectura de los tipos de bebida con la tabla vacía
			List <Ganancias> lista = pm.darGanancias();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un ganancias adicionado
			long cantidad1 = 10000;
			int mes1 = 12;
			int año1 = 2020;
			Ganancias ganancias1 = pm.adicionarGanancias(cantidad1, mes1, año1, op.getId());
			lista = pm.darGanancias();
			
			assertEquals ("Debe haber un ganancias creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", ganancias1, lista.get (0));

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			long cantidad2 = 100000;
			int mes2 = 12;
			int año2 = 2020;
			Ganancias ganancias2 = pm.adicionarGanancias(cantidad2, mes2, año2, op.getId());
			lista = pm.darGanancias();
			assertEquals ("Debe haber dos tipos de bebida creados !!", 2, lista.size ());
			assertTrue ("El primer ganancias adicionado debe estar en la tabla", ganancias1.equals (lista.get (0)) || ganancias1.equals (lista.get (1)));
			assertTrue ("El segundo ganancias adicionado debe estar en la tabla", ganancias2.equals (lista.get (0)) || ganancias2.equals (lista.get (1)));

			// Prueba de eliminación de un ganancias, dado su identificador
			long tbEliminados = pm.eliminarGananciasPorId (ganancias1.getId ());
			assertEquals ("Debe haberse eliminado un ganancias !!", 1, tbEliminados);
			lista = pm.darGanancias();
			assertEquals ("Debe haber un solo ganancias !!", 1, lista.size ());
			assertFalse ("El primer ganancias adicionado NO debe estar en la tabla", ganancias1.equals (lista.get (0)));
			assertTrue ("El segundo ganancias adicionado debe estar en la tabla", ganancias2.equals (lista.get (0)));
			
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla ganancias.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla ganancias");
		}
		finally
		{
			pm.limpiarAlohandes();
    		pm.cerrarUnidadPersistencia ();    		
		}
	}
	
    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de ganancias
     */
	@Test
	public void unicidadgananciasTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del id del ganancias");
			pm = PersistenciaAlohandes.getInstance ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de ganancias incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de ganancias incompleta. No se pudo conectar a la base de datos !!.\n";
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
			List <Ganancias> lista = pm.darGanancias();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());
			pm.modoPruebas();
			pm.asignarID(1000);
			long cantidad1 = 10000;
			int mes1 = 12;
			int año1 = 2020;
			Ganancias ganancias1 = pm.adicionarGanancias(cantidad1, mes1, año1, op.getId());
			lista = pm.darGanancias();
			assertEquals ("Debe haber un ganancias creado !!", 1, lista.size ());
			
			long cantidad2 = 100000;
			int mes2 = 12;
			int año2 = 2020;
			Ganancias ganancias2 = pm.adicionarGanancias(cantidad2, mes2, año2, op.getId());
			lista = pm.darGanancias();
			assertNull ("No puede adicionar dos gananciass con el mismo id !!", ganancias2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla ganancias.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla ganancias");
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
