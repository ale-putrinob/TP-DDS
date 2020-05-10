package dominio;

import java.io.IOException;

public class Usuario {
	String nombreUsuario;
	String password;
	
	public Usuario(String nombreUsuario, String password) throws IOException{
		ValidadorPassword.validarPassword(password);
		
		this.nombreUsuario = nombreUsuario;
		this.password = password;
	}
}
