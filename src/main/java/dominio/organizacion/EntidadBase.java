package dominio.organizacion;

import dominio.categoriaEntidad.CategoriaEntidad;
import dominio.excepcion.EntidadException;

public class EntidadBase extends Entidad {
	String nombreFicticio;
	String descripcion;
	EntidadJuridica dependencia;

	public EntidadBase(String nombreFicticio, String unaDescripcion, EntidadJuridica unaDependencia, CategoriaEntidad categoriaEntidad) {
		this.nombreFicticio = nombreFicticio;
		this.descripcion = unaDescripcion;
		this.categoriaEntidad = categoriaEntidad;
		this.validarDependencia(unaDependencia);
		this.dependencia = unaDependencia;
		
	}

	private void validarDependencia(EntidadJuridica entidadJuridica) {
		if(!(this.puedeSerParteDe(entidadJuridica) && entidadJuridica.aceptaNuevasEntidadesBase())) {
			throw new EntidadException("No se puede asociar la entidad base con la entidad jurídica");
		}
	}

	private boolean puedeSerParteDe(EntidadJuridica entidadJuridica) {
		return this.categoriaEntidad.aceptaSerParteDeEntidadJuridica();
	}
	
	public EntidadJuridica getDependencia() {
		return dependencia;
	}
	
}
