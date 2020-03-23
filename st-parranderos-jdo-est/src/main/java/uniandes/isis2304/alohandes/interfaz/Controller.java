package uniandes.isis2304.alohandes.interfaz;

import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.alohandes.negocio.Alohandes;
import uniandes.isis2304.alohandes.negocio.Cliente;
import uniandes.isis2304.alohandes.negocio.Cuarto;
import uniandes.isis2304.alohandes.negocio.Oferta;
import uniandes.isis2304.alohandes.negocio.Operador;
import uniandes.isis2304.alohandes.negocio.Seguro;
import uniandes.isis2304.alohandes.negocio.Servicio;
import uniandes.isis2304.alohandes.negocio.Vivienda;

public class Controller {

	/** Logger para escribir la traza de la ejecución. */

	private static Logger log = Logger.getLogger(InterfazAlohandes.class.getName());

	// Componente vista (consola)
	private InterfazAlohandes interfaz;

	// Componente modelo (logica de la aplicacion)
	private Alohandes mundo;

	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD.json"; 

	/** Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar. */
	private JsonObject tableConfig;

	/**
	 * Metodo constructor
	 */
	public Controller()
	{
		interfaz = new InterfazAlohandes();

		tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
		mundo = new Alohandes(tableConfig);
	}

	/**
	 * Metodo encargado de ejecutar los  requerimientos segun la opcion indicada por el usuario
	 */
	public void run(){

		long startTime;
		long endTime;
		long duration;

		Scanner sc = new Scanner(System.in);
		boolean fin = false;
		Controller controller = new Controller();

		while(!fin){
			interfaz.printMenu();
			int option = sc.nextInt();

			switch(option){

			case 0:
				interfaz.printMessage("Seleccione el tipo de operador");
				interfaz.printMessage("(0) PersonaNatural");
				interfaz.printMessage("(1) Hoteleria");
				interfaz.printMessage("(2) ViviendaUniversitaria");

				req1(sc);
				break;
			case 1:
				interfaz.printMessage("Escriba el tipo de operador al que ser va a hacer la propuesta IGUAL que alguna de las opciones:\n PERSONA_NATURAL, HOTELERIA, VIVIENDA_UNIVERSITARIA");

				req2(sc);
				break;
			case 2:
				interfaz.printMessage("Es un operador o un cliente? (1 o 2)");
				int t2 = sc.nextInt();
				if(t2 == 1)
					req1(sc);
				else if(t2 == 2)
					req3(sc);
				break;
			case 3:
				req4(sc);
				break;
			case 4:
				req5(sc);
				
				break;
			case 5:
				break;
			case 12:
				fin = true;
				sc.close();
				break;
			}
		}
	}

	private void req5(Scanner sc) {
		interfaz.printMessage("Ingrese el nombre del cliente a eliminar reserva");
		Cliente cliente = mundo.darClientePorNombre(sc.next());
	}

	private void req4(Scanner sc) {
		interfaz.printMessage("Ingresar");

		interfaz.printMessage("Ingrese el año de inicio de la reserva");
		int añoIn = sc.nextInt();
		interfaz.printMessage("Ingrese el mes de inicio de la reserva");
		int mesIn = sc.nextInt();
		interfaz.printMessage("Ingrese el dia de inicio de la reserva");
		int diaIn = sc.nextInt();

		interfaz.printMessage("Ingrese el año de fin de la reserva");
		int añoFin = sc.nextInt();
		interfaz.printMessage("Ingrese el mes de fin de la reserva");
		int mesFin = sc.nextInt();
		interfaz.printMessage("Ingrese el dia de fin de la reserva");
		int diaFin = sc.nextInt();

		Timestamp inicio = new Timestamp(añoIn, mesIn, diaIn, 0, 0, 0, 0);
		Timestamp fin = new Timestamp(añoFin, mesFin, diaFin, 0, 0, 0, 0);

		interfaz.printMessage("Ingrese el nombre del usuario que va a realizar la reserva");
		Cliente usuario = mundo.darClientePorNombre(sc.next());
		
		interfaz.printMessage("Ya sabe con que oferta quiere realizar la reserva? (Y/N)");
		boolean sabe = sc.next().equalsIgnoreCase("Y");
		
		if(sabe) {
		interfaz.printMessage("Ingrese el id de la oferta que se quiere reservar");
		long idOferta = sc.nextLong();

		String periodoArrendamiento = mundo.darOfertaPorId(idOferta).getPeriodo();
		interfaz.printMessage("Dar duración de la reserva en " + periodoArrendamiento +" (Escribir número entero)");
		int duracion = sc.nextInt();

		mundo.adicionarReserva(inicio, fin, duracion, periodoArrendamiento, idOferta, usuario.getId());
		}
		else {
			interfaz.printMessage("Algún servicio en específico? (Y/N)");
			boolean servicios = sc.next().equalsIgnoreCase("Y");
			
			ArrayList<String> lista = new ArrayList<String>();
			while(servicios) {
				interfaz.printMessage("Escriba el servicio que desea");
				lista.add(sc.next());
				interfaz.printMessage("Desea más servicios? (Y/N");
				servicios = sc.next().equalsIgnoreCase("Y");
			}
			
			List<Oferta> ofertas = mundo.darOfertasConServicios(lista);
			interfaz.printMessage("Las siguientes ofertas están disponibles: ");
			for (Oferta oferta : ofertas) {
				interfaz.printMessage(oferta.toString() + "ID OFERTA: " + oferta.getId());
			}
			
		}
	}

