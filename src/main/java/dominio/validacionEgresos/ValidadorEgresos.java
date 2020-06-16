package dominio.validacionEgresos;

import java.util.List;

import dominio.mensajes.Mensaje;
import dominio.operacionDeEgreso.OperacionEgreso;

public class ValidadorEgresos {
	List<ValidacionEgreso> validaciones;
	
	public void validarEgreso(OperacionEgreso egreso){
		validaciones.forEach(validacion -> this.aplicarValidacion(validacion, egreso));
	}
	
	void aplicarValidacion(ValidacionEgreso validacion, OperacionEgreso egreso) {
		if(validacion.pasaValidacion(egreso)) 
			egreso.enviarMensajeARevisores(new Mensaje(validacion.mensajePositivo(), egreso));
		else
			egreso.enviarMensajeARevisores(new Mensaje(validacion.mensajeNegativo(), egreso));
	}
}
