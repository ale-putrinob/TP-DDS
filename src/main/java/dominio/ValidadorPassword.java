package dominio;

import java.io.IOException;
import java.util.List;

public class ValidadorPassword {
	static List<String> caracteresConsecutivos;
	static List<String> caracteresRepetidos;

	public static void validarPassword(String password) throws IOException{
		validarSiEstaEntrePasswordsMasInseguras(password);
		validarLongitudPassword(password);
		validarCaracteresConsecutivos(password);
		validarCaracteresRepetidos(password);
	}

	private static void validarCaracteresRepetidos(String password) throws IOException {
		BuscadorEnArchivo.encontrarInclusion(password, "caracteresRepetidos.txt");
	}

	private static void validarCaracteresConsecutivos(String password) throws IOException {
		BuscadorEnArchivo.encontrarInclusion(password, "caracteresConsecutivos.txt");
	}

	private static void validarLongitudPassword(String password) {
		if(password.length() < 8) 
			throw new PasswordInseguraException("La contaseña ingresada es insegura");
	}

	private static void validarSiEstaEntrePasswordsMasInseguras(String password) throws IOException {
		BuscadorEnArchivo.encontrarIgualdad(password, "10k-most-common-passwords.txt");
	}

}
