package dominio.usuario;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.annotations.Type;

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
	
	@ManyToMany
	List<Mensaje> bandejaDeMensajes;
	
	
	public Usuario(String nombreUsuario, String password, boolean esAdministrador, List<Mensaje> bandejaDeMensajes) {
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
		bandejaDeMensajes.add(mensaje);
	}

	public boolean tieneMensajeConEseContenido(String contenido) {
		return bandejaDeMensajes.stream().anyMatch(mensaje -> mensaje.coincideContenido(contenido));
	}
	
	public List<Mensaje> getMensajes(){
		return this.bandejaDeMensajes;
	}
}
