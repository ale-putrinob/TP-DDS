package dominio.categoriaEntidad;

import java.util.ArrayList;
import java.util.List;

import dominio.organizacion.Entidad;

public class CategoriaEntidad {
	List<Funcionalidad> funcionalidades = new ArrayList<>();
	
	public boolean aceptaNuevosEgresos(Entidad entidad) {
		return funcionalidades.stream().allMatch(funcionalidad->funcionalidad.aceptaNuevosEgresos(entidad));
	}
	
	public boolean aceptaNuevasEntidadesBase() {
		return funcionalidades.stream().allMatch(funcionalidad->funcionalidad.aceptaNuevasEntidadesBase());
	}
	
	public boolean aceptaSerParteDeEntidadJuridica() {
		return funcionalidades.stream().allMatch(funcionalidad->funcionalidad.seaParteDeEntidadJuridica());
	}
}
