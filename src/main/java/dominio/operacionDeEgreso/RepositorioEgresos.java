package dominio.operacionDeEgreso;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class RepositorioEgresos implements WithGlobalEntityManager, TransactionalOps {
	
	private final static RepositorioEgresos INSTANCE = new RepositorioEgresos();
	 
	public static RepositorioEgresos getInstance() {
		return INSTANCE;
	}	
	
	@SuppressWarnings("unchecked")
	public List<OperacionEgreso> getEgresos(){
		return entityManager()
				.createQuery("from OperacionEgreso", OperacionEgreso.class)
				.getResultList();
	}

	public void agregarEgreso(OperacionEgreso egreso) {
		entityManager().persist(egreso);
	}

	public void actualizarEgreso(OperacionEgreso egreso){
		withTransaction(() -> {
			entityManager().persist(egreso);
		});
	}

	public List<OperacionEgreso> operacionesEgresoPendientesDeValidacion() {
		List<OperacionEgreso> operacionesEgresos = entityManager().createQuery("from OperacionEgreso", OperacionEgreso.class).getResultList();
		return operacionesEgresos.stream().filter(egreso -> egreso.estaPendienteDeValidacion()).collect(Collectors.toList());
				/*.createQuery("from OperacionEgreso where estado = :estado", OperacionEgreso.class)
				.setParameter("estado", "SIN_VALIDAR")
				.getResultList();*/
	}

	public void validarOperacionesPendientes() {
		System.out.println(operacionesEgresoPendientesDeValidacion().size() + "---------------------------");
		this.operacionesEgresoPendientesDeValidacion().forEach(operacion -> operacion.validarse());
	}

}
