package dominio.organizacion;

import java.util.List;
import java.util.stream.Collectors;

import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.usuario.Usuario;

import java.util.ArrayList;

public class Organizacion {
	List<Entidad> entidades = new ArrayList<>();
	public List<OperacionEgreso> operacionesEgreso = new ArrayList<>();
	List<Usuario> usuarios = new ArrayList<>(); 
	
	public Organizacion(List<Entidad> entidades, List<OperacionEgreso> operacionesEgreso, List<Usuario> usuarios) {
		this.entidades = entidades;
		this.operacionesEgreso = operacionesEgreso;
		this.usuarios = usuarios;
	}

	public void validarOperacionesPendientes() {
		this.operacionesEgresoPendientesDeValidacion().forEach(operacion -> operacion.validarse());
		}

	public List<OperacionEgreso> operacionesEgresoValidas() {
		return operacionesEgresoPendientesDeValidacion().stream().filter(egreso -> egreso.esValida()).collect(Collectors.toList());
	}
	
	public List<OperacionEgreso> operacionesEgresoPendientesDeValidacion(){
		return operacionesEgreso.stream().filter(egreso -> egreso.estaPendienteDeValidacion()).collect(Collectors.toList());
	}
	
	public void agregarOperacionesEgreso(OperacionEgreso egreso) {
		operacionesEgreso.add(egreso);
	}
}

