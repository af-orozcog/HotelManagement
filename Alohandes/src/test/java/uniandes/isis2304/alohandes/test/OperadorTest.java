package uniandes.isis2304.alohandes.test;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;


import uniandes.isis2304.alohandes.negocio.Operador;
import uniandes.isis2304.alohandes.persistencia.PersistenciaAlohandes;

public class OperadorTest {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(OperadorTest.class.getName());
	
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private PersistenciaAlohandes pm;
	
	/**
	 * Método que prueba las operaciones sobre la tabla operador
	 * 1. Adicionar un operador
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un operador por su identificador
	 * 4. Borrar un operador por su nombre
	 */
    @Test
	public void CRDOperadorTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre operador");
			pm = PersistenciaAlohandes.getInstance ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de operador incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de operador incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <Operador> lista = pm.darOperadores();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un operador adicionado
			String nombre1 = "zeus";
			String email1 = "zeus@gmail.com";
			String numero1 = "3246765434";
			String tipo1 = "PERSONA_NATURAL";
			Operador operador1 = pm.adicionarOperador(nombre1, email1, numero1, tipo1);
			lista = pm.darOperadores();
			
			assertEquals ("Debe haber un operador creado !!", 1, lista.size ());
			System.out.println("el nombre del sapo es: " + lista.get(0).getNombre());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", operador1.getNombre(), lista.get(0).getNombre());

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			String nombre2 = "zeuss";
			String email2 = "zeusss@gmail.com";
			String numero2 = "324676543445";
			String tipo2 = "PERSONA_NATURAL";
			Operador operador2 = pm.adicionarOperador(nombre2, email2, numero2, tipo2);
			lista = pm.darOperadores();
			assertEquals ("Debe haber dos tipos de bebida creados !!", 2, lista.size ());
			assertTrue ("El primer operador adicionado debe estar en la tabla", operador1.getNombre().equals (lista.get(0).getNombre()) || operador1.getNombre().equals (lista.get(1).getNombre()));
			assertTrue ("El segundo operador adicionado debe estar en la tabla", operador2.getNombre().equals (lista.get(0).getNombre()) || operador2.getNombre().equals(lista.get(1).getNombre()));

			// Prueba de eliminación de un operador, dado su identificador
			long tbEliminados = pm.eliminarOperadorPorId (operador1.getId());
			assertEquals ("Debe haberse eliminado un operador !!", 1, tbEliminados);
			lista = pm.darOperadores();
			assertEquals ("Debe haber un solo operador !!", 1, lista.size ());
			System.out.println("cual es el nombre de la persona ahi");
			assertFalse ("El primer operador adicionado NO debe estar en la tabla", operador1.getNombre().equals(lista.get(0).getNombre()));
			assertTrue ("El segundo operador adicionado debe estar en la tabla", operador2.getNombre().equals(lista.get(0).getNombre()));
			
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla operador.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla operador");
		}
		finally
		{
			pm.limpiarAlohandes();
    		pm.cerrarUnidadPersistencia ();    		
		}
	}
	
    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de operador
     */
	@Test
	public void unicidadOperadorTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del id del operador");
			pm = PersistenciaAlohandes.getInstance ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de operador incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de operador incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			
			// Lectura de los tipos de bebida con la tabla vacía
			List <Operador> lista = pm.darOperadores();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());
			pm.modoPruebas();
			pm.asignarID(1000);
			String nombre1 = "zeus";
			String email1 = "zeus@gmail.com";
			String numero1 = "3246765434";
			String tipo1 = "PERSONA_NATURAL";
			Operador operador1 = pm.adicionarOperador(nombre1, email1, numero1, tipo1);
			lista = pm.darOperadores();
			assertEquals ("Debe haber un operador creado !!", 1, lista.size ());
			
			
			String nombre2 = "zeus";
			String email2 = "zeus@gmail.com";
			String numero2 = "3246765434";
			String tipo2 = "PERSONA_NATURAL";
			Operador operador2 = pm.adicionarOperador(nombre2, email2, numero2, tipo2);
			lista = pm.darOperadores();
			assertNull ("No puede adicionar dos operadors con el mismo id !!", operador2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla operador.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla operador");
		}    				
		finally
		{
			pm.limpiarAlohandes();
    		pm.cerrarUnidadPersistencia ();    		
		}
	}
	
}
