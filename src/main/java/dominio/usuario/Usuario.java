package dominio;

import org.apache.commons.codec.digest.DigestUtils;

public class Usuario {
	String nombreUsuario;
	String password;
	boolean esAdministrador;
	
	public Usuario(String nombreUsuario, String password, boolean esAdministrador) {
		ValidadorPassword.validarPassword(password);
		
		this.nombreUsuario = nombreUsuario;
		this.password = DigestUtils.md5Hex(password);
		this.esAdministrador = esAdministrador;
	}
	
	public String getPassword() {
		return password;
	}
}
