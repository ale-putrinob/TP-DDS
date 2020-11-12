package dominio.categoriaEntidad;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import dominio.excepcion.EntidadException;

@Entity
@DiscriminatorValue("BEJ")
public class BloquearSerParteDeEntidadJuridica extends Funcionalidad{
	
	@Override
	public void validarAdicionAEntidadJuridica(){
		throw new EntidadException("No se puede asociar la entidad base con la entidad jurídica");
	}
}
