package dominio.categoriaEntidad;

import dominio.excepcion.EntidadException;
import dominio.organizacion.Entidad;

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
