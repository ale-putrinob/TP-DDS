package dominio.categoriaEntidad;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import dominio.excepcion.EntidadException;
import dominio.organizacion.Entidad;

@Entity
@DiscriminatorValue("BNE")
public class BloquearNuevosEgresos extends Funcionalidad{
	double montoMaximo;
	
	public BloquearNuevosEgresos(double montoMaximo){
		this.montoMaximo = montoMaximo;
	}
	
	@Override
	public void validarNuevosEgresos(Entidad entidad) {
		if(entidad.totalDeEgresosDeLaEntidad() <= montoMaximo) {
			throw new EntidadException("No se puede agregar el egreso porque se ha superado el monto establecido");
		}
	}
}
