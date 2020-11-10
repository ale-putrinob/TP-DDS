package dominio.organizacion;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepoEntidades implements WithGlobalEntityManager{
	private final static RepoEntidades INSTANCE = new RepoEntidades();
	 
	public static RepoEntidades getInstance() {
		return INSTANCE;
	}	
	
	@SuppressWarnings("unchecked")
	public List<Entidad> getEntidad(){
		return entityManager().createQuery("from Entidad").getResultList();
	}

	public void agregarEntidad(Entidad entidad) {
		entityManager().persist(entidad);
	}
}
