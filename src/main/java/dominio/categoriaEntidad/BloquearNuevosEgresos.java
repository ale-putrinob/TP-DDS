package dominio.categoriaEntidad;

import dominio.organizacion.Entidad;

public class BloquearNuevosEgresos extends Funcionalidad{
	double montoMaximo;
	
	public BloquearNuevosEgresos(double montoMaximo){
		this.montoMaximo = montoMaximo;
	}
	
	@Override
	public boolean aceptaNuevosEgresos(Entidad entidad) {
		return entidad.totalDeEgresos() <= montoMaximo;
	}
}
