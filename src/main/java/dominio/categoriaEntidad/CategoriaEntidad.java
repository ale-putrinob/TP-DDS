package dominio.categoriaEntidad;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import dominio.organizacion.Entidad;

@Entity
public class CategoriaEntidad {
	@Id
	@GeneratedValue
	private Long id;
	
	String nombre;
	
	@ManyToMany
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
