package com.Kaprekar.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.Kaprekar.constants.IConstantesKaprekar;
import com.Kaprekar.exceptions.NotNumberKaprecarException;

/**
 * 
 * @author diego
 *
 */
public class UtilsKaprekar {

	/**
	 * Método para completar una cadena por la izquierda
	 * 
	 * @param cadena
	 * @param compleStr
	 * @param longitud
	 * @return
	 */
	public static String padl(String cadena, String compleStr, int longitud) {
		StringBuilder builder = new StringBuilder();
		if (cadena != null) {
			int comple = longitud - cadena.length();
			if (comple > 0) {
				for (int i = 0; i < comple; i++) {
					builder.append(compleStr);
				}
			}
		}
		return builder.toString().concat(cadena);
	}

	/**
	 * Método para extraer los números para obtener el número Kaprekar
	 * 
	 * @param numeros
	 * @return
	 * @throws NotNumberKaprecarException
	 */
	private static List<Integer> extraerNumeros(String numeros) throws NotNumberKaprecarException {
		List<Integer> numerosResultado = new ArrayList<Integer>();
		if (numeros == null || numeros.trim().isEmpty() || numeros.trim().length() < 4 || numeros.trim().length() > 4) {
			throw new NotNumberKaprecarException();
		}
		String letra = null;
		Integer numero = null;
		Integer numeroAnterior = null;
		boolean todosNumerosIguales = false;
		for (int i = 0; i < numeros.length(); i++) {
			letra = numeros.substring(i, i + 1);
			if (letra.trim().isEmpty()) {
				throw new NotNumberKaprecarException();
			}
			try {
				numero = Integer.parseInt(letra);
			} catch (NumberFormatException nbe) {
				throw new NotNumberKaprecarException();
			}
			numerosResultado.add(numero);
			if (i > 0) {
				if (numero.equals(numeroAnterior)) {
					todosNumerosIguales = true;
				} else {
					todosNumerosIguales = false;
				}
			}
			numeroAnterior = numero;
		}
		if (todosNumerosIguales) {
			throw new NotNumberKaprecarException();
		}
		return numerosResultado;
	}

	/**
	 * Método para extraer los operandos de la resta de kKaprekar
	 * 
	 * @param numeros
	 * @return
	 */
	private static Integer[] extraerOperandosKaprekar(List<Integer> numeros) {

		List<Integer> minuendoList = new ArrayList<Integer>(numeros);
		List<Integer> substraendoList = new ArrayList<Integer>(numeros);
		// Ordenamos descendetemente
		Collections.sort(minuendoList, Collections.reverseOrder());
		// Ordenamos ascendentemente
		Collections.sort(substraendoList);
		Integer minuendo = 0;
		Integer substraendo = 0;
		StringBuilder builderOperando = new StringBuilder();
		minuendoList.forEach((v) -> {
			builderOperando.append(v.toString());
		});
		// Obtenemos el primer número
		minuendo = Integer.parseInt(builderOperando.toString());
		builderOperando.setLength(0);
		substraendoList.forEach((v) -> {
			builderOperando.append(v.toString());
		});
		// Obtenemos el segundo número
		substraendo = Integer.parseInt(builderOperando.toString());
		return new Integer[] { minuendo, substraendo };
	}

	/**
	 * Método para restar los números
	 * 
	 * @param numeros
	 * @return
	 */
	private static Integer restaOperadores(Integer[] numeros) {
		return numeros[0] - numeros[1];
	}

	/**
	 * Método para obtener el número Kaprekar
	 * 
	 * @param numeros
	 * @return el número de iteraciones hasta llegar
	 * @throws NotNumberKaprecarException
	 */
	public static int operaKaprekar(String numeros) throws NotNumberKaprecarException {
		int iteraciones = 0;
		Integer resultado = 0;
		while (!resultado.equals(IConstantesKaprekar.NUMERO_KAPRECAR)) {
			resultado = restaOperadores(extraerOperandosKaprekar(extraerNumeros(padl(numeros, "0", 4))));
			System.out.println("Iteración: " + iteraciones + ", resultado: " + resultado.toString());
			iteraciones++;
			numeros = String.valueOf(resultado);
		}
		return iteraciones;
	}
}
