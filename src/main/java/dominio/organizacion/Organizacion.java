package dominio.organizacion;

import java.util.List;

import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.operacionDeEgreso.OperacionEgresoConPresupuestoPendienteDeValidacion;
import dominio.usuario.Usuario;

import java.util.ArrayList;

public class Organizacion {
	List<Entidad> entidades;
	List<OperacionEgreso> operacionesEgreso;
	List<OperacionEgresoConPresupuestoPendienteDeValidacion> operacionesEgresoPendientesDeValidacion;
	List<Usuario> usuarios; 
	
	public Organizacion(List<Entidad> entidades, List<OperacionEgreso> operacionesEgreso) {
		this.entidades = new ArrayList<>();
		this.entidades = entidades;
		this.operacionesEgreso = new ArrayList<>();
		this.operacionesEgreso = operacionesEgreso;
	}
	
	
}
