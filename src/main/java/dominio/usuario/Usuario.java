package dominio.usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.annotations.Type;

import dominio.mensajes.BandejaDeMensajes;
import dominio.mensajes.Mensaje;
import dominio.validacionPassword.ValidadorPassword;


@Entity
public class Usuario {
	
	@Id
	@GeneratedValue
	private Long id_usuario;
	
	String nombreUsuario;
	String password;
	
	@Type(type="yes_no") 
	boolean esAdministrador;
	
	@OneToOne
	@JoinColumn(name = "BandejaDeMensajes")
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
