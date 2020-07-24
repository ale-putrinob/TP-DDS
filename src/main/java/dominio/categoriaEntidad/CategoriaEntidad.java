package dominio.categoriaEntidad;

import java.util.ArrayList;
import java.util.List;

import dominio.organizacion.Entidad;

public class CategoriaEntidad {
	String nombre;
	List<Funcionalidad> funcionalidades = new ArrayList<>();
	
	public void validarNuevosEgresos(Entidad entidad) {
		funcionalidades.stream().forEach(funcionalidad->funcionalidad.validarNuevosEgresos(entidad));
	}
	
	public void validarNuevasEntidadesBase() {
		funcionalidades.stream().forEach(funcionalidad->funcionalidad.validarNuevasEntidadesBase());
	}
	
	public void validarAdicionAEntidadJuridica() {
		funcionalidades.stream().forEach(funcionalidad->funcionalidad.validarAdicionAEntidadJuridica());
	}
	
	public void agregarFuncionalidad(Funcionalidad funcionalidad) {
		funcionalidades.add(funcionalidad);
	}
	
	public void quitarFuncionalidad(Funcionalidad funcionalidad) {
		funcionalidades.remove(funcionalidad);
	}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
}
