package dominio.usuario;

import org.apache.commons.codec.digest.DigestUtils;

import dominio.validacion.ValidadorPassword;



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
