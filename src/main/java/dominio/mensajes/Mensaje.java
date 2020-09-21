package dominio.mensajes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import dominio.operacionDeEgreso.OperacionEgreso;

@Entity
public class Mensaje {
	
	@Id
	@GeneratedValue
	private Long mensaje_id;
	
	
	String contenido;
	@ManyToOne
	OperacionEgreso operacionEgreso;
	
	public Mensaje(String contenido, OperacionEgreso operacionEgreso) {
		this.contenido = contenido;
		this.operacionEgreso = operacionEgreso;
	}
	
	public boolean coincideContenido(String otroContenido) {
		return contenido.equals(otroContenido);
	}
}