	@SuppressWarnings("deprecation")
	public void req1(Scanner sc) {
		int op = sc.nextInt();

		interfaz.printMessage("Escriba el nombre");
		String nombre = sc.next();
		interfaz.printMessage("Escriba el email");
		String email = sc.next();
		interfaz.printMessage("Escriba el numero");
		String numero = sc.next();

		switch (op) {
		case 0:
			interfaz.printMessage("Escriba el numero de documento");
			String documento = sc.next();
			interfaz.printMessage("Escriba el tipo de usuario IGUAL que alguna de las opciones:\n PROFESOR, EMPLEADO, EGRESADO, ESTUDIANTE, PADRE, NO_RELACIONADO, VECINO");
			String tipoPersona = sc.next();
			mundo.adicionarPersona_Natural(nombre, email, numero, documento, tipoPersona);
			break;
		case 1:
			interfaz.printMessage("Escriba el tipo de hoteleria IGUAL  que alguna de las opciones:\n HOSTAL, HOTEL");
			String tipoHoteleria = sc.next();

			int horaIn = 0, minIn = 0, horaFin = 0, minFin = 0;
			if(tipoHoteleria.equalsIgnoreCase("HOSTAL")) {
				interfaz.printMessage("Esriba hora de inicio (0-24)");
				horaIn = sc.nextInt();
				interfaz.printMessage("Escriba minuto de inicio");
				minIn = sc.nextInt();
				interfaz.printMessage("Esriba hora de cierre (0-24)");
				horaFin = sc.nextInt();
				interfaz.printMessage("Escriba minuto de cierre");
				minFin = sc.nextInt();
			}


			Timestamp horaApertura = new Timestamp(2000, 1, 1, horaIn, minIn, 0, 0);
			Timestamp horaCierre = new Timestamp(2000, 1, 1, horaFin, minFin, 0, 0);
			mundo.adicionarHoteleria(nombre, email, numero, tipoHoteleria, horaApertura, horaCierre);
			break;
		case 2:
			mundo.adicionarViviendaUniversitaria(nombre, email, numero);
			break;
		}
	}

