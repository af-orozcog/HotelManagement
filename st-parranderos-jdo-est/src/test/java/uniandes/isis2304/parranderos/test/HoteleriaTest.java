package uniandes.isis2304.parranderos.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import uniandes.isis2304.alohandes.negocio.Hoteleria;
import uniandes.isis2304.alohandes.negocio.Operador;
import uniandes.isis2304.alohandes.persistencia.PersistenciaAlohandes;

public class HoteleriaTest {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(HoteleriaTest.class.getName());
	
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private PersistenciaAlohandes pm;
	
	/**
	 * Método que prueba las operaciones sobre la tabla Hoteleria
	 * 1. Adicionar un Hoteleria
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un Hoteleria por su identificador
	 * 4. Borrar un Hoteleria por su nombre
	 */
    @Test
	public void CRDHoteleriaTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre Hoteleria");
			pm = PersistenciaAlohandes.getInstance ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de Hoteleria incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de Hoteleria incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
    		Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
			// Lectura de los tipos de bebida con la tabla vacía
			List <Hoteleria> lista = pm.darHotelerias();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un Hoteleria adicionado
			String nombre1 = "elSapo";
			String email1 = "sapo@gmail.com";
			String numero1 = "345654323456";
			String tipoHoteleria1 = "HOTELERIA";
			Timestamp horaInicio1 = new Timestamp(1000);
			Timestamp horaFin1 = new Timestamp(100000);
			Hoteleria Hoteleria1 = pm.adicionarHoteleria(nombre1, email1, numero1, tipoHoteleria1, horaInicio1, horaFin1);
			lista = pm.darHotelerias();
			
			assertEquals ("Debe haber un Hoteleria creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", Hoteleria1, lista.get (0));

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			String nombre2 = "elSapoPerro";
			String email2 = "sapo@gmail.com";
			String numero2 = "345654323456";
			String tipoHoteleria2 = "HOTELERIA";
			Timestamp horaInicio2 = new Timestamp(1000);
			Timestamp horaFin2 = new Timestamp(100000);
			Hoteleria Hoteleria2 = pm.adicionarHoteleria(nombre2, email2, numero2, tipoHoteleria2, horaInicio2, horaFin2);
			lista = pm.darHotelerias();
			assertEquals ("Debe haber dos tipos de bebida creados !!", 2, lista.size ());
			assertTrue ("El primer Hoteleria adicionado debe estar en la tabla", Hoteleria1.equals (lista.get (0)) || Hoteleria1.equals (lista.get (1)));
			assertTrue ("El segundo Hoteleria adicionado debe estar en la tabla", Hoteleria2.equals (lista.get (0)) || Hoteleria2.equals (lista.get (1)));

			// Prueba de eliminación de un Hoteleria, dado su identificador
			long tbEliminados = pm.eliminarHoteleriaPorId (Hoteleria1.getId ());
			assertEquals ("Debe haberse eliminado un Hoteleria !!", 1, tbEliminados);
			lista = pm.darHotelerias();
			assertEquals ("Debe haber un solo Hoteleria !!", 1, lista.size ());
			assertFalse ("El primer Hoteleria adicionado NO debe estar en la tabla", Hoteleria1.equals (lista.get (0)));
			assertTrue ("El segundo Hoteleria adicionado debe estar en la tabla", Hoteleria2.equals (lista.get (0)));
			
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla Hoteleria.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla Hoteleria");
		}
		finally
		{
			pm.limpiarAlohandes();
    		pm.cerrarUnidadPersistencia ();    		
		}
	}
	
    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de Hoteleria
     */
	@Test
	public void unicidadHoteleriaTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del id del Hoteleria");
			pm = PersistenciaAlohandes.getInstance ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de Hoteleria incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de Hoteleria incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
			
			// Lectura de los tipos de bebida con la tabla vacía
			List <Hoteleria> lista = pm.darHotelerias();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());
			pm.modoPruebas();
			pm.asignarID(1000);
			String nombre1 = "elSapo";
			String email1 = "sapo@gmail.com";
			String numero1 = "345654323456";
			String tipoHoteleria1 = "HOTELERIA";
			Timestamp horaInicio1 = new Timestamp(1000);
			Timestamp horaFin1 = new Timestamp(100000);
			Hoteleria Hoteleria1 = pm.adicionarHoteleria(nombre1, email1, numero1, tipoHoteleria1, horaInicio1, horaFin1);
			lista = pm.darHotelerias();
			assertEquals ("Debe haber un Hoteleria creado !!", 1, lista.size ());
			
			String nombre2 = "elSapoPerro";
			String email2 = "sapo@gmail.com";
			String numero2 = "345654323456";
			String tipoHoteleria2 = "HOTELERIA";
			Timestamp horaInicio2 = new Timestamp(1000);
			Timestamp horaFin2 = new Timestamp(100000);
			Hoteleria Hoteleria2 = pm.adicionarHoteleria(nombre2, email2, numero2, tipoHoteleria2, horaInicio2, horaFin2);
			lista = pm.darHotelerias();
			assertNull ("No puede adicionar dos Hotelerias con el mismo id !!", Hoteleria2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla Hoteleria.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla Hoteleria");
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
