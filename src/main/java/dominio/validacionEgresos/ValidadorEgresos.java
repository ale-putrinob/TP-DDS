package dominio.validacionEgresos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dominio.mensajes.Mensaje;
import dominio.operacionDeEgreso.OperacionEgreso;

public class ValidadorEgresos {
	List<ValidacionEgreso> validaciones = new ArrayList<>(Arrays.asList(new ValidacionAplicacionPresupuesto(), new ValidacionSeleccionProveedor(), new ValidacionCantidadPresupuestos()));
	
	public void validarEgreso(OperacionEgreso egreso){
		validaciones.forEach(validacion -> this.aplicarValidacion(validacion, egreso));
	}
	
	void aplicarValidacion(ValidacionEgreso validacion, OperacionEgreso egreso) {
		if(validacion.pasaValidacion(egreso)){
			egreso.enviarMensajeARevisores(new Mensaje(validacion.mensajePositivo(), egreso));
			egreso.cargarResultadoDeValidacion(validacion.mensajePositivo());
		}
		else {
			egreso.enviarMensajeARevisores(new Mensaje(validacion.mensajeNegativo(), egreso));
			egreso.cargarResultadoDeValidacion(validacion.mensajeNegativo());
		}
	}

	public boolean pasaTodasLasValidaciones(OperacionEgreso egreso) {
		return validaciones.stream().allMatch(validacion -> validacion.pasaValidacion(egreso));
	}
	
	public void agregarValidacion(ValidacionEgreso validacion) {
		validaciones.add(validacion);
	}
}


