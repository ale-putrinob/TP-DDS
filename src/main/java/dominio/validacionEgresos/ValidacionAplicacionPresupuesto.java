package dominio.validacionEgresos;

import dominio.operacionDeEgreso.OperacionEgreso;

public class ValidacionAplicacionPresupuesto implements ValidacionEgreso {

	@Override
	public boolean pasaValidacion(OperacionEgreso egreso) {
		return egreso.aplicaAlgunPresupuesto();
	}

	@Override
	public String mensajePositivo() {
		// TODO Auto-generated method stub
		return "Se está aplicando alguno de los presupuestos en la compra";
	}

	@Override
	public String mensajeNegativo() {
		return "¡No se aplica ninguno de los presupuestos en la compra!";
	}

}
