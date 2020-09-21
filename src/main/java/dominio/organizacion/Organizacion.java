package dominio.organizacion;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.operacionDeEgreso.RepositorioEgresos;
import dominio.usuario.Usuario;

import java.util.ArrayList;

@Entity
public class Organizacion {
	
	@Id
	@GeneratedValue
	private Long organizacion_id;
	
	/*checkear entidades*/
	@Transient
	List<Entidad> entidades = new ArrayList<>();
	@OneToMany
	@JoinColumn(name = "organizacion_id")
	List<Usuario> usuarios = new ArrayList<>();

	public Organizacion(List<Entidad> entidades, List<OperacionEgreso> operacionesEgreso, List<Usuario> usuarios) {
		this.entidades = entidades;
		this.usuarios = usuarios;
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

	public void agregarOperacionesEgreso(OperacionEgreso egreso) {
		RepositorioEgresos.getInstance().agregarEgreso(egreso);
	}
}
