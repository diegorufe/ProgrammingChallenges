package com.Kaprekar;

import com.Kaprekar.constants.IConstantesKaprekar;
import com.Kaprekar.exceptions.NotNumberKaprecarException;
import com.Kaprekar.utils.UtilsKaprekar;

public class Main {

	public static void main(String[] args) {
		try {
			String numero = "1112";
			System.out.println(
					"Numero para llegar al número Kaprekar " + IConstantesKaprekar.NUMERO_KAPRECAR + " es: " + numero);
			int iteraciones = UtilsKaprekar.operaKaprekar(numero);
			System.out.println("Número de operaciones realizadas: " + iteraciones);
		} catch (NotNumberKaprecarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
