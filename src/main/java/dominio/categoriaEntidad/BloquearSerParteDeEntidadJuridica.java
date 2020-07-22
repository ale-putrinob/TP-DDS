package dominio.categoriaEntidad;

import dominio.excepcion.EntidadException;

public class BloquearSerParteDeEntidadJuridica extends Funcionalidad{
	
	@Override
	public void validarAdicionAEntidadJuridica(){
		throw new EntidadException("No se puede asociar la entidad base con la entidad jurídica");
	}
}
