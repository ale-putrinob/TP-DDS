package dominio.categoriaEntidad;

import dominio.excepcion.EntidadException;

public class BloquearNuevasEntidadesBase extends Funcionalidad {

	@Override
	public void validarNuevasEntidadesBase() {
		throw new EntidadException("No se puede asociar la entidad base con la entidad jurídica");
	}
}
