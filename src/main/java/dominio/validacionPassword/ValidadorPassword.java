package dominio.validacionPassword;

import java.util.ArrayList;
import java.util.List;

import dominio.excepcion.PasswordInseguraException;

public class ValidadorPassword {
	public static List<ValidacionPassword> validaciones = new ArrayList<>();
	
	public static void agregarValidaciones(ValidacionPassword validacion) {
		validaciones.add(validacion);
	}
	
	private static boolean seCumplenTodasLasValidaciones(String password) {
		return validaciones.stream().allMatch(validacion -> validacion.cumpleCondicion(password));
	}
	
	public static void validarPassword(String password) {
		if(!seCumplenTodasLasValidaciones(password)) {
			throw new PasswordInseguraException("La contraseña ingresada es insegura");
		}
	}	

}
