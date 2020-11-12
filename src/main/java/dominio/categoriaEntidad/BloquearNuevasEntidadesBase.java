package dominio.categoriaEntidad;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import dominio.excepcion.EntidadException;

@Entity
@DiscriminatorValue("BNEB")
public class BloquearNuevasEntidadesBase extends Funcionalidad {

	@Override
	public void validarNuevasEntidadesBase() {
		throw new EntidadException("No se puede asociar la entidad base con la entidad jurídica");
	}
}
