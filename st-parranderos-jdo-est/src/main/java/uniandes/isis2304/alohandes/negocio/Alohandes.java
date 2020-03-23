/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Alohandes Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

import uniandes.isis2304.alohandes.persistencia.PersistenciaAlohandes;

/**
 * Clase principal del negocio
 * Sarisface todos los requerimientos funcionales del negocio
 *
 * @author Germán Bravo
 */
public class Alohandes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
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
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
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
	* Adiciona entradas al log de la aplicación
	* @param direccion - direccion del Vivienda
	* @param cupos - cupos del Vivienda
	* @param idOperador - idOperador del Vivienda
	* @return El objeto Vivienda adicionado. null si ocurre alguna Excepción
	*/
	public Vivienda adicionarVivienda (String direccion, int cupos, long idOperador)
	{
	log.info ("Adicionando nuevo(a) Vivienda ");
	Vivienda vivienda = pa.adicionarVivienda(direccion, cupos, idOperador);	
	log.info ("Adicionando Vivienda: " + vivienda);
	return vivienda;
	}

	/**
	 * Elimina un Vivienda por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del Vivienda a eliminar
	 * @return El número de tuplas eliminadas
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Vivienda, llenos con su información básica
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOVivienda llenos con su información básica
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

	/* ****************************************************************
	* 			Métodos para manejar los(as) HABITACION
	*****************************************************************/
	/**
	* Adiciona de manera persistente un Habitacion
	* Adiciona entradas al log de la aplicación
	* @param direccion - direccion del Habitacion
	* @param cupos - cupos del Habitacion
	* @param idOperador - idOperador del Habitacion
	* @param tipoHabitacion - tipoHabitacion del Habitacion
	* @param categoria - categoria del Habitacion
	* @param capacidad - capacidad del Habitacion
	* @param numero - numero del Habitacion
	* @return El objeto Habitacion adicionado. null si ocurre alguna Excepción
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
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del Habitacion a eliminar
	 * @return El número de tuplas eliminadas
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Habitacion, llenos con su información básica
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOHabitacion llenos con su información básica
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
	* Adiciona entradas al log de la aplicación
	* @param direccion - direccion del Apartamento
	* @param cupos - cupos del Apartamento
	* @param idOperador - idOperador del Apartamento
	* @param area - area del Apartamento
	* @param amoblado - amoblado del Apartamento
	* @param numeroHabitaciones - numeroHabitaciones del Apartamento
	* @return El objeto Apartamento adicionado. null si ocurre alguna Excepción
	*/
	public Apartamento adicionarApartamento ( String direccion, int cupos, long idOperador, double area, boolean amoblado, int numeroHabitaciones)
	{
	log.info ("Adicionando nuevo(a) Apartamento ");
	Apartamento apartamento = pa.adicionarApartamento(direccion, cupos, idOperador, area, amoblado, numeroHabitaciones);	
	log.info ("Adicionando Apartamento: " + apartamento);
	return apartamento;
	}

	/**
	 * Elimina un Apartamento por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del Apartamento a eliminar
	 * @return El número de tuplas eliminadas
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Apartamento, llenos con su información básica
	 */
	public List<Apartamento> darApartamentos ()
	{
		log.info ("Consultando Apartamento");
	    List<Apartamento> apartamento = pa.darApartamentos();
	    log.info ("Consultando Apartamento: " + apartamento.size() + " existentes");
	    return apartamento;
	}

	/**
	 * Encuentra todos los Apartamento en Alohandes y los devuelve como una lista de VOApartamento
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOApartamento llenos con su información básica
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
	* Adiciona entradas al log de la aplicación
	* @param direccion - direccion del Esporadico
	* @param cupos - cupos del Esporadico
	* @param idOperador - idOperador del Esporadico
	* @param area - area del Esporadico
	* @param amoblado - amoblado del Esporadico
	* @param numeroHabitaciones - numeroHabitaciones del Esporadico
	* @param nochesAño - nochesAño del Esporadico
	* @param idSeguro - idSeguro del Esporadico
	* @return El objeto Esporadico adicionado. null si ocurre alguna Excepción
	*/
	public Esporadico adicionarEsporadico (String direccion, int cupos, long idOperador, double area, boolean amoblado, int numeroHabitaciones, int nochesAño, long idSeguro)
	{
	log.info ("Adicionando nuevo(a) Esporadico ");
	Esporadico esporadico = pa.adicionarEsporadico(direccion, cupos, idOperador, area, amoblado, numeroHabitaciones, nochesAño, idSeguro);	
	log.info ("Adicionando Esporadico: " + esporadico);
	return esporadico;
	}

	/**
	 * Elimina un Esporadico por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del Esporadico a eliminar
	 * @return El número de tuplas eliminadas
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Esporadico, llenos con su información básica
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOEsporadico llenos con su información básica
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
	* Adiciona entradas al log de la aplicación
	* @param empresa - empresa del Seguro
	* @param monto - monto del Seguro
	* @param inicioSeguro - inicioSeguro del Seguro
	* @param finSeguro - finSeguro del Seguro
	* @return El objeto Seguro adicionado. null si ocurre alguna Excepción
	*/
	public Seguro adicionarSeguro ( String empresa, int monto, Timestamp inicioSeguro, Timestamp finSeguro)
	{
	log.info ("Adicionando nuevo(a) Seguro ");
	Seguro seguro = pa.adicionarSeguro(empresa, monto, inicioSeguro, finSeguro);	
	log.info ("Adicionando Seguro: " + seguro);
	return seguro;
	}

	/**
	 * Elimina un Seguro por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del Seguro a eliminar
	 * @return El número de tuplas eliminadas
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Seguro, llenos con su información básica
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOSeguro llenos con su información básica
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
	* Adiciona entradas al log de la aplicación
	* @param direccion - direccion del Cuarto
	* @param cupos - cupos del Cuarto
	* @param idOperador - idOperador del Cuarto
	* @param bañoPrivado - bañoPrivado del Cuarto
	* @param cuartoPrivado - cuartoPrivado del Cuarto
	* @param esquema - esquema del Cuarto
	* @param mensaje - mensaje del Cuarto
	* @return El objeto Cuarto adicionado. null si ocurre alguna Excepción
	*/
	public Cuarto adicionarCuarto (String direccion, int cupos, long idOperador, boolean bañoPrivado, boolean cuartoPrivado, String esquema, String mensaje)
	{
	log.info ("Adicionando nuevo(a) Cuarto ");
	Cuarto cuarto = pa.adicionarCuarto(direccion, cupos, idOperador, bañoPrivado, cuartoPrivado, esquema, mensaje);
	log.info ("Adicionando Cuarto: " + cuarto);
	return cuarto;
	}

	/**
	 * Elimina un Cuarto por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del Cuarto a eliminar
	 * @return El número de tuplas eliminadas
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Cuarto, llenos con su información básica
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOCuarto llenos con su información básica
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
	* Adiciona entradas al log de la aplicación
	* @param nombre - nombre del Operador
	* @param email - email del Operador
	* @param numero - numero del Operador
	* @return El objeto Operador adicionado. null si ocurre alguna Excepción
	*/
	public Operador adicionarOperador (nombre, email, numero)
	{
	log.info ("Adicionando nuevo(a) Operador ");
	Operador operador = pa.adicionarOperador (nombre);	
	log.info ("Adicionando Operador: " + operador);
	return operador;
	}

	/**
	 * Elimina un Operador por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del Operador a eliminar
	 * @return El número de tuplas eliminadas
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Operador, llenos con su información básica
	 */
	public List<Operador> darOperadors ()
	{
		log.info ("Consultando Operador");
	    List<Operador> operador = pa.darOperadors();
	    log.info ("Consultando Operador: " + operador.size() + " existentes");
	    return operador;
	}

	/**
	 * Encuentra todos los Operador en Alohandes y los devuelve como una lista de VOOperador
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOOperador llenos con su información básica
	 */
	public List<VOOperador> darVOOperador ()
	{
		log.info ("Generando los VO de Operador");
	    List<VOOperador> voOperador = new LinkedList<VOOperador> ();
	    for (Operador tb : pa.darOperadors ())
	    {
	    	voOperador.add (tb);
	    }
	    log.info ("Generando los VO de Operador: " + voOperador.size() + " existentes");
	    return voOperador;
	}

	/* ****************************************************************
	* 			Métodos para manejar los(as) HOTELERIA
	*****************************************************************/
	/**
	* Adiciona de manera persistente un Hoteleria
	* Adiciona entradas al log de la aplicación
	* @param nombre - nombre del Hoteleria
	* @param email - email del Hoteleria
	* @param numero - numero del Hoteleria
	* @param tipoHoteleria - tipoHoteleria del Hoteleria
	* @param horaApertura - horaApertura del Hoteleria
	* @param horaCierre - horaCierre del Hoteleria
	* @return El objeto Hoteleria adicionado. null si ocurre alguna Excepción
	*/
	public Hoteleria adicionarHoteleria (nombre, email, numero, tipoHoteleria, horaApertura, horaCierre)
	{
	log.info ("Adicionando nuevo(a) Hoteleria ");
	Hoteleria hoteleria = pa.adicionarHoteleria (nombre);	
	log.info ("Adicionando Hoteleria: " + hoteleria);
	return hoteleria;
	}

	/**
	 * Elimina un Hoteleria por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del Hoteleria a eliminar
	 * @return El número de tuplas eliminadas
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Hoteleria, llenos con su información básica
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOHoteleria llenos con su información básica
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
	* Adiciona entradas al log de la aplicación
	* @param nombre - nombre del Persona_Natural
	* @param email - email del Persona_Natural
	* @param numero - numero del Persona_Natural
	* @param documento - documento del Persona_Natural
	* @param tipoPersona - tipoPersona del Persona_Natural
	* @return El objeto Persona_Natural adicionado. null si ocurre alguna Excepción
	*/
	public Persona_Natural adicionarPersona_Natural (nombre, email, numero, documento, tipoPersona)
	{
	log.info ("Adicionando nuevo(a) Persona_Natural ");
	Persona_Natural persona_Natural = pa.adicionarPersona_Natural (nombre);	
	log.info ("Adicionando Persona_Natural: " + persona_Natural);
	return persona_Natural;
	}

	/**
	 * Elimina un Persona_Natural por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del Persona_Natural a eliminar
	 * @return El número de tuplas eliminadas
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Persona_Natural, llenos con su información básica
	 */
	public List<Persona_Natural> darPersona_Naturals ()
	{
		log.info ("Consultando Persona_Natural");
	    List<Persona_Natural> persona_Natural = pa.darPersona_Naturals();
	    log.info ("Consultando Persona_Natural: " + persona_Natural.size() + " existentes");
	    return persona_Natural;
	}

	/**
	 * Encuentra todos los Persona_Natural en Alohandes y los devuelve como una lista de VOPersona_Natural
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOPersona_Natural llenos con su información básica
	 */
	public List<VOPersona_Natural> darVOPersona_Natural ()
	{
		log.info ("Generando los VO de Persona_Natural");
	    List<VOPersona_Natural> voPersona_Natural = new LinkedList<VOPersona_Natural> ();
	    for (Persona_Natural tb : pa.darPersona_Naturals ())
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
	* Adiciona entradas al log de la aplicación
	* @param cantidad - cantidad del Ganancias
	* @param mes - mes del Ganancias
	* @param año - año del Ganancias
	* @param idOperador - idOperador del Ganancias
	* @return El objeto Ganancias adicionado. null si ocurre alguna Excepción
	*/
	public Ganancias adicionarGanancias (cantidad, mes, año, idOperador)
	{
	log.info ("Adicionando nuevo(a) Ganancias ");
	Ganancias ganancias = pa.adicionarGanancias (nombre);	
	log.info ("Adicionando Ganancias: " + ganancias);
	return ganancias;
	}

	/**
	 * Elimina un Ganancias por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del Ganancias a eliminar
	 * @return El número de tuplas eliminadas
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Ganancias, llenos con su información básica
	 */
	public List<Ganancias> darGananciass ()
	{
		log.info ("Consultando Ganancias");
	    List<Ganancias> ganancias = pa.darGananciass();
	    log.info ("Consultando Ganancias: " + ganancias.size() + " existentes");
	    return ganancias;
	}

	/**
	 * Encuentra todos los Ganancias en Alohandes y los devuelve como una lista de VOGanancias
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOGanancias llenos con su información básica
	 */
	public List<VOGanancias> darVOGanancias ()
	{
		log.info ("Generando los VO de Ganancias");
	    List<VOGanancias> voGanancias = new LinkedList<VOGanancias> ();
	    for (Ganancias tb : pa.darGananciass ())
	    {
	    	voGanancias.add (tb);
	    }
	    log.info ("Generando los VO de Ganancias: " + voGanancias.size() + " existentes");
	    return voGanancias;
	}

	/* ****************************************************************
	* 			Métodos para manejar los(as) CLIENTE
	*****************************************************************/
	/**
	* Adiciona de manera persistente un Cliente
	* Adiciona entradas al log de la aplicación
	* @param nombre - nombre del Cliente
	* @param email - email del Cliente
	* @param numero - numero del Cliente
	* @param documento - documento del Cliente
	* @param tipoCliente - tipoCliente del Cliente
	* @return El objeto Cliente adicionado. null si ocurre alguna Excepción
	*/
	public Cliente adicionarCliente (nombre, email, numero, documento, tipoCliente)
	{
	log.info ("Adicionando nuevo(a) Cliente ");
	Cliente cliente = pa.adicionarCliente (nombre);	
	log.info ("Adicionando Cliente: " + cliente);
	return cliente;
	}

	/**
	 * Elimina un Cliente por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del Cliente a eliminar
	 * @return El número de tuplas eliminadas
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Cliente, llenos con su información básica
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOCliente llenos con su información básica
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

	/* ****************************************************************
	* 			Métodos para manejar los(as) RESERVA
	*****************************************************************/
	/**
	* Adiciona de manera persistente un Reserva
	* Adiciona entradas al log de la aplicación
	* @param inicio - inicio del Reserva
	* @param duracion - duracion del Reserva
	* @param periodoArrendamiento - periodoArrendamiento del Reserva
	* @param idOferta - idOferta del Reserva
	* @param idUsuario - idUsuario del Reserva
	* @return El objeto Reserva adicionado. null si ocurre alguna Excepción
	*/
	public Reserva adicionarReserva (inicio, duracion, periodoArrendamiento, idOferta, idUsuario)
	{
	log.info ("Adicionando nuevo(a) Reserva ");
	Reserva reserva = pa.adicionarReserva (nombre);	
	log.info ("Adicionando Reserva: " + reserva);
	return reserva;
	}

	/**
	 * Elimina un Reserva por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del Reserva a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarReservaPorId (long idReserva)
	{
		log.info ("Eliminando Reserva por id: " + idReserva);
	    long resp = pa.eliminarReservaPorId(idReserva);
	    log.info ("Eliminando Reserva por id: " + resp + " tuplas eliminadas");
	    return resp;
	}

	/**
	 * Encuentra todos los Reserva en Alohandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Reserva, llenos con su información básica
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOReserva llenos con su información básica
	 */
	public List<VOReserva> darVOReserva ()
	{
		log.info ("Generando los VO de Reserva");
	    List<VOReserva> voReserva = new LinkedList<VOReserva> ();
	    for (Reserva tb : pa.darReservas ())
	    {
	    	voReserva.add (tb);
	    }
	    log.info ("Generando los VO de Reserva: " + voReserva.size() + " existentes");
	    return voReserva;
	}

	/* ****************************************************************
	* 			Métodos para manejar los(as) OFERTA
	*****************************************************************/
	/**
	* Adiciona de manera persistente un Oferta
	* Adiciona entradas al log de la aplicación
	* @param precio - precio del Oferta
	* @param periodo - periodo del Oferta
	* @param idVivienda - idVivienda del Oferta
	* @return El objeto Oferta adicionado. null si ocurre alguna Excepción
	*/
	public Oferta adicionarOferta (precio, periodo, idVivienda)
	{
	log.info ("Adicionando nuevo(a) Oferta ");
	Oferta oferta = pa.adicionarOferta (nombre);	
	log.info ("Adicionando Oferta: " + oferta);
	return oferta;
	}

	/**
	 * Elimina un Oferta por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del Oferta a eliminar
	 * @return El número de tuplas eliminadas
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Oferta, llenos con su información básica
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOOferta llenos con su información básica
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

	/* ****************************************************************
	* 			Métodos para manejar los(as) SERVICIO
	*****************************************************************/
	/**
	* Adiciona de manera persistente un Servicio
	* Adiciona entradas al log de la aplicación
	* @param nombre - nombre del Servicio
	* @param costo - costo del Servicio
	* @return El objeto Servicio adicionado. null si ocurre alguna Excepción
	*/
	public Servicio adicionarServicio (nombre, costo)
	{
	log.info ("Adicionando nuevo(a) Servicio ");
	Servicio servicio = pa.adicionarServicio (nombre);	
	log.info ("Adicionando Servicio: " + servicio);
	return servicio;
	}

	/**
	 * Elimina un Servicio por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del Servicio a eliminar
	 * @return El número de tuplas eliminadas
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Servicio, llenos con su información básica
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
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOServicio llenos con su información básica
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
	* Adiciona entradas al log de la aplicación
	* @param idOferta - idOferta del Incluye
	* @param idServicio - idServicio del Incluye
	* @param incluido - incluido del Incluye
	* @return El objeto Incluye adicionado. null si ocurre alguna Excepción
	*/
	public Incluye adicionarIncluye (idOferta, idServicio, incluido)
	{
	log.info ("Adicionando nuevo(a) Incluye ");
	Incluye incluye = pa.adicionarIncluye (nombre);	
	log.info ("Adicionando Incluye: " + incluye);
	return incluye;
	}

	/**
	 * Elimina un Incluye por su id
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del Incluye a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarIncluyePorId (long idIncluye)
	{
		log.info ("Eliminando Incluye por id: " + idIncluye);
	    long resp = pa.eliminarIncluyePorId(idIncluye);
	    log.info ("Eliminando Incluye por id: " + resp + " tuplas eliminadas");
	    return resp;
	}

	/**
	 * Encuentra todos los Incluye en Alohandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Incluye, llenos con su información básica
	 */
	public List<Incluye> darIncluyes ()
	{
		log.info ("Consultando Incluye");
	    List<Incluye> incluye = pa.darIncluyes();
	    log.info ("Consultando Incluye: " + incluye.size() + " existentes");
	    return incluye;
	}

	/**
	 * Encuentra todos los Incluye en Alohandes y los devuelve como una lista de VOIncluye
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOIncluye llenos con su información básica
	 */
	public List<VOIncluye> darVOIncluye ()
	{
		log.info ("Generando los VO de Incluye");
	    List<VOIncluye> voIncluye = new LinkedList<VOIncluye> ();
	    for (Incluye tb : pa.darIncluyes ())
	    {
	    	voIncluye.add (tb);
	    }
	    log.info ("Generando los VO de Incluye: " + voIncluye.size() + " existentes");
	    return voIncluye;
	}

	
	

	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Alohandes
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarAlohandes ()
	{
        log.info ("Limpiando la BD de Alohandes");
        long [] borrrados = pa.limpiarAlohandes();	
        log.info ("Limpiando la BD de Alohandes: Listo!");
        return borrrados;
	}
}
