package dominio.validacionEgresos;

import dominio.operacionDeEgreso.OperacionEgreso;

public class ValidacionCantidadPresupuestos implements ValidacionEgreso {

	@Override
	public boolean pasaValidacion(OperacionEgreso egreso) {
		return egreso.cumpleConLosPresupuestosRequeridos();
	}

	@Override
	public String mensajePositivo() {
		return "Cantidad correcta de presupuestos cargados";
	}

	@Override
	public String mensajeNegativo() {
		return "Cantidad incorrecta de presupuestos cargados";
	}

}
