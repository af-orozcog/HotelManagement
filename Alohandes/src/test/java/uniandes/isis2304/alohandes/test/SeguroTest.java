package uniandes.isis2304.alohandes.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import uniandes.isis2304.alohandes.negocio.Operador;
import uniandes.isis2304.alohandes.negocio.Seguro;
import uniandes.isis2304.alohandes.persistencia.PersistenciaAlohandes;

public class SeguroTest {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(SeguroTest.class.getName());
	
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private PersistenciaAlohandes pm;
	
	/**
	 * Método que prueba las operaciones sobre la tabla seguro
	 * 1. Adicionar un seguro
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un seguro por su identificador
	 * 4. Borrar un seguro por su nombre
	 */
    @Test
	public void CRDSeguroTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre seguro");
			pm = PersistenciaAlohandes.getInstance ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de seguro incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de seguro incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
    		Operador op = pm.adicionarOperador("..", "..", "..", "PERSONA_NATURAL");
			// Lectura de los tipos de bebida con la tabla vacía
			List <Seguro> lista = pm.darSeguros();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un seguro adicionado
			String empresa1 = "elSAPOINC";
			int monto1 = 1000;
			Timestamp inicio1 = new Timestamp(10000);
			Timestamp fin1 = new Timestamp(100000);
			Seguro seguro1 = pm.adicionarSeguro(empresa1, monto1, inicio1, fin1);
			lista = pm.darSeguros();
			
			assertEquals ("Debe haber un seguro creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", seguro1, lista.get (0));

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			String empresa2 = "elSAPOINC";
			int monto2 = 1000;
			Timestamp inicio2 = new Timestamp(10000);
			Timestamp fin2 = new Timestamp(100000);
			Seguro seguro2 = pm.adicionarSeguro(empresa2, monto2, inicio2, fin2);
			lista = pm.darSeguros();
			assertEquals ("Debe haber dos tipos de bebida creados !!", 2, lista.size ());
			assertTrue ("El primer seguro adicionado debe estar en la tabla", seguro1.equals (lista.get (0)) || seguro1.equals (lista.get (1)));
			assertTrue ("El segundo seguro adicionado debe estar en la tabla", seguro2.equals (lista.get (0)) || seguro2.equals (lista.get (1)));

			// Prueba de eliminación de un seguro, dado su identificador
			long tbEliminados = pm.eliminarSeguroPorId (seguro1.getId ());
			assertEquals ("Debe haberse eliminado un seguro !!", 1, tbEliminados);
			lista = pm.darSeguros();
			assertEquals ("Debe haber un solo seguro !!", 1, lista.size ());
			assertFalse ("El primer seguro adicionado NO debe estar en la tabla", seguro1.equals (lista.get (0)));
			assertTrue ("El segundo seguro adicionado debe estar en la tabla", seguro2.equals (lista.get (0)));
			
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla seguro.\n";
			msg += "Revise el log de pm y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla seguro");
		}
		finally
		{
			pm.limpiarAlohandes();
    		pm.cerrarUnidadPersistencia ();    		
		}
	}
	
    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de seguro
     */
	@Test
	public void unicidadSeguroTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del id del seguro");
			pm = PersistenciaAlohandes.getInstance ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de seguro incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de seguro incompleta. No se pudo conectar a la base de datos !!.\n";
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
			List <Seguro> lista = pm.darSeguros();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());
			pm.modoPruebas();
			pm.asignarID(1000);
			String empresa1 = "elSAPOINC";
			int monto1 = 1000;
			Timestamp inicio1 = new Timestamp(10000);
			Timestamp fin1 = new Timestamp(100000);
			Seguro seguro1 = pm.adicionarSeguro(empresa1, monto1, inicio1, fin1);
			lista = pm.darSeguros();
			assertEquals ("Debe haber un seguro creado !!", 1, lista.size ());
			
			
			String empresa2 = "elSAPOINC";
			int monto2 = 1000;
			Timestamp inicio2 = new Timestamp(10000);
			Timestamp fin2 = new Timestamp(100000);
			Seguro seguro2 = pm.adicionarSeguro(empresa2, monto2, inicio2, fin2);
			assertNull ("No puede adicionar dos seguros con el mismo id !!", seguro2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla seguro.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla seguro");
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
