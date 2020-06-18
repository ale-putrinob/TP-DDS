package dominio.mensajes;

import dominio.operacionDeEgreso.OperacionEgreso;

public class Mensaje {
	String contenido;
	OperacionEgreso operacionEgreso;
	
	public Mensaje(String contenido, OperacionEgreso operacionEgreso) {
		this.contenido = contenido;
		this.operacionEgreso = operacionEgreso;
	}
	
	public boolean coincideContenido(String otroContenido) {
		return contenido.equals(otroContenido);
	}
}
