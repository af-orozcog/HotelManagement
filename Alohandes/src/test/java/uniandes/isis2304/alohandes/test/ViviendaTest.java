package uniandes.isis2304.alohandes.test;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import uniandes.isis2304.alohandes.negocio.Operador;
import uniandes.isis2304.alohandes.negocio.Vivienda;
import uniandes.isis2304.alohandes.persistencia.PersistenciaAlohandes;

public class ViviendaTest {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(ViviendaTest.class.getName());
	
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private PersistenciaAlohandes pm;
	
	/**
	 * Método que prueba las operaciones sobre la tabla vivienda
	 * 1. Adicionar un vivienda
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un vivienda por su identificador
	 * 4. Borrar un vivienda por su nombre
	 */
    @Test
	public void CRDViviendaTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre vivienda");
			pm = PersistenciaAlohandes.getInstance ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de Vivienda incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de vivienda incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
    		Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
			// Lectura de los tipos de bebida con la tabla vacía
			List <Vivienda> lista = pm.darViviendas();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un vivienda adicionado
			String direccionVivienda1 = "calle23C#69C-20";
			int cuposVivienda1 = 4;
			
			Vivienda vivienda1 = pm.adicionarVivienda(direccionVivienda1, cuposVivienda1,op.getId(),"HABITACION");
			lista = pm.darViviendas();
			assertEquals ("Debe haber un vivienda creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", vivienda1.getDireccion(), lista.get(0).getDireccion());

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			String direccionVivienda2 = "calle24C#20C-202";
			int cuposVivienda2 = 4;
			
			Vivienda vivienda2 = pm.adicionarVivienda (direccionVivienda2,cuposVivienda2,op.getId(),"HABITACION");
			lista = pm.darViviendas();
			assertEquals ("Debe haber dos tipos de bebida creados !!", 2, lista.size ());
			assertTrue ("El primer vivienda adicionado debe estar en la tabla", vivienda1.getDireccion().equals(lista.get(0).getDireccion()) || vivienda1.getDireccion().equals(lista.get(1).getDireccion()));
			assertTrue ("El segundo vivienda adicionado debe estar en la tabla", vivienda2.getDireccion().equals(lista.get(0).getDireccion()) || vivienda2.getDireccion().equals(lista.get(1).getDireccion()));

			// Prueba de eliminación de un vivienda, dado su identificador
			long tbEliminados = pm.eliminarViviendaPorId (vivienda1.getId ());
			assertEquals ("Debe haberse eliminado un vivienda !!", 1, tbEliminados);
			lista = pm.darViviendas();
			assertEquals ("Debe haber un solo vivienda !!", 1, lista.size ());
			assertFalse ("El primer vivienda adicionado NO debe estar en la tabla", vivienda1.getDireccion().equals(lista.get(0).getDireccion()));
			assertTrue ("El segundo vivienda adicionado debe estar en la tabla", vivienda2.getDireccion().equals (lista.get(0).getDireccion()));
			
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla vivienda.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla vivienda");
		}
		finally
		{
			pm.limpiarAlohandes();
    		pm.cerrarUnidadPersistencia ();    		
		}
	}
	
    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de vivienda
     */
	@Test
	public void unicidadviviendaTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del id del vivienda");
			pm = PersistenciaAlohandes.getInstance ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de vivienda incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de vivienda incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
			String direccionVivienda1 = "calle23C#69C-20";
			int cuposVivienda1 = 4;
			pm.modoPruebas();
			pm.asignarID(1000);
			// Lectura de los tipos de bebida con la tabla vacía
			List <Vivienda> lista = pm.darViviendas();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());
			
			// Lectura de los tipos de bebida con un vivienda adicionado
			Vivienda vivienda1 = pm.adicionarVivienda (direccionVivienda1,cuposVivienda1,op.getId(),"HABITACION");
			lista = pm.darViviendas();
			assertEquals ("Debe haber un vivienda creado !!", 1, lista.size ());
			String direccionVivienda2 = "calle24C#20C-20";
			int cuposVivienda2 = 4;
			
			Vivienda vivienda2 = pm.adicionarVivienda (direccionVivienda2, cuposVivienda2,op.getId(),"HABITACION");
			assertNull ("No puede adicionar dos viviendas con el mismo id !!", vivienda2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla vivienda.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla vivienda");
		}    				
		finally
		{
			pm.limpiarAlohandes();
    		pm.cerrarUnidadPersistencia ();    		
		}
	}

}
