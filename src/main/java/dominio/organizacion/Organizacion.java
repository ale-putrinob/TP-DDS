package dominio.organizacion;

import java.util.List;
import java.util.stream.Collectors;

import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.operacionDeEgreso.RepositorioEgresos;

public class Organizacion {
	
	//List<Entidad> entidades = new ArrayList<>();
	//List<Usuario> usuarios = new ArrayList<>();

	/*public Organizacion(List<Entidad> entidades, List<OperacionEgreso> operacionesEgreso, List<Usuario> usuarios) {
		this.entidades = entidades;
		this.usuarios = usuarios;
	}*/
	public Organizacion() {
	}
	
	
	public void validarOperacionesPendientes() {
		this.operacionesEgresoPendientesDeValidacion().forEach(operacion -> operacion.validarse());
	}

	public List<OperacionEgreso> operacionesEgresoValidas() {
		return RepositorioEgresos.getInstance().getEgresos().stream().filter(egreso -> egreso.esValida())
				.collect(Collectors.toList());
	}
	
	public List<OperacionEgreso> operacionesEgresoInvalidas() {
		return RepositorioEgresos.getInstance().getEgresos().stream().filter(egreso -> egreso.esInvalida())
				.collect(Collectors.toList());
	}

	public List<OperacionEgreso> operacionesEgresoPendientesDeValidacion() {
		return RepositorioEgresos.getInstance().getEgresos().stream().filter(egreso -> egreso.estaPendienteDeValidacion())
				.collect(Collectors.toList());
	}
	
}
