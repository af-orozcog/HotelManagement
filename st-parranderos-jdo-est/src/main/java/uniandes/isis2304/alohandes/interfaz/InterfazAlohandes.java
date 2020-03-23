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
		System.out.println("(5) RF6.Retirar una reserva");
		
		System.out.println("---------------------------------------------------");
		System.out.println("(6) RFC1.MOSTRAR EL DINERO RECIBIDO POR CADA PROVEEDOR DE ALOJAMIENTO DURANTE EL AÑO ACTUAL Y EL AÑO CORRIDO");
		System.out.println("(7) RFC2.MOSTRAR LAS 20 OFERTAS MÁS POPULARES");
		System.out.println("(8) RFC3.MOSTRAR EL ÍNDICE DE OCUPACIÓN DE CADA UNA DE LAS OFERTAS DE ALOJAMIENTO REGISTRADAS");
		System.out.println("(9) RFC4.MOSTRAR LOS ALOJAMIENTOS DISPONIBLES EN UN RANGO DE FECHAS, QUE CUMPLEN CON UN CONJUNTO DE REQUERIMIENTOS DE DOTACIÓN O SERVICIOS. POR EJEMPLO, COCINETA, TV CABLE, INTERNET, SALA.");
		System.out.println("(10) RFC5.MOSTRAR EL USO DE ALOHANDES PARA CADA TIPO DE USUARIO DE LA COMUNIDAD");
		System.out.println("(11) RFC5.MOSTRAR EL USO DE ALOHANDES PARA UN USUARIO DADO (NÚMERO DE NOCHES O MESES CONTRATADOS, CARACTERÍSTICAS DEL ALOJAMIENTO UTILIZADO, DINERO PAGADO.");
		
		System.out.println("---------------------------------------------------");
		System.out.println("(12) Salir");
	}
	
	public void printMessage(String mensaje) {
		System.out.println(mensaje);
	}
}
