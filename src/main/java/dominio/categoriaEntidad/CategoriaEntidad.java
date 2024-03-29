package dominio.categoriaEntidad;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import dominio.organizacion.Entidad;
import dominio.persistentEntity.PersistentEntity;

@Entity
public class CategoriaEntidad extends PersistentEntity{
	
	String nombre;
	
	@OneToMany()
	@JoinColumn(name = "categoriaEntidad_id")
	List<Funcionalidad> funcionalidades = new ArrayList<>();
	
	public CategoriaEntidad(String nombre) {
		this.nombre = nombre;
	}
	
	public CategoriaEntidad(){}
	
	public void validarNuevosEgresos(Entidad entidad) {
		if(!funcionalidades.isEmpty())
			funcionalidades.stream().forEach(funcionalidad->funcionalidad.validarNuevosEgresos(entidad));
	}
	
	public void validarNuevasEntidadesBase() {
		if(!funcionalidades.isEmpty())
			funcionalidades.stream().forEach(funcionalidad->funcionalidad.validarNuevasEntidadesBase());
	}
	
	public void validarAdicionAEntidadJuridica() {
		if(!funcionalidades.isEmpty())
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
	public String getNombre() {
		return nombre;
	}
}
