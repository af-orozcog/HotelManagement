package uniandes.isis2304.alohandes.interfaz;

import java.util.Scanner;

import uniandes.isis2304.alohandes.negocio.Alohandes;

public class Controller {

	// Componente vista (consola)
	private InterfazAlohandes interfaz;
	
	// Componente modelo (logica de la aplicacion)
	private Alohandes mundo;

	/**
	 * Metodo constructor
	 */
	public Controller()
	{
		interfaz = new InterfazAlohandes();
		mundo = new Alohandes();
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

	public void req1(Scanner sc) {
		int op = sc.nextInt();
		switch (op) {
		case 0:
			interfaz.printMessage("Escriba el nombre");
			String nombre = sc.next();
			interfaz.printMessage("Escriba el email");
			String email = sc.next();
			interfaz.printMessage("Escriba el numero");
			String numero = sc.next();
			interfaz.printMessage("Escriba el numero de documento");
			String documento = sc.next();
			interfaz.printMessage("Escriba el tipo de usuario IGUAL que alguna de las opciones:\n PROFESOR, EMPLEADO, EGRESADO, ESTUDIANTE, PADRE, NO_RELACIONADO, VECINO");
			String tipoPersona = sc.next();
			mundo.adicionarPersona_Natural(nombre, email, numero, documento, tipoPersona);
			
			
			break;

		case 1:
			break;
		}
	}
	
	
	public static void main(String[] args) {
		Controller controler = new Controller();
		controler.run();
	}
}