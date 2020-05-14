package dominio;

import org.apache.commons.codec.digest.DigestUtils;

public abstract class Usuario {
	String nombreUsuario;
	String password;
	
	public Usuario(String nombreUsuario, String password) {
		ValidadorPassword.validarPassword(password);
		
		this.nombreUsuario = nombreUsuario;
		this.password = DigestUtils.md5Hex(password);
	}
	
	public String getPassword() {
		return password;
	}
}
