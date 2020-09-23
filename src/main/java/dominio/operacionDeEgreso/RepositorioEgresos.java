package dominio.operacionDeEgreso;

import java.util.List;

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


}
