package dominio.mensajes;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class BandejaDeMensajes {
	
	@Id
	@GeneratedValue
	private Long bandejaDeMensajes_id;
	
	@ManyToMany
	List<Mensaje> mensajes = new ArrayList<>();
	
	public void agregarMensaje(Mensaje mensaje) {
		mensajes.add(mensaje);
	}

	public boolean tieneMensajeConEseContenido(String contenido) {
		return mensajes.stream().anyMatch(mensaje -> mensaje.coincideContenido(contenido));
	}
	
	public List<Mensaje> getMensajes(){
		return this.mensajes;
	}
}
