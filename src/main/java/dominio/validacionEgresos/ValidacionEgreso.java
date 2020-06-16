package dominio.validacionEgresos;

import dominio.operacionDeEgreso.OperacionEgreso;

public interface ValidacionEgreso {

	public boolean pasaValidacion(OperacionEgreso egreso);

	public String mensajePositivo();
	
	public String mensajeNegativo();

}
