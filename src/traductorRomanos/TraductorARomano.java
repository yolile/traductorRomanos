
package traductorRomanos;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Traductor de Numeros Decimales a romanos segun BNF de Victor Ughelli:
 * 
 * numero-> 1SDE | (2..9)SD SD-> 0TD | NTD 
 * NTD-> (1..9)TD | vacio 
 * SDE-> OTDE | NTD 
 * TD-> 0 | NUM 
 * NUM-> 1|...|9| vacio 
 * TDE-> 0CD | NUM 
 * CD-> 0 | vacio
 * 
 * 
 *
 * @author Victor Ughelli
 * @author Yohanna Lisnichuk
 * 
 *
 */
public class TraductorARomano {

	private static final String ENTRADA_INVALIDA = "Entrada Invalida";
	private String numeroEntero = "";
	private String numeroRomano = "";
	private Integer cantidadDigitos = new Integer(0);
	List<Integer> numerosEnteros = new ArrayList<Integer>();

	public String traducirARomano() {

		numero();
		return numeroRomano;

	}

	private void numero() {

		String primerDigito;
		// caso en el que sea nulo
		try {
			primerDigito = getNumeroEntero().substring(0, 1);
		} catch (IndexOutOfBoundsException e) {
			numeroRomano = ENTRADA_INVALIDA;
			return;
		}
		// caso en el que introduza 1 como primer digito
		if (primerDigito.equals("1")) {

			cantidadDigitos++;
			numerosEnteros.add(1);
			SDE();

			// caso en el que introduzca otro numero como primer digito
		} else {
			if (primerDigito.equals("2") || primerDigito.equals("3") || primerDigito.equals("4")
					|| primerDigito.equals("5") || primerDigito.equals("6") || primerDigito.equals("7")
					|| primerDigito.equals("8") || primerDigito.equals("9")) {

				cantidadDigitos++;
				numerosEnteros.add(Integer.valueOf(primerDigito));
				SD();

				// caso en el que introduzca un caracter no numerico o 0 como
				// primer digito
			} else {
				numeroRomano = ENTRADA_INVALIDA;
				return;
			}

		}
	}

	private void SDE() {

		String segundoDigito;
		try {
			segundoDigito = getNumeroEntero().substring(1, 2);
		//si el segundo digito es vacio lo maneja NTD
		} catch (IndexOutOfBoundsException e) {
			NTD();
			return;
		}
		//si el segundo digito es 0 lo maneja TDE
		if (segundoDigito.equals("0")) {
			cantidadDigitos++;
			numerosEnteros.add(0);
			TDE();

		} else {
			//si el segundo digito no es vacio ni 0 lo maneja NTD
			NTD();

		}
	}

	private void SD() {

		String segundoDigito;
		try {
			segundoDigito = getNumeroEntero().substring(1, 2);
		} catch (IndexOutOfBoundsException e) {
			NTD();
			return;
		}
		if (segundoDigito.equals("0")) {
			cantidadDigitos++;
			numerosEnteros.add(0);
			TD();

		} else {
			NTD();

		}
	}

	private void NTD() {
		String segundoDigito;
		try {
			segundoDigito = getNumeroEntero().substring(1, 2);
			// si no tiene segundo digito se pasa a traducir
		} catch (IndexOutOfBoundsException e) {
			traducir();
			return;
		}
		//si es un numero no 0 lo maneja TD
		if (segundoDigito.equals("1") || segundoDigito.equals("2") || segundoDigito.equals("3")
				|| segundoDigito.equals("4") || segundoDigito.equals("5") || segundoDigito.equals("6")
				|| segundoDigito.equals("7") || segundoDigito.equals("8") || segundoDigito.equals("9")) {

			cantidadDigitos++;
			numerosEnteros.add(Integer.valueOf(segundoDigito));
			TD(); 
			return;
			//si no es ni numeros ni vacio, es una entrada invalida
		} else {
			numeroRomano = ENTRADA_INVALIDA;
		}
	}

