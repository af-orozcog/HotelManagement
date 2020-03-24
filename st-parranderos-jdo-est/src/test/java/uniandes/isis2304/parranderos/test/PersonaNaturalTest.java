package uniandes.isis2304.parranderos.test;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import uniandes.isis2304.alohandes.negocio.Persona_Natural;
import uniandes.isis2304.alohandes.persistencia.PersistenciaAlohandes;

public class PersonaNaturalTest {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersonaNaturalTest.class.getName());
	
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private PersistenciaAlohandes pm;
	
	/**
	 * Método que prueba las operaciones sobre la tabla Persona_Natural
	 * 1. Adicionar un Persona_Natural
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un Persona_Natural por su identificador
	 * 4. Borrar un Persona_Natural por su nombre
	 */
    @Test
	public void CRDPersona_NaturalTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre Persona_Natural");
			pm = PersistenciaAlohandes.getInstance ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de Persona_Natural incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de Persona_Natural incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <Persona_Natural> lista = pm.darPersona_Naturales();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un Persona_Natural adicionado
			String nombre1 = "zeuss";
			String email1 = "zeus@gmail.com";
			String numero1 = "3246765434";
			String documento1 = "2343454345";
			String tipo1 = "PERSONA_NATURAL";
			Persona_Natural Persona_Natural1 = pm.adicionarPersona_Natural(nombre1, email1, documento1, numero1, tipo1);
			lista = pm.darPersona_Naturales();
			
			assertEquals ("Debe haber un Persona_Natural creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", Persona_Natural1, lista.get (0));

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			String nombre2 = "zeuss";
			String email2 = "zeus@gmail.com";
			String numero2 = "3246765434";
			String documento2 = "23434543sf45";
			String tipo2 = "PERSONA_NATURAL";
			Persona_Natural Persona_Natural2 = pm.adicionarPersona_Natural(nombre2, email2, documento2, numero2, tipo2);
			lista = pm.darPersona_Naturales();
			assertEquals ("Debe haber dos tipos de bebida creados !!", 2, lista.size ());
			assertTrue ("El primer Persona_Natural adicionado debe estar en la tabla", Persona_Natural1.equals (lista.get (0)) || Persona_Natural1.equals (lista.get (1)));
			assertTrue ("El segundo Persona_Natural adicionado debe estar en la tabla", Persona_Natural2.equals (lista.get (0)) || Persona_Natural2.equals (lista.get (1)));

			// Prueba de eliminación de un Persona_Natural, dado su identificador
			long tbEliminados = pm.eliminarPersona_NaturalPorId (Persona_Natural1.getId ());
			assertEquals ("Debe haberse eliminado un Persona_Natural !!", 1, tbEliminados);
			lista = pm.darPersona_Naturales();
			assertEquals ("Debe haber un solo Persona_Natural !!", 1, lista.size ());
			assertFalse ("El primer Persona_Natural adicionado NO debe estar en la tabla", Persona_Natural1.equals (lista.get (0)));
			assertTrue ("El segundo Persona_Natural adicionado debe estar en la tabla", Persona_Natural2.equals (lista.get (0)));
			
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla Persona_Natural.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla Persona_Natural");
		}
		finally
		{
    		pm.cerrarUnidadPersistencia ();    		
		}
	}
	
    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de Persona_Natural
     */
	@Test
	public void unicidadPersona_NaturalTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del id del Persona_Natural");
			pm = PersistenciaAlohandes.getInstance ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de Persona_Natural incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de Persona_Natural incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			
			// Lectura de los tipos de bebida con la tabla vacía
			List <Persona_Natural> lista = pm.darPersona_Naturales();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());
			pm.modoPruebas();
			pm.asignarID(1000);
			String nombre1 = "zeuss";
			String email1 = "zeus@gmail.com";
			String numero1 = "3246765434";
			String documento1 = "2343454345";
			String tipo1 = "PERSONA_NATURAL";
			Persona_Natural Persona_Natural1 = pm.adicionarPersona_Natural(nombre1, email1, documento1, numero1, tipo1);
			lista = pm.darPersona_Naturales();
			assertEquals ("Debe haber un Persona_Natural creado !!", 1, lista.size ());
			
			
			String nombre2 = "zeuss";
			String email2 = "zeus@gmail.com";
			String numero2 = "3246765434";
			String documento2 = "23434543sf45";
			String tipo2 = "PERSONA_NATURAL";
			Persona_Natural Persona_Natural2 = pm.adicionarPersona_Natural(nombre2, email2, documento2, numero2, tipo2);
			lista = pm.darPersona_Naturales();
			assertNull ("No puede adicionar dos Persona_Naturals con el mismo id !!", Persona_Natural2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla Persona_Natural.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla Persona_Natural");
		}    				
		finally
		{
    		pm.cerrarUnidadPersistencia ();    		
		}
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
}
