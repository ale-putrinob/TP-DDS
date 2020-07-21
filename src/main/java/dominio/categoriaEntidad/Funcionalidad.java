package dominio.categoriaEntidad;

import dominio.organizacion.Entidad;

public abstract class Funcionalidad {
	public boolean aceptaNuevosEgresos(Entidad entidad) {
		return true;
	}
	
	public boolean aceptaNuevasEntidadesBase() {
		return true;
	}
	
	public boolean seaParteDeEntidadJuridica(){
		return true;
	}

	public Object aceptaSerParteDeEntidadJuridica() {
		return true;
	}
}
