package dominio.organizacion;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import dominio.categoriaEntidad.CategoriaEntidad;

@Entity
public class EntidadBase extends Entidad {
	String nombreFicticio;
	String descripcion;
	
	@ManyToOne (cascade = {CascadeType.PERSIST})
	EntidadJuridica dependencia;

	public EntidadBase(String nombreFicticio, String unaDescripcion, EntidadJuridica unaDependencia, CategoriaEntidad categoriaEntidad) {
		this.dependencia = unaDependencia;
		this.validarDependencia(unaDependencia);
		this.nombreFicticio = nombreFicticio;
		this.descripcion = unaDescripcion;
		this.categoriaEntidad = categoriaEntidad;
		
		
	}

	public EntidadBase() {}
	
	private void validarDependencia(EntidadJuridica entidadJuridica) {
		if(categoriaEntidad != null)
			this.categoriaEntidad.validarAdicionAEntidadJuridica();
		entidadJuridica.validarNuevasEntidadesBase();
	}
	
	public EntidadJuridica getDependencia() {
		return dependencia;
	}
	
	public String getNombreFicticio() {
		return nombreFicticio;
	}
	
}
