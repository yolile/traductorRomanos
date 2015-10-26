/**
 * 
 */
package traductorRomanos;

import java.util.Scanner;

/**
 * Clase de ejemplo de uso del {@link TraductorARomano}
 * 
 * @author Yohanna
 *
 */
public class TraductorRomanosMain {
	private static Scanner teclado;

	public static void main(String[] ar) {

		TraductorARomano traductor = new TraductorARomano();
		teclado = new Scanner(System.in);
		System.out.print("Ingrese el número:");
		traductor.setNumeroEntero(teclado.next());
		System.out.println("El numero en romano es: " + traductor.traducirARomano());

	}

}
