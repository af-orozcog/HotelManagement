package uniandes.isis2304.alohandes.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import uniandes.isis2304.alohandes.negocio.Esporadico;
import uniandes.isis2304.alohandes.negocio.Operador;
import uniandes.isis2304.alohandes.negocio.Seguro;
import uniandes.isis2304.alohandes.persistencia.PersistenciaAlohandes;

public class EsporadicoTest {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(EsporadicoTest.class.getName());
	
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private PersistenciaAlohandes pm;
	
	/**
	 * Método que prueba las operaciones sobre la tabla esporadico
	 * 1. Adicionar un esporadico
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un esporadico por su identificador
	 * 4. Borrar un esporadico por su nombre
	 */
    @Test
	public void CRDEsporadicoTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre esporadico");
			pm = PersistenciaAlohandes.getInstance ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de esporadico incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de esporadico incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
    		Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
			// Lectura de los tipos de bebida con la tabla vacía
			List <Esporadico> lista = pm.darEsporadicos();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());
			String empresa1 = "elSAPOINC";
			int monto1 = 1000;
			Timestamp inicio1 = new Timestamp(10000);
			Timestamp fin1 = new Timestamp(100000);
			Seguro seguro1 = pm.adicionarSeguro(empresa1, monto1, inicio1, fin1);
			
			// Lectura de los tipos de bebida con un esporadico adicionado
			String direccionesporadico1 = "calle23C#69C-20";
			int cuposesporadico1 = 4;
			String tipoesporadico1 = "ESTANDAR";
			String Categoria1 = "cualquiera";
			int capacidad1 = 4;
			int numero1 = 3;
			double area1 = 45.5;
			int numeroHabitaciones1 = 2;
			int nochesAnio1 = 0;
			Esporadico esporadico1 = pm.adicionarEsporadico(direccionesporadico1, cuposesporadico1, op.getId(), area1, true, numeroHabitaciones1, 0, seguro1.getId());
			lista = pm.darEsporadicos();
			
			assertEquals ("Debe haber un esporadico creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", esporadico1, lista.get (0));

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			String direccionesporadico2 = "calle23C#69C-20";
			int cuposesporadico2 = 4;
			String tipoesporadico2 = "ESTANDAR";
			String Categoria2 = "cualquiera";
			int capacidad2 = 4;
			int numero2 = 3;
			double area2 = 45.5;
			int numeroHabitaciones2 = 2;
			int nochesAnio2 = 0;
			Esporadico esporadico2 = pm.adicionarEsporadico(direccionesporadico2, cuposesporadico2, op.getId(), area2, true, numeroHabitaciones2, 0, seguro1.getId());
			lista = pm.darEsporadicos();
			assertEquals ("Debe haber dos tipos de bebida creados !!", 2, lista.size ());
			assertTrue ("El primer esporadico adicionado debe estar en la tabla", esporadico1.equals (lista.get (0)) || esporadico1.equals (lista.get (1)));
			assertTrue ("El segundo esporadico adicionado debe estar en la tabla", esporadico2.equals (lista.get (0)) || esporadico2.equals (lista.get (1)));

			// Prueba de eliminación de un esporadico, dado su identificador
			long tbEliminados = pm.eliminarEsporadicoPorId (esporadico1.getId ());
			assertEquals ("Debe haberse eliminado un esporadico !!", 1, tbEliminados);
			lista = pm.darEsporadicos();
			assertEquals ("Debe haber un solo esporadico !!", 1, lista.size ());
			assertFalse ("El primer esporadico adicionado NO debe estar en la tabla", esporadico1.equals (lista.get (0)));
			assertTrue ("El segundo esporadico adicionado debe estar en la tabla", esporadico2.equals (lista.get (0)));
			
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla esporadico.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla esporadico");
		}
		finally
		{
			pm.limpiarAlohandes();
    		pm.cerrarUnidadPersistencia ();    		
		}
	}
	
    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de esporadico
     */
	@Test
	public void unicidadEsporadicoTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del id del esporadico");
			pm = PersistenciaAlohandes.getInstance ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de esporadico incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de esporadico incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
			
			// Lectura de los tipos de bebida con la tabla vacía
			List <Esporadico> lista = pm.darEsporadicos();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());
			String empresa1 = "elSAPOINC";
			int monto1 = 1000;
			Timestamp inicio1 = new Timestamp(10000);
			Timestamp fin1 = new Timestamp(100000);
			Seguro seguro1 = pm.adicionarSeguro(empresa1, monto1, inicio1, fin1);
			
			
			
			pm.modoPruebas();
			pm.asignarID(1000);
			String direccionesporadico1 = "calle23C#69C-20";
			int cuposesporadico1 = 4;
			String tipoesporadico1 = "ESTANDAR";
			String Categoria1 = "cualquiera";
			int capacidad1 = 4;
			int numero1 = 3;
			double area1 = 45.5;
			int numeroHabitaciones1 = 2;
			int nochesAnio1 = 0;
			Esporadico esporadico1 = pm.adicionarEsporadico(direccionesporadico1, cuposesporadico1, op.getId(), area1, true, numeroHabitaciones1, 0, seguro1.getId());
			lista = pm.darEsporadicos();
			assertEquals ("Debe haber un esporadico creado !!", 1, lista.size ());
			
			
			String direccionesporadico2 = "calle23C#69C-20";
			int cuposesporadico2 = 4;
			String tipoesporadico2 = "ESTANDAR";
			String Categoria2 = "cualquiera";
			int capacidad2 = 4;
			int numero2 = 3;
			double area2 = 45.5;
			int numeroHabitaciones2 = 2;
			int nochesAnio2 = 0;
			Esporadico esporadico2 = pm.adicionarEsporadico(direccionesporadico2, cuposesporadico2, op.getId(), area2, true, numeroHabitaciones2, 0, seguro1.getId());
			assertNull ("No puede adicionar dos esporadicos con el mismo id !!", esporadico2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla esporadico.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla esporadico");
		}    				
		finally
		{
			pm.limpiarAlohandes();
    		pm.cerrarUnidadPersistencia ();    		
		}
	}

}