	private void TD() {

		String tercerDigito;
		//si no tiene tercer digito lo maneja NUM
		try {
			tercerDigito = getNumeroEntero().substring(2, 3);
			// si no tiene segundo digito
		} catch (IndexOutOfBoundsException e) {
			NUM();
			return;
		}
		//si el tercer digito es 0 lo consumimos y pedimos el siguiente numero,
		//si existe, es una entrada invalida, si no, pasamos a traducir
		if (tercerDigito.equals("0")) {
			cantidadDigitos++;
			numerosEnteros.add(0);
			try {
				tercerDigito = getNumeroEntero().substring(3, 4);
			} catch (IndexOutOfBoundsException e) {
				traducir();
				return;
			}
			numeroRomano = ENTRADA_INVALIDA;
			return;
		} else {
			NUM();
		}
	}

	private void NUM() {

		String digito;
		try {
			digito = getNumeroEntero().substring(2, 3);
			// si no tiene tercer digito pasamos a traducir
		} catch (IndexOutOfBoundsException e) {
			traducir();
			return;
		}

		//si es un numero, lo consumimos y preguntamos por el siguiente, si lo tiene,
		//es una entrada invalida, si no, lo traducimos
		if (digito.equals("1") || digito.equals("2") || digito.equals("3") || digito.equals("4") || digito.equals("5")
				|| digito.equals("6") || digito.equals("7") || digito.equals("8") || digito.equals("9")) {
			cantidadDigitos++;
			numerosEnteros.add(Integer.valueOf(digito));
			try {
				digito = getNumeroEntero().substring(3, 4);
			} catch (IndexOutOfBoundsException e) {
				traducir();
				return;
			}
			numeroRomano = ENTRADA_INVALIDA;
			return;
			//si no es un numero ni vacio, es una entrada invalida
		} else {

			numeroRomano = ENTRADA_INVALIDA;
		}

	}

	private void TDE() {
		String tercerDigito;
		try {
			tercerDigito = getNumeroEntero().substring(2, 3);
		} catch (IndexOutOfBoundsException e) {
			NUM();
			return;
		}
		if (tercerDigito.equals("0")) {
			cantidadDigitos++;
			numerosEnteros.add(0);
			CD();
		} else {
			NUM();
		}

	}

	private void CD() {
		String cuartoDigito;
		try {
			cuartoDigito = getNumeroEntero().substring(3, 4);
		} catch (IndexOutOfBoundsException e) {
			traducir();
			return;
		}

		if (cuartoDigito.equals("0")) {
			cantidadDigitos++;
			numerosEnteros.add(0);
			traducir();
			return;
		} else {
			numeroRomano = ENTRADA_INVALIDA;
			return;
		}
	}

	private void traducir() {
		String v1 = "";
		String v5 = "";
		String vPos = "";
		Integer digito;
		int contador = -1;

		while (!cantidadDigitos.equals(0)) {

			if (cantidadDigitos.equals(4)) {
				numeroRomano = "M";
				return;
			}
			if (cantidadDigitos.equals(3)) {
				v1 = "C";
				v5 = "D";
				vPos = "M";
			}
			if (cantidadDigitos.equals(2)) {
				v1 = "X";
				v5 = "L";
				vPos = "C";
			}
			if (cantidadDigitos.equals(1)) {
				v1 = "I";
				v5 = "V";
				vPos = "X";
			}
			contador++;
			digito = numerosEnteros.get(contador);
			if (digito.compareTo(4) < 0) {
				for (int i = 0; i < digito; i++) {
					numeroRomano = numeroRomano + v1;
				}
			}
			if (digito.equals(4)) {
				numeroRomano = numeroRomano + v1 + v5;
			}
			if (digito.compareTo(4) > 0 && digito.compareTo(9) < 0) {
				numeroRomano = numeroRomano + v5;
				for (int i = digito - 5; i > 0; i--) {
					numeroRomano = numeroRomano + v1;
				}
			}
			if (digito.equals(9)) {
				numeroRomano = numeroRomano + v1 + vPos;
			}

			cantidadDigitos--;

		}
	}

	public String getNumeroEntero() {
		return numeroEntero;
	}

	public void setNumeroEntero(String numero) {
		this.numeroEntero = numero;
	}

}