	public void req2(Scanner sc) {
		String tipoOperador = sc.next();
		interfaz.printMessage("Escriba el nombre del operador");
		String nombre = sc.next();
		interfaz.printMessage("La vivienda de la que piensa crear una propuesta ya está registrada? (Responer Y/N)");
		String ans = sc.next();

		Operador op = mundo.darOperadorPorNombre(nombre, tipoOperador);

		long idVivienda;
		boolean es = false;
		if(ans.equalsIgnoreCase("N") ) {
			Vivienda vi = crearVivienda(sc, op, es);
			idVivienda = vi.getId();
		}
		else {
			interfaz.printMessage("Escriba el id de la vivienda ya creada");
			idVivienda = sc.nextLong();
			interfaz.printMessage("La vivienda es esporadica? (Y/N)");
			es = sc.next().equalsIgnoreCase("Y");
		}

		interfaz.printMessage("Ingrese el precio de la propuesta");
		long precio = sc.nextLong();
		interfaz.printMessage("Escriba el periodo de la propuesta (DIAS, MESES, SEMESTRES)");
		String periodo = sc.next();
		if(periodo.equalsIgnoreCase("DIAS") && !es)
			interfaz.printMessage("ERROR DE PERIDO, No se puede elegir DIAS si no es esporadico");
		else {
			interfaz.printMessage("Ingrese el año de inicio de la propuesta");
			int añoIn = sc.nextInt();
			interfaz.printMessage("Ingrese el mes de inicio de la propuesta");
			int mesIn = sc.nextInt();
			interfaz.printMessage("Ingrese el dia de inicio de la propuesta");
			int diaIn = sc.nextInt();

			interfaz.printMessage("Ingrese el año de fin de la propuesta");
			int añoFin = sc.nextInt();
			interfaz.printMessage("Ingrese el mes de fin de la propuesta");
			int mesFin = sc.nextInt();
			interfaz.printMessage("Ingrese el dia de fin de la propuesta");
			int diaFin = sc.nextInt();

			Timestamp fechaInicio = new Timestamp(añoIn, mesIn, diaIn, 0, 0, 0, 0);
			Timestamp fechaFin = new Timestamp(añoFin, mesFin, diaFin, 0, 0, 0, 0);

			Oferta oferta = mundo.adicionarOferta(precio, periodo, idVivienda, fechaInicio, fechaFin);

			interfaz.printMessage("Desea adicionar servicios? (Y/N)"); 
			boolean servicio = sc.next().equalsIgnoreCase("Y");
			while(servicio) {
				interfaz.printMessage("El servicio ya existe está registrado? (Y/N))");
				boolean reg = sc.next().equalsIgnoreCase("Y");

				long idServicio;
				if(reg) {
					interfaz.printMessage("Ingrese el id del servicio");
					idServicio = sc.nextLong();
				}
				else {
					idServicio = registrarServicio(sc).getId();
				}

				interfaz.printMessage("El precio del servicio ya está incluido en la oferta? (Y/N)");
				boolean incluido = sc.next().equalsIgnoreCase("Y");
				mundo.adicionarIncluye(oferta.getId(), idServicio, incluido);
				
				interfaz.printMessage("Desea seguir adicionando servicios? (Y/N)"); 
				servicio = sc.next().equalsIgnoreCase("Y");
			}
		}

	}

	private Servicio registrarServicio(Scanner sc) {
		interfaz.printMessage("Ingrese el nombre del servicio");
		String nombre = sc.next();
		interfaz.printMessage("Ingrese el costo del servicio");
		long costo = sc.nextLong();
		return mundo.adicionarServicio(nombre, costo);
	}

	private void req3(Scanner sc) {
		interfaz.printMessage("Escriba el nombre");
		String nombre = sc.next();
		interfaz.printMessage("Escriba el email");
		String email = sc.next();
		interfaz.printMessage("Escriba el numero");
		String numero = sc.next();
		interfaz.printMessage("Escriba el documento");
		String documento = sc.next();
		interfaz.printMessage("Escriba el tipo de cliente de alguna de las opciones\n PROFESOR, EMPLEADO, EGRESADO, ESTUDIANTE, PADRE, NO_RELACIONADO, VECINO ");
		String tipoCliente = sc.next();

		mundo.adicionarCliente(nombre, email, numero, documento, tipoCliente);
	}

