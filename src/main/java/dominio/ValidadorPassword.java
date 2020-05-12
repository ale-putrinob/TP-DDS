package dominio;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorPassword {
	

	public static void validarPassword(String password) throws IOException{
		validarSiEstaEntrePasswordsMasInseguras(password);
		validarLongitudPassword(password);
		validarCaracteresConsecutivos(password);
		validarCaracteresRepetidos(password);
	}

	private static void validarCaracteresRepetidos(String password) throws IOException {
		Pattern pat = Pattern.compile(".*((\\w)\\2\\2).*"); 
		Matcher mat = pat.matcher(password);
		
		if(mat.matches())
			throw new PasswordInseguraException("La contraseņa ingresada es insegura");
	}

	private static void validarCaracteresConsecutivos(String password) throws IOException {
		BuscadorEnArchivo.encontrarInclusion(password, "caracteresConsecutivos.txt");
	}

	private static void validarLongitudPassword(String password) {
		if(password.length() < 8) 
			throw new PasswordInseguraException("La contaseņa ingresada es insegura");
	}

	private static void validarSiEstaEntrePasswordsMasInseguras(String password) throws IOException {
		BuscadorEnArchivo.encontrarIgualdad(password, "10k-most-common-passwords.txt");
	}
	

}
