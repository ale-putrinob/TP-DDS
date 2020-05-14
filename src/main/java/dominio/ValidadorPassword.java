package dominio;

import java.util.ArrayList;
import java.util.List;


public class ValidadorPassword {
	public static List<Validacion> validaciones = new ArrayList<>();
	
	public static void agregarValidaciones(Validacion validacion) {
		validaciones.add(validacion);
	}
	
	private static boolean seCumplenTodasLasValidaciones(String password) {
		return validaciones.stream().allMatch(validacion -> validacion.cumpleCondicion(password));
	}
	
	public static void validarPassword(String password) {
		if(!seCumplenTodasLasValidaciones(password)) {
			throw new PasswordInseguraException("La contraseņa ingresada es insegura");
		}
	}	

}
