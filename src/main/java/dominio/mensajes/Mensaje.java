package dominio.mensajes;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.persistentEntity.PersistentEntity;

@Entity
public class Mensaje extends PersistentEntity{
			
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
