package dominio.validacionEgresos;

import dominio.operacionDeEgreso.OperacionEgreso;

public class ValidacionSeleccionProveedor implements ValidacionEgreso {

	@Override
	public boolean pasaValidacion(OperacionEgreso egreso) {
		return egreso.seEligioProveedorSegunCriterio();
	}

	@Override
	public String mensajePositivo() {
		return "Se ha seleccionado al proveedor correcto según el criterio elegido";
	}

	@Override
	public String mensajeNegativo() {
		return "No se respeta el criterio seleccionado para elegir al proveedor!";
	}

}