	private Vivienda crearVivienda(Scanner sc, Operador operador, boolean es) {
		interfaz.printMessage("Indroduzca la dirección");
		String direccion = sc.next();
		interfaz.printMessage("Introduzca los cupos que tiene está vivienda");
		int cupos = sc.nextInt();

		switch (operador.getTipoOperador()) {
		case "HOTELERIA":
			interfaz.printMessage("Es un hotel u hostal? (Escriba HOTEL u HOSTAL");
			String hotTemp = sc.next();
			String tipoHabitacion = "COMPARTIDA";
			if(hotTemp.equalsIgnoreCase("HOTEL")) {
				interfaz.printMessage("Escriba el tipo de habitacion IGUAL que alguna de las opciones:\n ESTANDAR, SEMISUITE, SUITE");
				tipoHabitacion = sc.next();
			}
			interfaz.printMessage("Introduzca categoria");
			String categoria = sc.next();
			interfaz.printMessage("Introduzca capacidad (Numero entero)");
			int capacidad = sc.nextInt();
			interfaz.printMessage("Introduzca número de habitación (Numero entero)");
			int numero = sc.nextInt();

			return mundo.adicionarHabitacion(direccion, cupos, operador.getId(), tipoHabitacion, categoria, capacidad, numero);
		case "PERSONA_NATURAL":
			interfaz.printMessage("Es un cuarto o un apartamento? (1 o 2)");
			int perTemp = sc.nextInt();
			if(perTemp == 1) 
				registrarCuarto(operador, sc, direccion, cupos);
			else if(perTemp == 2) {
				interfaz.printMessage("Ingrese el area (en decimales y en metros cuadrados ej: 22.5)");
				double area = sc.nextDouble();
				interfaz.printMessage("El apartamento está amoblado? (Y/N)");
				boolean amoblado = sc.next().equalsIgnoreCase("Y");
				interfaz.printMessage("Ingrese el número de habitaciones (en número entero)");
				int numeroHabitaciones = sc.nextInt();

				interfaz.printMessage("Va a ser esporadico? (Y/N)");
				es = sc.next().equalsIgnoreCase("Y");
				if(es) {
					Seguro seg = registrarSeguro(sc);
					return mundo.adicionarEsporadico(direccion, cupos, operador.getId(), area, amoblado, numeroHabitaciones, 0, seg.getId());
				}
				else {
					return mundo.adicionarApartamento(direccion, cupos, operador.getId(), area, amoblado, numeroHabitaciones);
				}
			}
		case "VIVIENDA_UNIVERSITARIA":
			return registrarCuarto(operador, sc, direccion, cupos);
		}
		return null;

	}

	public Seguro registrarSeguro(Scanner sc) {
		interfaz.printMessage("Ingrese la empresa a la que quiere asegurar la vivienda");
		String empresa = sc.next();
		interfaz.printMessage("Ingrese el monto por el que se quiere asegurar la vivienda");
		int monto = sc.nextInt();

		interfaz.printMessage("Ingrese el año de inicio del seguro");
		int añoIn = sc.nextInt();
		interfaz.printMessage("Ingrese el mes de inicio del seguro");
		int mesIn = sc.nextInt();
		interfaz.printMessage("Ingrese el dia de inicio del seguro");
		int diaIn = sc.nextInt();

		interfaz.printMessage("Ingrese el año de fin del seguro");
		int añoFin = sc.nextInt();
		interfaz.printMessage("Ingrese el mes de fin del seguro");
		int mesFin = sc.nextInt();
		interfaz.printMessage("Ingrese el dia de fin del seguro");
		int diaFin = sc.nextInt();

		Timestamp inicioSeguro = new Timestamp(añoIn, mesIn, diaIn, 0, 0, 0, 0);
		Timestamp finSeguro = new Timestamp(añoFin, mesFin, diaFin, 0, 0, 0, 0);

		return mundo.adicionarSeguro(empresa, monto, inicioSeguro, finSeguro);
	}

	public Cuarto registrarCuarto(Operador operador, Scanner sc, String direccion, int cupos) {
		interfaz.printMessage("El cuarto tiene baño privado? (Y/N)");
		boolean bañoPrivado = sc.next().equalsIgnoreCase("Y");
		interfaz.printMessage("El cuarto es privado? (Y/N)");
		boolean cuartoPrivado = sc.next().equalsIgnoreCase("Y");		

		interfaz.printMessage("Ingrese el esquema del cuarto");
		String esquema = sc.next();
		interfaz.printMessage("Ingrese el menaje del cuarto");
		String menaje = sc.next();

		return mundo.adicionarCuarto(direccion, cupos, operador.getId(), bañoPrivado, cuartoPrivado, esquema, menaje);

	}

	/**
	 * Lee datos de configuraciÃ³n para la aplicaciÃ³, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuraciÃ³n deseada
	 * @param archConfig - Archivo Json que contiene la configuraciÃ³n
	 * @return Un objeto JSON con la configuraciÃ³n del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontrÃ³ un archivo de configuraciÃ³n vÃ¡lido: " + tipo);
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontrÃ³ un archivo de configuraciÃ³n vÃ¡lido");			
			JOptionPane.showMessageDialog(null, "No se encontrÃ³ un archivo de configuraciÃ³n de interfaz vÃ¡lido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	public static void main(String[] args) {
		Controller controler = new Controller();
		String log4jConfPath = "./path/to/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		controler.run();
	}
}