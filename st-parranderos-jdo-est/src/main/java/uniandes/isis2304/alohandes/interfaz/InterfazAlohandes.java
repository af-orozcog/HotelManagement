package uniandes.isis2304.alohandes.interfaz;

public class InterfazAlohandes {

	public void printMenu() {
		System.out.println("---------ISIS 2304_01 - Sistemas transaccionales----------");
		System.out.println("---------------------Iteración 2----------------------");
		System.out.println("(0) RF1.Registrar operador");
		System.out.println("(1) RF2.Registrar propuesta");
		System.out.println("(2) RF3.Registrar usuario");
		System.out.println("(3) RF4.Registrar reserva");
		System.out.println("(4) RF5.Cancelar una reserva");
		System.out.println("(5) RF6.Retirar una propuesta");
		
		System.out.println("---------------------------------------------------");
		System.out.println("(6) RFC1.MOSTRAR EL DINERO RECIBIDO POR CADA PROVEEDOR DE ALOJAMIENTO DURANTE EL AÑO ACTUAL Y EL AÑO CORRIDO");
		System.out.println("(7) RFC2.MOSTRAR LAS 20 OFERTAS MÁS POPULARES");		
		System.out.println("---------------------------------------------------");
		System.out.println("(8) Salir");
	}
	
	public void printMessage(String mensaje) {
		System.out.println(mensaje);
	}
}
