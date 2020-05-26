/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(BogotÃ¡	- Colombia)
 * Departamento	de	IngenierÃ­a	de	Sistemas	y	Computacién
 * Licenciado	bajo	el	esquema	Academic Free License versién 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Alohandes Uniandes
 * @version 1.0
 * @author GermÃ¡n Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.alohandes.negocio;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

import oracle.sql.DATE;
import uniandes.isis2304.alohandes.persistencia.PersistenciaAlohandes;

/**
 * Clase principal del negocio
 * Sarisface todos los requerimientos funcionales del negocio
 *
 * @author GermÃ¡n Bravo
 */
public class Alohandes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecucién
	 */
	private static Logger log = Logger.getLogger(Alohandes.class.getName());

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaAlohandes pa;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public Alohandes ()
	{
		pa = PersistenciaAlohandes.getInstance ();
	}

	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public Alohandes (JsonObject tableConfig)
	{
		pa = PersistenciaAlohandes.getInstance (tableConfig);
	}

	/**
	 * Cierra la conexién con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pa.cerrarUnidadPersistencia ();
	}

	/* ****************************************************************
	 * 			Métodos para manejar los(as) VIVIENDA
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Vivienda
	 * Adiciona entradas al log de la aplicacién
	 * @param direccion - direccion del Vivienda
	 * @param cupos - cupos del Vivienda
	 * @param idOperador - idOperador del Vivienda
	 * @return El objeto Vivienda adicionado. null si ocurre alguna Excepcién
	 */
	public Vivienda adicionarVivienda (String direccion, int cupos, long idOperador, String tipo)
	{
		log.info ("Adicionando nuevo(a) Vivienda ");
		Vivienda vivienda = pa.adicionarVivienda(direccion, cupos, idOperador, tipo);	
		log.info ("Adicionando Vivienda: " + vivienda);
		return vivienda;
	}

	/**
	 * Elimina un Vivienda por su id
	 * Adiciona entradas al log de la aplicacién
	 * @param id - El id del Vivienda a eliminar
	 * @return El némero de tuplas eliminadas
	 */
	public long eliminarViviendaPorId (long idVivienda)
	{
		log.info ("Eliminando Vivienda por id: " + idVivienda);
		long resp = pa.eliminarViviendaPorId(idVivienda);
		log.info ("Eliminando Vivienda por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los Vivienda en Alohandes
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos Vivienda, llenos con su informacién bÃ¡sica
	 */
	public List<Vivienda> darViviendas ()
	{
		log.info ("Consultando Vivienda");
		List<Vivienda> vivienda = pa.darViviendas();
		log.info ("Consultando Vivienda: " + vivienda.size() + " existentes");
		return vivienda;
	}

	/**
	 * Encuentra todos los Vivienda en Alohandes y los devuelve como una lista de VOVivienda
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos VOVivienda llenos con su informacién bÃ¡sica
	 */
	public List<VOVivienda> darVOVivienda ()
	{
		log.info ("Generando los VO de Vivienda");
		List<VOVivienda> voVivienda = new LinkedList<VOVivienda> ();
		for (Vivienda tb : pa.darViviendas ())
		{
			voVivienda.add (tb);
		}
		log.info ("Generando los VO de Vivienda: " + voVivienda.size() + " existentes");
		return voVivienda;

	}
	
	/**
	 * Encuentra la vivienda con el Id buscado en Alohandes
	 * Adiciona entradas al log de la aplicacion
	 * @return Un objeto tipo Vivienda con el id buscado
	 */
	public Vivienda darViviendaPorId(long idVivienda) {
		log.info("Buscando Vivienda con Id: " + idVivienda);
		Vivienda vivienda = pa.darViviendaPorId(idVivienda);
		log.info("Encontrada Vivienda: " + vivienda);
		return vivienda;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los(as) HABITACION
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Habitacion
	 * Adiciona entradas al log de la aplicacién
	 * @param direccion - direccion del Habitacion
	 * @param cupos - cupos del Habitacion
	 * @param idOperador - idOperador del Habitacion
	 * @param tipoHabitacion - tipoHabitacion del Habitacion
	 * @param categoria - categoria del Habitacion
	 * @param capacidad - capacidad del Habitacion
	 * @param numero - numero del Habitacion
	 * @return El objeto Habitacion adicionado. null si ocurre alguna Excepcién
	 */
	public Habitacion adicionarHabitacion (String direccion, int cupos, long idOperador, String tipoHabitacion, String categoria, int capacidad, int numero)
	{
		log.info ("Adicionando nuevo(a) Habitacion ");
		Habitacion habitacion = pa.adicionarHabitacion(direccion, cupos, idOperador, tipoHabitacion, categoria, capacidad, numero);
		log.info ("Adicionando Habitacion: " + habitacion);
		return habitacion;
	}

	/**
	 * Elimina un Habitacion por su id
	 * Adiciona entradas al log de la aplicacién
	 * @param id - El id del Habitacion a eliminar
	 * @return El némero de tuplas eliminadas
	 */
	public long eliminarHabitacionPorId (long idHabitacion)
	{
		log.info ("Eliminando Habitacion por id: " + idHabitacion);
		long resp = pa.eliminarHabitacionPorId(idHabitacion);
		log.info ("Eliminando Habitacion por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los Habitacion en Alohandes
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos Habitacion, llenos con su informacién bÃ¡sica
	 */
	public List<Habitacion> darHabitaciones ()
	{
		log.info ("Consultando Habitacion");
		List<Habitacion> habitacion = pa.darHabitaciones();
		log.info ("Consultando Habitacion: " + habitacion.size() + " existentes");
		return habitacion;
	}

	/**
	 * Encuentra todos los Habitacion en Alohandes y los devuelve como una lista de VOHabitacion
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos VOHabitacion llenos con su informacién bÃ¡sica
	 */
	public List<VOHabitacion> darVOHabitacion ()
	{
		log.info ("Generando los VO de Habitacion");
		List<VOHabitacion> voHabitacion = new LinkedList<VOHabitacion> ();
		for (Habitacion tb : pa.darHabitaciones ())
		{
			voHabitacion.add (tb);
		}
		log.info ("Generando los VO de Habitacion: " + voHabitacion.size() + " existentes");
		return voHabitacion;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los(as) APARTAMENTO
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Apartamento
	 * Adiciona entradas al log de la aplicacién
	 * @param direccion - direccion del Apartamento
	 * @param cupos - cupos del Apartamento
	 * @param idOperador - idOperador del Apartamento
	 * @param area - area del Apartamento
	 * @param amoblado - amoblado del Apartamento
	 * @param numeroHabitaciones - numeroHabitaciones del Apartamento
	 * @return El objeto Apartamento adicionado. null si ocurre alguna Excepcién
	 */
	public Apartamento adicionarApartamento ( String direccion, int cupos, long idOperador, double area, int amoblado, int numeroHabitaciones)
	{
		//System.out.println("poeque nuca llego por asfqwfgfd");
		log.info ("Adicionando nuevo(a) Apartamento ");
		Apartamento apartamento = pa.adicionarApartamento(direccion, cupos, idOperador, area, amoblado, numeroHabitaciones);	
		log.info ("Adicionando Apartamento: " + apartamento);
		return apartamento;
	}

	/**
	 * Elimina un Apartamento por su id
	 * Adiciona entradas al log de la aplicacién
	 * @param id - El id del Apartamento a eliminar
	 * @return El némero de tuplas eliminadas
	 */
	public long eliminarApartamentoPorId (long idApartamento)
	{
		log.info ("Eliminando Apartamento por id: " + idApartamento);
		long resp = pa.eliminarApartamentoPorId(idApartamento);
		log.info ("Eliminando Apartamento por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los Apartamento en Alohandes
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos Apartamento, llenos con su informacién bÃ¡sica
	 */
	public List<Apartamento> darApartamentos ()
	{
		log.info ("Consultando Apartamento");
		
		List<Apartamento> apartamento = pa.darApartamentos();
		System.out.println("hay fallas???" + apartamento.size());
		System.out.println("Consultando Apartamento: " + apartamento.size() + " existentes");
		log.info ("Consultando Apartamento: " + apartamento.size() + " existentes");
		return apartamento;
	}

	/**
	 * Encuentra todos los Apartamento en Alohandes y los devuelve como una lista de VOApartamento
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos VOApartamento llenos con su informacién bÃ¡sica
	 */
	public List<VOApartamento> darVOApartamento ()
	{
		log.info ("Generando los VO de Apartamento");
		List<VOApartamento> voApartamento = new LinkedList<VOApartamento> ();
		for (Apartamento tb : pa.darApartamentos ())
		{
			voApartamento.add (tb);
		}
		log.info ("Generando los VO de Apartamento: " + voApartamento.size() + " existentes");
		return voApartamento;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los(as) ESPORADICO
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Esporadico
	 * Adiciona entradas al log de la aplicacién
	 * @param direccion - direccion del Esporadico
	 * @param cupos - cupos del Esporadico
	 * @param idOperador - idOperador del Esporadico
	 * @param area - area del Esporadico
	 * @param amoblado - amoblado del Esporadico
	 * @param numeroHabitaciones - numeroHabitaciones del Esporadico
	 * @param nochesanio - nochesanio del Esporadico
	 * @param idSeguro - idSeguro del Esporadico
	 * @return El objeto Esporadico adicionado. null si ocurre alguna Excepcién
	 */
	public Esporadico adicionarEsporadico (String direccion, int cupos, long idOperador, double area, int amoblado, int numeroHabitaciones, int nochesanio, long idSeguro)
	{
		log.info ("Adicionando nuevo(a) Esporadico ");
		Esporadico esporadico = pa.adicionarEsporadico(direccion, cupos, idOperador, area, amoblado, numeroHabitaciones, nochesanio, idSeguro);	
		log.info ("Adicionando Esporadico: " + esporadico);
		return esporadico;
	}

	/**
	 * Elimina un Esporadico por su id
	 * Adiciona entradas al log de la aplicacién
	 * @param id - El id del Esporadico a eliminar
	 * @return El némero de tuplas eliminadas
	 */
	public long eliminarEsporadicoPorId (long idEsporadico)
	{
		log.info ("Eliminando Esporadico por id: " + idEsporadico);
		long resp = pa.eliminarEsporadicoPorId(idEsporadico);
		log.info ("Eliminando Esporadico por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los Esporadico en Alohandes
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos Esporadico, llenos con su informacién bÃ¡sica
	 */
	public List<Esporadico> darEsporadicos ()
	{
		log.info ("Consultando Esporadico");
		List<Esporadico> esporadico = pa.darEsporadicos();
		log.info ("Consultando Esporadico: " + esporadico.size() + " existentes");
		return esporadico;
	}

	/**
	 * Encuentra todos los Esporadico en Alohandes y los devuelve como una lista de VOEsporadico
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos VOEsporadico llenos con su informacién bÃ¡sica
	 */
	public List<VOEsporadico> darVOEsporadico ()
	{
		log.info ("Generando los VO de Esporadico");
		List<VOEsporadico> voEsporadico = new LinkedList<VOEsporadico> ();
		for (Esporadico tb : pa.darEsporadicos ())
		{
			voEsporadico.add (tb);
		}
		log.info ("Generando los VO de Esporadico: " + voEsporadico.size() + " existentes");
		return voEsporadico;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los(as) SEGURO
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Seguro
	 * Adiciona entradas al log de la aplicacién
	 * @param empresa - empresa del Seguro
	 * @param monto - monto del Seguro
	 * @param inicioSeguro - inicioSeguro del Seguro
	 * @param finSeguro - finSeguro del Seguro
	 * @return El objeto Seguro adicionado. null si ocurre alguna Excepcién
	 */
	public Seguro adicionarSeguro ( String empresa, int monto, DATE inicioSeguro, DATE finSeguro)
	{
		log.info ("Adicionando nuevo(a) Seguro ");
		Seguro seguro = pa.adicionarSeguro(empresa, monto, inicioSeguro, finSeguro);	
		log.info ("Adicionando Seguro: " + seguro);
		return seguro;
	}

	/**
	 * Elimina un Seguro por su id
	 * Adiciona entradas al log de la aplicacién
	 * @param id - El id del Seguro a eliminar
	 * @return El némero de tuplas eliminadas
	 */
	public long eliminarSeguroPorId (long idSeguro)
	{
		log.info ("Eliminando Seguro por id: " + idSeguro);
		long resp = pa.eliminarSeguroPorId(idSeguro);
		log.info ("Eliminando Seguro por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los Seguro en Alohandes
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos Seguro, llenos con su informacién bÃ¡sica
	 */
	public List<Seguro> darSeguros ()
	{
		log.info ("Consultando Seguro");
		List<Seguro> seguro = pa.darSeguros();
		log.info ("Consultando Seguro: " + seguro.size() + " existentes");
		return seguro;
	}

	/**
	 * Encuentra todos los Seguro en Alohandes y los devuelve como una lista de VOSeguro
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos VOSeguro llenos con su informacién bÃ¡sica
	 */
	public List<VOSeguro> darVOSeguro ()
	{
		log.info ("Generando los VO de Seguro");
		List<VOSeguro> voSeguro = new LinkedList<VOSeguro> ();
		for (Seguro tb : pa.darSeguros ())
		{
			voSeguro.add (tb);
		}
		log.info ("Generando los VO de Seguro: " + voSeguro.size() + " existentes");
		return voSeguro;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los(as) CUARTO
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Cuarto
	 * Adiciona entradas al log de la aplicacién
	 * @param direccion - direccion del Cuarto
	 * @param cupos - cupos del Cuarto
	 * @param idOperador - idOperador del Cuarto
	 * @param banioPrivado - banioPrivado del Cuarto
	 * @param cuartoPrivado - cuartoPrivado del Cuarto
	 * @param esquema - esquema del Cuarto
	 * @param menaje - mensaje del Cuarto
	 * @return El objeto Cuarto adicionado. null si ocurre alguna Excepcién
	 */
	public Cuarto adicionarCuarto (String direccion, int cupos, long idOperador, int banio_privado, int cuarto_privado, String esquema, String menaje)
	{
		log.info ("Adicionando nuevo(a) Cuarto ");
		Cuarto cuarto = pa.adicionarCuarto(direccion, cupos, idOperador, banio_privado, cuarto_privado, esquema, menaje);
		log.info ("Adicionando Cuarto: " + cuarto);
		return cuarto;
	}

	/**
	 * Elimina un Cuarto por su id
	 * Adiciona entradas al log de la aplicacién
	 * @param id - El id del Cuarto a eliminar
	 * @return El némero de tuplas eliminadas
	 */
	public long eliminarCuartoPorId (long idCuarto)
	{
		log.info ("Eliminando Cuarto por id: " + idCuarto);
		long resp = pa.eliminarCuartoPorId(idCuarto);
		log.info ("Eliminando Cuarto por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los Cuarto en Alohandes
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos Cuarto, llenos con su informacién bÃ¡sica
	 */
	public List<Cuarto> darCuartos ()
	{
		log.info ("Consultando Cuarto");
		List<Cuarto> cuarto = pa.darCuartos();
		log.info ("Consultando Cuarto: " + cuarto.size() + " existentes");
		return cuarto;
	}

	/**
	 * Encuentra todos los Cuarto en Alohandes y los devuelve como una lista de VOCuarto
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos VOCuarto llenos con su informacién bÃ¡sica
	 */
	public List<VOCuarto> darVOCuarto ()
	{
		log.info ("Generando los VO de Cuarto");
		List<VOCuarto> voCuarto = new LinkedList<VOCuarto> ();
		for (Cuarto tb : pa.darCuartos ())
		{
			voCuarto.add (tb);
		}
		log.info ("Generando los VO de Cuarto: " + voCuarto.size() + " existentes");
		return voCuarto;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los(as) OPERADOR
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Operador
	 * Adiciona entradas al log de la aplicacién
	 * @param nombre - nombre del Operador
	 * @param email - email del Operador
	 * @param numero - numero del Operador
	 * @return El objeto Operador adicionado. null si ocurre alguna Excepcién
	 */
	public Operador adicionarOperador (String nombre, String email, String numero, String tipoOperador)
	{
		System.out.println("por que fallas?");
		log.info ("Adicionando nuevo(a) Operador ");
		Operador operador = pa.adicionarOperador(nombre, email, numero, tipoOperador);
		log.info ("Adicionando Operador: " + operador);
		return operador;
	}

	/**
	 * Elimina un Operador por su id
	 * Adiciona entradas al log de la aplicacién
	 * @param id - El id del Operador a eliminar
	 * @return El némero de tuplas eliminadas
	 */
	public long eliminarOperadorPorId (long idOperador)
	{
		log.info ("Eliminando Operador por id: " + idOperador);
		long resp = pa.eliminarOperadorPorId(idOperador);
		log.info ("Eliminando Operador por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los Operador en Alohandes
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos Operador, llenos con su informacién bÃ¡sica
	 */
	public List<Operador> darOperadores ()
	{
		log.info ("Consultando Operador");
		List<Operador> operador = pa.darOperadores();
		log.info ("Consultando Operador: " + operador.size() + " existentes");
		return operador;
	}

	/**
	 * Encuentra todos los Operador en Alohandes y los devuelve como una lista de VOOperador
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos VOOperador llenos con su informacién bÃ¡sica
	 */
	public List<VOOperador> darVOOperador ()
	{
		log.info ("Generando los VO de Operador");
		List<VOOperador> voOperador = new LinkedList<VOOperador> ();
		for (Operador tb : pa.darOperadores ())
		{
			voOperador.add (tb);
		}
		log.info ("Generando los VO de Operador: " + voOperador.size() + " existentes");
		return voOperador;
	}

	/**
	 * Encuentra un Operador en Alohandes y lo devuelve como un Operador
	 * Adiciona entradas al log de la aplicacion
	 * @return Un objeto Operador que coincida con el nombre y tipo buscados
	 */
	public Operador darOperadorPorNombre(String nombre, String tipo) {
		log.info("Buscando Operador con nombre: " + nombre);
		Operador operador = pa.darOperadorPorNombre(nombre, tipo);
		log.info("Encontrado Operador: " + operador);
		return operador;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los(as) VIVIENDA_UNIVERSITARIA
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un ViviendaUniversitaria
	 * Adiciona entradas al log de la aplicacién
	 * @param nombre - nombre del Operador
	 * @param email - email del Operador
	 * @param numero - numero del Operador
	 * @return El objeto Operador adicionado. null si ocurre alguna Excepcién
	 */
	public ViviendaUniversitaria adicionarViviendaUniversitaria (String nombre, String email, String numero)
	{
		log.info ("Adicionando nuevo(a) ViviendaUniversitaria ");
		ViviendaUniversitaria viviendaUniversitaria = pa.adicionarViviendaUniversitaria(nombre, email, numero);
		log.info ("Adicionando ViviendaUniversitaria: " + viviendaUniversitaria);
		return viviendaUniversitaria;
	}

	/**
	 * Elimina un ViviendaUniversitaria por su id
	 * Adiciona entradas al log de la aplicacién
	 * @param id - El id de la ViviendaUniversitaria a eliminar
	 * @return El némero de tuplas eliminadas
	 */
	public long eliminarViviendaUniversitariaPorId (long idViviendaUniversitaria)
	{
		log.info ("Eliminando ViviendaUniversitaria por id: " + idViviendaUniversitaria);
		long resp = pa.eliminarViviendaUniversitariaPorId(idViviendaUniversitaria);
		log.info ("Eliminando ViviendaUniversitaria por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los ViviendaUniversitaria en Alohandes
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos ViviendaUniversitaria, llenos con su informacién bÃ¡sica
	 */
	public List<ViviendaUniversitaria> darViviendasUniversitarias ()
	{
		log.info ("Consultando ViviendaUniversitaria");
		List<ViviendaUniversitaria> viviendaUniversitaria = pa.darViviendasUniversitarias();
		log.info ("Consultando ViviendaUniversitaria: " + viviendaUniversitaria.size() + " existentes");
		return viviendaUniversitaria;
	}

	/**
	 * Encuentra todos los Operador en Alohandes y los devuelve como una lista de VOOperador
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos VOOperador llenos con su informacién bÃ¡sica
	 */
	public List<VOViviendaUniversitaria> darVOViviendaUniversitaria ()
	{
		log.info ("Generando los VO de ViviendaUniversitaria");
		List<VOViviendaUniversitaria> voViviendaUniversitaria = new LinkedList<VOViviendaUniversitaria> ();
		for (ViviendaUniversitaria tb : pa.darViviendasUniversitarias() )
		{
			voViviendaUniversitaria.add (tb);
		}
		log.info ("Generando los VO de ViviendaUniversitaria: " + voViviendaUniversitaria.size() + " existentes");
		return voViviendaUniversitaria;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los(as) HOTELERIA
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Hoteleria
	 * Adiciona entradas al log de la aplicacién
	 * @param nombre - nombre del Hoteleria
	 * @param email - email del Hoteleria
	 * @param numero - numero del Hoteleria
	 * @param tipoHoteleria - tipoHoteleria del Hoteleria
	 * @param horaApertura - horaApertura del Hoteleria
	 * @param horaCierre - horaCierre del Hoteleria
	 * @return El objeto Hoteleria adicionado. null si ocurre alguna Excepcién
	 */
	public Hoteleria adicionarHoteleria (String nombre, String email, String numero, String tipoHoteleria, String horaApertura, String horaCierre)
	{
		log.info ("Adicionando nuevo(a) Hoteleria ");
		Hoteleria hoteleria = pa.adicionarHoteleria(nombre, email, numero, tipoHoteleria, horaApertura, horaCierre);	
		log.info ("Adicionando Hoteleria: " + hoteleria);
		return hoteleria;
	}

	/**
	 * Elimina un Hoteleria por su id
	 * Adiciona entradas al log de la aplicacién
	 * @param id - El id del Hoteleria a eliminar
	 * @return El némero de tuplas eliminadas
	 */
	public long eliminarHoteleriaPorId (long idHoteleria)
	{
		log.info ("Eliminando Hoteleria por id: " + idHoteleria);
		long resp = pa.eliminarHoteleriaPorId(idHoteleria);
		log.info ("Eliminando Hoteleria por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los Hoteleria en Alohandes
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos Hoteleria, llenos con su informacién bÃ¡sica
	 */
	public List<Hoteleria> darHotelerias ()
	{
		log.info ("Consultando Hoteleria");
		List<Hoteleria> hoteleria = pa.darHotelerias();
		log.info ("Consultando Hoteleria: " + hoteleria.size() + " existentes");
		return hoteleria;
	}

	/**
	 * Encuentra todos los Hoteleria en Alohandes y los devuelve como una lista de VOHoteleria
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos VOHoteleria llenos con su informacién bÃ¡sica
	 */
	public List<VOHoteleria> darVOHoteleria ()
	{
		log.info ("Generando los VO de Hoteleria");
		List<VOHoteleria> voHoteleria = new LinkedList<VOHoteleria> ();
		for (Hoteleria tb : pa.darHotelerias ())
		{
			voHoteleria.add (tb);
		}
		log.info ("Generando los VO de Hoteleria: " + voHoteleria.size() + " existentes");
		return voHoteleria;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los(as) PERSONA_NATURAL
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Persona_Natural
	 * Adiciona entradas al log de la aplicacién
	 * @param nombre - nombre del Persona_Natural
	 * @param email - email del Persona_Natural
	 * @param numero - numero del Persona_Natural
	 * @param documento - documento del Persona_Natural
	 * @param tipoPersona - tipoPersona del Persona_Natural
	 * @return El objeto Persona_Natural adicionado. null si ocurre alguna Excepcién
	 */
	public Persona_Natural adicionarPersona_Natural (String nombre, String email, String numero, String documento, String tipoPersona)
	{
		log.info ("Adicionando nuevo(a) Persona_Natural ");
		Persona_Natural persona_Natural = pa.adicionarPersona_Natural(nombre, email, documento, numero, tipoPersona);	
		log.info ("Adicionando Persona_Natural: " + persona_Natural);
		return persona_Natural;
	}

	/**
	 * Elimina un Persona_Natural por su id
	 * Adiciona entradas al log de la aplicacién
	 * @param id - El id del Persona_Natural a eliminar
	 * @return El némero de tuplas eliminadas
	 */
	public long eliminarPersona_NaturalPorId (long idPersona_Natural)
	{
		log.info ("Eliminando Persona_Natural por id: " + idPersona_Natural);
		long resp = pa.eliminarPersona_NaturalPorId(idPersona_Natural);
		log.info ("Eliminando Persona_Natural por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los Persona_Natural en Alohandes
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos Persona_Natural, llenos con su informacién bÃ¡sica
	 */
	public List<Persona_Natural> darPersona_Naturales ()
	{
		log.info ("Consultando Persona_Natural");
		List<Persona_Natural> persona_Natural = pa.darPersona_Naturales();
		log.info ("Consultando Persona_Natural: " + persona_Natural.size() + " existentes");
		return persona_Natural;
	}

	/**
	 * Encuentra todos los Persona_Natural en Alohandes y los devuelve como una lista de VOPersona_Natural
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos VOPersona_Natural llenos con su informacién bÃ¡sica
	 */
	public List<VOPersona_Natural> darVOPersona_Natural ()
	{
		log.info ("Generando los VO de Persona_Natural");
		List<VOPersona_Natural> voPersona_Natural = new LinkedList<VOPersona_Natural> ();
		for (Persona_Natural tb : pa.darPersona_Naturales ())
		{
			voPersona_Natural.add (tb);
		}
		log.info ("Generando los VO de Persona_Natural: " + voPersona_Natural.size() + " existentes");
		return voPersona_Natural;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los(as) GANANCIAS
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Ganancias
	 * Adiciona entradas al log de la aplicacién
	 * @param cantidad - cantidad del Ganancias
	 * @param mes - mes del Ganancias
	 * @param anio - anio del Ganancias
	 * @param idOperador - idOperador del Ganancias
	 * @return El objeto Ganancias adicionado. null si ocurre alguna Excepcién
	 */
	public Ganancias adicionarGanancias (long cantidad, DATE fecha, long idOperador)
	{
		log.info ("Adicionando nuevo(a) Ganancias ");
		Ganancias ganancias = pa.adicionarGanancias(cantidad, fecha, idOperador);	
		log.info ("Adicionando Ganancias: " + ganancias);
		return ganancias;
	}

	/**
	 * Elimina un Ganancias por su id
	 * Adiciona entradas al log de la aplicacién
	 * @param id - El id del Ganancias a eliminar
	 * @return El némero de tuplas eliminadas
	 */
	public long eliminarGananciasPorId (long idGanancias)
	{
		log.info ("Eliminando Ganancias por id: " + idGanancias);
		long resp = pa.eliminarGananciasPorId(idGanancias);
		log.info ("Eliminando Ganancias por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los Ganancias en Alohandes
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos Ganancias, llenos con su informacién bÃ¡sica
	 */
	public List<Ganancias> darGanancias ()
	{
		log.info ("Consultando Ganancias");
		List<Ganancias> ganancias = pa.darGanancias();
		log.info ("Consultando Ganancias: " + ganancias.size() + " existentes");
		return ganancias;
	}

	/**
	 * Encuentra todos los Ganancias en Alohandes y los devuelve como una lista de VOGanancias
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos VOGanancias llenos con su informacién bÃ¡sica
	 */
	public List<VOGanancias> darVOGanancias ()
	{
		log.info ("Generando los VO de Ganancias");
		List<VOGanancias> voGanancias = new LinkedList<VOGanancias> ();
		for (Ganancias tb : pa.darGanancias ())
		{
			voGanancias.add (tb);
		}
		log.info ("Generando los VO de Ganancias: " + voGanancias.size() + " existentes");
		return voGanancias;
	}
	
	public void aumentarGanancias(Long aumento, long idOperador, int mes, int anio) {
		
		log.info("Aumentando las ganancias del operador: " + idOperador + " en " + aumento);
		pa.aumentarGanancias(aumento, idOperador, mes, anio);
		log.info("Aumentadas las ganancias del operador: " + idOperador);
	}

	/* ****************************************************************
	 * 			Métodos para manejar los(as) CLIENTE
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Cliente
	 * Adiciona entradas al log de la aplicacién
	 * @param nombre - nombre del Cliente
	 * @param email - email del Cliente
	 * @param numero - numero del Cliente
	 * @param documento - documento del Cliente
	 * @param tipoCliente - tipoCliente del Cliente
	 * @return El objeto Cliente adicionado. null si ocurre alguna Excepcién
	 */
	public Cliente adicionarCliente ( String nombre, String email, String numero, String documento, String tipoCliente)
	{
		log.info ("Adicionando nuevo(a) Cliente ");
		Cliente cliente = pa.adicionarCliente(nombre, email, numero, tipoCliente, documento);
		log.info ("Adicionando Cliente: " + cliente);
		return cliente;
	}

	/**
	 * Elimina un Cliente por su id
	 * Adiciona entradas al log de la aplicacién
	 * @param id - El id del Cliente a eliminar
	 * @return El némero de tuplas eliminadas
	 */
	public long eliminarClientePorId (long idCliente)
	{
		log.info ("Eliminando Cliente por id: " + idCliente);
		long resp = pa.eliminarClientePorId(idCliente);
		log.info ("Eliminando Cliente por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los Cliente en Alohandes
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos Cliente, llenos con su informacién bÃ¡sica
	 */
	public List<Cliente> darClientes ()
	{
		log.info ("Consultando Cliente");
		List<Cliente> cliente = pa.darClientes();
		log.info ("Consultando Cliente: " + cliente.size() + " existentes");
		return cliente;
	}

	/**
	 * Encuentra todos los Cliente en Alohandes y los devuelve como una lista de VOCliente
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos VOCliente llenos con su informacién bÃ¡sica
	 */
	public List<VOCliente> darVOCliente ()
	{
		log.info ("Generando los VO de Cliente");
		List<VOCliente> voCliente = new LinkedList<VOCliente> ();
		for (Cliente tb : pa.darClientes ())
		{
			voCliente.add (tb);
		}
		log.info ("Generando los VO de Cliente: " + voCliente.size() + " existentes");
		return voCliente;
	}
	
	/**
	 * Encuentra un Cliente por su nombre
	 * Adiciona entradas al log de la aplicacién
	 * @return Objeto Cliente con el nombre buscado
	 */
	public Cliente darClientePorNombre( String nombreCliente ) {
		log.info("Buscanod el cliente con nombre: " + nombreCliente);
		Cliente cliente = pa.buscarClientePorNombre(nombreCliente);
		log.info("Encontrade el Cliente: " + cliente);
		return cliente;
	}
	
	/**
	 * Encuentra un Cliente por su nombre
	 * Adiciona entradas al log de la aplicacién
	 * @return Objeto Cliente con el nombre buscado
	 */
	public Cliente darClientePorId( long idCliente ) {
		log.info("Buscando el cliente con id: " + idCliente);
		Cliente cliente = pa.buscarClientePorId(idCliente);
		log.info("Encontrade el Cliente: " + cliente);
		return cliente;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los(as) RESERVA
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Reserva
	 * Adiciona entradas al log de la aplicacién
	 * @param inicio1 - inicio del Reserva
	 * @param duracion - duracion del Reserva
	 * @param periodoArrendamiento - periodoArrendamiento del Reserva
	 * @param idOferta - idOferta del Reserva
	 * @param idUsuario - idUsuario del Reserva
	 * @return El objeto Reserva adicionado. null si ocurre alguna Excepcién
	 */
	public Reserva adicionarReserva (DATE inicio1, DATE fin1, String periodoArrendamiento, long idOferta, long idUsuario, long idColectiva)
	{
		log.info ("Adicionando nuevo(a) Reserva ");
		Reserva reserva = pa.adicionarReserva(inicio1, fin1, periodoArrendamiento, idUsuario, idOferta, idColectiva);
		log.info ("Adicionando Reserva: " + reserva);
		return reserva;
	}

	/**
	 * Elimina un Reserva por su id
	 * Adiciona entradas al log de la aplicacién
	 * @param id - El id del Reserva a eliminar
	 * @return El némero de tuplas eliminadas
	 */
	public long eliminarReservaPorId (long idReserva)
	{
		log.info ("Eliminando Reserva por id: " + idReserva);
		long resp = pa.eliminarReservaPorId(idReserva);
		log.info ("Eliminando Reserva por id: " + resp + " tuplas eliminadas");
		return resp;
	}
	
	/**
	 * Retorna un Reserva por su id
	 * Adiciona entradas al log de la aplicacién
	 * @param id - El id del Reserva a eliminar
	 * @return La reserva
	 */
	public Reserva darReservaPorId (long idReserva)
	{
		log.info ("Retornando Reserva por id: " + idReserva);
		Reserva reserva = pa.darReservaPorId(idReserva);
		log.info ("Retornando Reserva por id: " + reserva + " tuplas eliminadas");
		return reserva;
	}

	/**
	 * Encuentra todos los Reserva en Alohandes
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos Reserva, llenos con su informacién bÃ¡sica
	 */
	public List<Reserva> darReservas ()
	{
		log.info ("Consultando Reserva");
		List<Reserva> reserva = pa.darReservas();
		log.info ("Consultando Reserva: " + reserva.size() + " existentes");
		return reserva;
	}

	/**
	 * Encuentra todos los Reserva en Alohandes y los devuelve como una lista de VOReserva
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos VOReserva llenos con su informacién bÃ¡sica
	 */
	public List<VOReserva> darVOReserva ()
	{
		log.info ("Generando los VO de Reserva");
		List<VOReserva> voReserva = new LinkedList<VOReserva> ();
		for (Reserva tb : pa.darReservas ())
		{
			voReserva.add ((VOReserva) tb);
		}
		log.info ("Generando los VO de Reserva: " + voReserva.size() + " existentes");
		return voReserva;
	}
	
	/**
	 * Encuentra todas las reservas de un cliente
	 * @param idCliente Identificador del cliente
	 * @return Todas las reservas del cliente buscado
	 */
	public List<Reserva> darReservasPorCliente(long idCliente) {
		log.info("Buscando reservas del cliente con id: " + idCliente);
		List<Reserva> reservas = pa.darReservasPorCliente(idCliente);
		log.info("Encontrado reservas del cliente");
		return reservas;
	}
	
	public List<Reserva> darReservasColectiva(long idReserva){
		log.info("Buscando reservas de la colectiva "+ idReserva);
		List<Reserva> reservas = pa.darReservasColectiva(idReserva);
		log.info("Enovntradas reservas");
		return reservas;
	}


	/* ****************************************************************
	 * 			Métodos para manejar los(as) RESERVA
	 *****************************************************************/

	/**
	 * 	 * Adiciona de manera persistente un Reserva
	 * Adiciona entradas al log de la aplicacién
	 * @param fechaRealizacion
	 * @param cantidad
	 * @param idCliente
	 * @return
	 */
	public ReservaColectiva adicionarReservaColectiva(DATE fechaRealizacion, int cantidad, long idCliente, ArrayList<String> lista,
			String tipo, String periodo, DATE inicio, DATE fin)
	{
		log.info ("Adicionando nuevo(a) Reserva ");
		ReservaColectiva reserva = pa.adicionarReservaColectiva(fechaRealizacion, cantidad, idCliente, lista, tipo, periodo, inicio, fin);
		log.info ("Adicionando Reserva: " + reserva);
		return reserva;
	}

	/**
	 * Elimina un Reserva por su id
	 * Adiciona entradas al log de la aplicacién
	 * @param id - El id del Reserva a eliminar
	 * @return El némero de tuplas eliminadas
	 */
	public long eliminarReservaColectivaPorId (long idReserva)
	{
		log.info ("Eliminando Reserva por id: " + idReserva);
		long resp = pa.eliminarReservaColectivaPorId(idReserva);
		log.info ("Eliminando Reserva por id: " + resp + " tuplas eliminadas");
		return resp;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los(as) OFERTA
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Oferta
	 * Adiciona entradas al log de la aplicacién
	 * @param precio - precio del Oferta
	 * @param periodo - periodo del Oferta
	 * @param idVivienda - idVivienda del Oferta
	 * @return El objeto Oferta adicionado. null si ocurre alguna Excepcién
	 */
	public Oferta adicionarOferta ( long precio, String periodo, long idVivienda, DATE fechaInicio, DATE fechaFin)
	{
		log.info ("Adicionando nuevo(a) Oferta ");
		Oferta oferta = pa.adicionarOferta(precio, periodo, idVivienda, fechaInicio, fechaFin);
		log.info ("Adicionando Oferta: " + oferta);
		return oferta;
	}

	/**
	 * Elimina un Oferta por su id
	 * Adiciona entradas al log de la aplicacién
	 * @param id - El id del Oferta a eliminar
	 * @return El némero de tuplas eliminadas
	 */
	public long eliminarOfertaPorId (long idOferta)
	{
		log.info ("Eliminando Oferta por id: " + idOferta);
		long resp = pa.eliminarOfertaPorId(idOferta);
		log.info ("Eliminando Oferta por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los Oferta en Alohandes
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos Oferta, llenos con su informacién bÃ¡sica
	 */
	public List<Oferta> darOfertas ()
	{
		log.info ("Consultando Oferta");
		List<Oferta> oferta = pa.darOfertas();
		log.info ("Consultando Oferta: " + oferta.size() + " existentes");
		return oferta;
	}

	/**
	 * Encuentra todos los Oferta en Alohandes y los devuelve como una lista de VOOferta
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos VOOferta llenos con su informacién bÃ¡sica
	 */
	public List<VOOferta> darVOOferta ()
	{
		log.info ("Generando los VO de Oferta");
		List<VOOferta> voOferta = new LinkedList<VOOferta> ();
		for (Oferta tb : pa.darOfertas ())
		{
			voOferta.add (tb);
		}
		log.info ("Generando los VO de Oferta: " + voOferta.size() + " existentes");
		return voOferta;
	}
	
	/**
	 * Encuentra una oferta segén su id en Alohandes
	 * Adiciona entradas al log de la aplicacién
	 * @return un objeto tipo Oferta con el id buscado
	 */
	public Oferta darOfertaPorId(long idOferta) {
		log.info("Buscando Oferta con id: " + idOferta);
		Oferta oferta = pa.darOfertaPorId(idOferta);
		log.info("Encontrada oferta: " + oferta);
		return oferta;
	}
	
	/**
	 * Retorna un grupo de ofertas que cumplan con los servicios pedidos
	 * @param lista Servicios requeridos
	 * @return lista de Ofertas con los servicios requeridos
	 */
	public List<Oferta> darOfertasConServicios(ArrayList<String> lista, DATE inicio, DATE fin) {
		log.info("Buscando ofertas con servicos");
		List<Oferta> ofertas = pa.darOfertasConServicios(lista, inicio, fin);
		log.info("Encontrado ofertas");
		return ofertas;
	}
	
	/**
	 * Retorna un grupo de ofertas que cumplan con los servicios pedidos
	 * @param lista Servicios requeridos
	 * @param tipo Tipo de operador requerido
	 * @return lista de Ofertas con los servicios requeridos
	 */
	public List<Oferta> darOfertasConServiciosYTipo(ArrayList<String> lista, String tipo, String periodo, DATE inicio, DATE fin) {
		log.info("Buscando ofertas con servicos");
		List<Oferta> ofertas = pa.darOfertasConServiciosYTipo(lista, tipo, periodo, inicio, fin);
		log.info("Encontrado ofertas");
		return ofertas;
	}
	
	
	/**
	 * Encuentra todas las reservas de un operador
	 * @param idOperador Identificador del operador
	 * @return Todas las reservas del operador buscado
	 */
	public List<Oferta> darOfertasPorOperador(long idOperador) {
		log.info("Buscando ofertas del operador con id: " + idOperador);
		List<Oferta> ofertas = pa.darOfertasPorOperador(idOperador);
		log.info("Encontrado ofertas del operador");
		return ofertas;
	}

	/**
	 * 
	 * @param idOferta
	 * @return
	 */
	public List<Reserva> deshabilitarOferta(long idOferta){
		log.info("Deshabilitando la oferta con id: " + idOferta);
		List<Reserva> reservas = pa.deshabilitarOferta(idOferta);
		log.info("Deshabilitada la oferta");
		return reservas;
	}
	
	/**
	 * 
	 * @param idOferta
	 * @return
	 */
	public long rehabilitarOferta(long idOferta){
		log.info("Deshabilitando la oferta con id: " + idOferta);
		long resp = pa.habilitarOferta(idOferta);
				log.info("Deshabilitada la oferta");
		return resp;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los(as) SERVICIO
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Servicio
	 * Adiciona entradas al log de la aplicacién
	 * @param nombre - nombre del Servicio
	 * @param costo - costo del Servicio
	 * @return El objeto Servicio adicionado. null si ocurre alguna Excepcién
	 */
	public Servicio adicionarServicio (String nombre, long costo)
	{
		log.info ("Adicionando nuevo(a) Servicio ");
		Servicio servicio = pa.adicionarServicio(nombre, costo);	
		log.info ("Adicionando Servicio: " + servicio);
		return servicio;
	}

	/**
	 * Elimina un Servicio por su id
	 * Adiciona entradas al log de la aplicacién
	 * @param id - El id del Servicio a eliminar
	 * @return El némero de tuplas eliminadas
	 */
	public long eliminarServicioPorId (long idServicio)
	{
		log.info ("Eliminando Servicio por id: " + idServicio);
		long resp = pa.eliminarServicioPorId(idServicio);
		log.info ("Eliminando Servicio por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los Servicio en Alohandes
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos Servicio, llenos con su informacién bÃ¡sica
	 */
	public List<Servicio> darServicios ()
	{
		log.info ("Consultando Servicio");
		List<Servicio> servicio = pa.darServicios();
		log.info ("Consultando Servicio: " + servicio.size() + " existentes");
		return servicio;
	}

	/**
	 * Encuentra todos los Servicio en Alohandes y los devuelve como una lista de VOServicio
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos VOServicio llenos con su informacién bÃ¡sica
	 */
	public List<VOServicio> darVOServicio ()
	{
		log.info ("Generando los VO de Servicio");
		List<VOServicio> voServicio = new LinkedList<VOServicio> ();
		for (Servicio tb : pa.darServicios ())
		{
			voServicio.add (tb);
		}
		log.info ("Generando los VO de Servicio: " + voServicio.size() + " existentes");
		return voServicio;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los(as) INCLUYE
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Incluye
	 * Adiciona entradas al log de la aplicacién
	 * @param idOferta - idOferta del Incluye
	 * @param idServicio - idServicio del Incluye
	 * @param incluido - incluido del Incluye
	 * @return El objeto Incluye adicionado. null si ocurre alguna Excepcién
	 */
	public Incluye adicionarIncluye (long idServicio, long idOferta, int incluido)
	{
		log.info ("Adicionando nuevo(a) Incluye ");
		Incluye incluye = pa.adicionarIncluye(idServicio, idOferta, incluido);	
		log.info ("Adicionando Incluye: " + incluye);
		return incluye;
	}

	/**
	 * Elimina un Incluye por su id
	 * Adiciona entradas al log de la aplicacién
	 * @param idServicio 
	 * @param idOferta 
	 * @param id - El id del Incluye a eliminar
	 * @return El némero de tuplas eliminadas
	 */
	public long eliminarIncluyePorId (long idServicio, long idOferta)
	{
		log.info ("Eliminando Incluye por ids: Servicio: " + idServicio + " - Oferta: " + idOferta);
		long resp = pa.eliminarIncluye(idServicio, idOferta);
		log.info ("Eliminando Incluye por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos los Incluye en Alohandes
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos Incluye, llenos con su informacién bÃ¡sica
	 */
	public List<Incluye> darIncluye ()
	{
		log.info ("Consultando Incluye");
		List<Incluye> incluye = pa.darIncluye();
		log.info ("Consultando Incluye: " + incluye.size() + " existentes");
		return incluye;
	}

	/**
	 * Encuentra todos los Incluye en Alohandes y los devuelve como una lista de VOIncluye
	 * Adiciona entradas al log de la aplicacién
	 * @return Una lista de objetos VOIncluye llenos con su informacién bÃ¡sica
	 */
	public List<VOIncluye> darVOIncluye ()
	{
		log.info ("Generando los VO de Incluye");
		List<VOIncluye> voIncluye = new LinkedList<VOIncluye> ();
		for (Incluye tb : pa.darIncluye())
		{
			voIncluye.add (tb);
		}
		log.info ("Generando los VO de Incluye: " + voIncluye.size() + " existentes");
		return voIncluye;
	}

	/* ****************************************************************
	 * 			Métodos para manejar las consultas
	 *****************************************************************/

	public long reqc1Actual(long idOperador) {
		log.info("Consultando dinero recibido en el anio actual por el operador con id: " + idOperador);
		long actual = pa.reqc1Actual(idOperador);
		log.info("El dinero consultado fue:  " + actual);
		return actual;
	}
	
	public long reqc1Corrido(long idOperador) {
		log.info("Consultando dinero recibido en el anio corrido por el operador con id: " + idOperador);
		long corrido = pa.reqc1Corrido(idOperador);
		log.info("El dinero consultado fue:  " + corrido);
		return corrido;	
	}
	
	public List<Oferta> reqc2(){
		log.info("Consultando las 20 ofertas mÃ¡s populares");
		List<Oferta> ofertas = pa.reqc2();
		log.info("Consultadas las 20 ofertas mÃ¡s populares");
		return ofertas;
		}

	public String reqC7MayorDemanda(String tiempo, String alojamiento	) {
		log.info("Analizando mayor demanda para tiempo: " + tiempo + " y alojamiento " + alojamiento);
		String ans = pa.reqC7MayorDemanda(tiempo, alojamiento);
		log.info("Analizado");
		return ans;
	}
	
	public String reqC7MenorDemanda(String tiempo, String alojamiento	) {
		log.info("Analizando menor demanda para tiempo: " + tiempo + " y alojamiento " + alojamiento);
		String ans = pa.reqC7MenorDemanda(tiempo, alojamiento);
		log.info("Analizado");
		return ans;
	}
	
	public String reqC7Ganancia(String tiempo, String alojamiento) {
		log.info("Analizando mayor ganancia para tiempo: " + tiempo + " y alojamiento " + alojamiento);
		String ans = pa.reqC7Ganancia(tiempo, alojamiento);
		log.info("Analizado");
		return ans;
	}
	
	public List<Cliente> reqC8(long vivienda) {
		log.info("Encontrando clientes frecuentes de la vivienda: " + vivienda);
		List<Cliente> clientes = pa.reqC8(vivienda);
		log.info("Encontrado los clientes");
		return clientes;
	}
	
	public List<Oferta> reqC9(){
		log.info("Buscando ofertas con poca demanda");
		List<Oferta> ofertas = pa.reqC9();
		log.info("Encontradas");
		return ofertas;
	}
	
	/* ****************************************************************
	 * 			Métodos para administracién
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Alohandes
	 * @return Un arreglo con 7 némeros que indican el némero de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarAlohandes ()
	{
		log.info ("Limpiando la BD de Alohandes");
		long [] borrrados = pa.limpiarAlohandes();	
		log.info ("Limpiando la BD de Alohandes: Listo!");
		return borrrados;
	}

	public void modoPruebas() {
		pa.modoPruebas();
	}

	public void desactivarModoPruebas() {
		pa.desactivarModoPruebas();
	}
	
	/**
	 * 
	 * @param id
	 */
	public void asignarID(long id) {
		pa.asignarID(id);
	}
	
}
