package dominio.usuario;

import org.apache.commons.codec.digest.DigestUtils;

import dominio.mensajes.BandejaDeMensajes;
import dominio.mensajes.Mensaje;
import dominio.validacionPassword.ValidadorPassword;



public class Usuario {
	String nombreUsuario;
	String password;
	boolean esAdministrador;
	BandejaDeMensajes bandejaDeMensajes;
	
	public Usuario(String nombreUsuario, String password, boolean esAdministrador, BandejaDeMensajes bandejaDeMensajes) {
		ValidadorPassword.validarPassword(password);
		
		this.nombreUsuario = nombreUsuario;
		this.password = DigestUtils.md5Hex(password);
		this.esAdministrador = esAdministrador;
		this.bandejaDeMensajes = bandejaDeMensajes;
	}
	
	public String getPassword() {
		return password;
	}

	public void recibirMensaje(Mensaje mensaje) {
		bandejaDeMensajes.agregarMensaje(mensaje);
	}
}
