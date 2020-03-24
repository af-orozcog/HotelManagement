package uniandes.isis2304.parranderos.test;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import uniandes.isis2304.alohandes.negocio.Operador;
import uniandes.isis2304.alohandes.negocio.Habitacion;
import uniandes.isis2304.alohandes.persistencia.PersistenciaAlohandes;

public class HabitacionTest {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(HabitacionTest.class.getName());
	
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private PersistenciaAlohandes pm;
	
	/**
	 * Método que prueba las operaciones sobre la tabla habitacion
	 * 1. Adicionar un habitacion
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un habitacion por su identificador
	 * 4. Borrar un habitacion por su nombre
	 */
    @Test
	public void CRDHabitacionTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre habitacion");
			pm = PersistenciaAlohandes.getInstance ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de habitacion incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de habitacion incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
    		Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
			// Lectura de los tipos de bebida con la tabla vacía
			List <Habitacion> lista = pm.darHabitaciones();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un habitacion adicionado
			String direccionhabitacion1 = "calle23C#69C-20";
			int cuposhabitacion1 = 4;
			String tipoHabitacion1 = "ESTANDAR";
			String Categoria1 = "cualquiera";
			int capacidad1 = 4;
			int numero1 = 3;
			Habitacion habitacion1 = pm.adicionarHabitacion(direccionhabitacion1, cuposhabitacion1, op.getId(), tipoHabitacion1, Categoria1, capacidad1, numero1);
			lista = pm.darHabitaciones();
			
			assertEquals ("Debe haber un habitacion creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", habitacion1, lista.get (0));

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			String direccionhabitacion2 = "calle20C#20C-20";
			int cuposhabitacion2 = 4;
			String tipoHabitacion2 = "SEMISUITE";
			String Categoria2 = "nose";
			int capacidad2 = 4;
			int numero2 = 3;
			Habitacion habitacion2 = pm.adicionarHabitacion(direccionhabitacion2, cuposhabitacion2, op.getId(), tipoHabitacion2, Categoria2, capacidad2, numero2);
			lista = pm.darHabitaciones();
			assertEquals ("Debe haber dos tipos de bebida creados !!", 2, lista.size ());
			assertTrue ("El primer habitacion adicionado debe estar en la tabla", habitacion1.equals (lista.get (0)) || habitacion1.equals (lista.get (1)));
			assertTrue ("El segundo habitacion adicionado debe estar en la tabla", habitacion2.equals (lista.get (0)) || habitacion2.equals (lista.get (1)));

			// Prueba de eliminación de un habitacion, dado su identificador
			long tbEliminados = pm.eliminarHabitacionPorId (habitacion1.getId ());
			assertEquals ("Debe haberse eliminado un habitacion !!", 1, tbEliminados);
			lista = pm.darHabitaciones();
			assertEquals ("Debe haber un solo habitacion !!", 1, lista.size ());
			assertFalse ("El primer habitacion adicionado NO debe estar en la tabla", habitacion1.equals (lista.get (0)));
			assertTrue ("El segundo habitacion adicionado debe estar en la tabla", habitacion2.equals (lista.get (0)));
			
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla habitacion.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla habitacion");
		}
		finally
		{
    		pm.cerrarUnidadPersistencia ();    		
		}
	}
	
    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de habitacion
     */
	@Test
	public void unicidadHabitacionTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del id del habitacion");
			pm = PersistenciaAlohandes.getInstance ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de habitacion incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de habitacion incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
			
			// Lectura de los tipos de bebida con la tabla vacía
			List <Habitacion> lista = pm.darHabitaciones();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());
			pm.modoPruebas();
			pm.asignarID(1000);
			String direccionhabitacion1 = "calle23C#69C-20";
			int cuposhabitacion1 = 4;
			String tipoHabitacion1 = "ESTANDAR";
			String Categoria1 = "cualquiera";
			int capacidad1 = 4;
			int numero1 = 3;
			Habitacion habitacion1 = pm.adicionarHabitacion(direccionhabitacion1, cuposhabitacion1, op.getId(), tipoHabitacion1, Categoria1, capacidad1, numero1);
			// Lectura de los tipos de bebida con un habitacion adicionado
			lista = pm.darHabitaciones();
			assertEquals ("Debe haber un habitacion creado !!", 1, lista.size ());
			String direccionhabitacion2 = "calle20C#20C-20";
			int cuposhabitacion2 = 4;
			String tipoHabitacion2 = "SEMISUITE";
			String Categoria2 = "nose";
			int capacidad2 = 4;
			int numero2 = 3;
			Habitacion habitacion2 = pm.adicionarHabitacion(direccionhabitacion2, cuposhabitacion2, op.getId(), tipoHabitacion2, Categoria2, capacidad2, numero2);
			assertNull ("No puede adicionar dos habitacions con el mismo id !!", habitacion2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla habitacion.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla habitacion");
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
