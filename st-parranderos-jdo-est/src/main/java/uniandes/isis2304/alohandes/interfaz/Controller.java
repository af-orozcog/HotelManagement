package uniandes.isis2304.alohandes.interfaz;

import java.io.FileReader;
import java.sql.Timestamp;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.alohandes.negocio.Alohandes;

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
				interfaz.printMessage("");
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
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
		String log4jConfPath = "/path/to/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		controler.run();
	}
}