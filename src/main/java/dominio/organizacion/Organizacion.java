package dominio.organizacion;

import java.util.List;
import java.util.stream.Collectors;

import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.usuario.Usuario;

import java.util.ArrayList;

public class Organizacion {
	List<Entidad> entidades = new ArrayList<>();
	public List<OperacionEgreso> operacionesEgreso = new ArrayList<>();
	public List<OperacionEgreso> operacionesEgresoPendientesDeValidacion = new ArrayList<>();
	List<Usuario> usuarios = new ArrayList<>(); 
	
	public Organizacion(List<Entidad> entidades, List<OperacionEgreso> operacionesEgreso, List<OperacionEgreso> operacionesEgresoPendientesDeValidacion, List<Usuario> usuarios) {
		this.entidades = entidades;
		this.operacionesEgreso = operacionesEgreso;
		this.operacionesEgresoPendientesDeValidacion = operacionesEgresoPendientesDeValidacion;
		this.usuarios = usuarios;
	}

	public void validarOperacionesPendientes() {
		operacionesEgresoPendientesDeValidacion.forEach(operacion -> operacion.validarse());
		this.moverEgresosValidos();
		}

	private void moverEgresosValidos() {
		operacionesEgreso.addAll(this.operacionesEgresoValidas());
		operacionesEgresoPendientesDeValidacion.removeAll(this.operacionesEgresoValidas());
	}

	private List<OperacionEgreso> operacionesEgresoValidas() {
		return operacionesEgresoPendientesDeValidacion.stream().filter(egreso -> egreso.esValida()).collect(Collectors.toList());
	}

	/*private void ejecutarValidaciones(OperacionEgreso operacion) {
		operacion.validarse();
		if(operacion.esValida()) {
			operacionesEgresoPendientesDeValidacion.remove(operacion);
			operacionesEgreso.add(operacion);
		}
	}*/
	
	public void agregarOperacionesPendientes(OperacionEgreso egreso) {
		operacionesEgresoPendientesDeValidacion.add(egreso);
	}
}

