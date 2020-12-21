package dominio.operacionDeEgreso;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioEgresos implements WithGlobalEntityManager{
	
	private final static RepositorioEgresos INSTANCE = new RepositorioEgresos();
	 
	public static RepositorioEgresos getInstance() {
		return INSTANCE;
	}	
	
	@SuppressWarnings("unchecked")
	public List<OperacionEgreso> getEgresos(){
		return entityManager()
				.createQuery("from OperacionEgreso")
				.getResultList();
	}

	public void agregarEgreso(OperacionEgreso egreso) {
		entityManager().persist(egreso);
	}

	public List<OperacionEgreso> operacionesEgresoPendientesDeValidacion() {
		return entityManager()
				.createQuery("from OperacionEgreso where estado = :estado", OperacionEgreso.class)
				.setParameter("estado", "SIN_VALIDAR")
				.getResultList();
	}

	public void validarOperacionesPendientes() {
		this.operacionesEgresoPendientesDeValidacion().forEach(operacion -> operacion.validarse());
	}

}
