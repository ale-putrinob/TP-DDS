package dominio.organizacion;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

@SuppressWarnings("unchecked")
public class RepoEntidades implements WithGlobalEntityManager{
	private final static RepoEntidades INSTANCE = new RepoEntidades();
	 
	public static RepoEntidades getInstance() {
		return INSTANCE;
	}	
	
	public List<Entidad> getEntidades(){
		return entityManager().createQuery("from Entidad").getResultList();
	}
	
	public List<EntidadJuridica> getEntidadesJuridicas(){
		return entityManager().createQuery("from EntidadJuridica").getResultList();
	}

	public List<EntidadBase> getEntidadesBase(){
		return entityManager().createQuery("from EntidadBase").getResultList();
	}
	
	public void agregarEntidad(Entidad entidad) {
		entityManager().persist(entidad);
	}
	
	public void agregarEntidadJuridica(EntidadJuridica entidad) {
		entityManager().persist(entidad);
	}
	
	public void agregarEntidadBase(EntidadBase entidad) {
		entityManager().persist(entidad);
	}

	public EntidadJuridica buscarEntidadJuridica(String id_dependencia) {
		return entityManager().find(EntidadJuridica.class, new Long(id_dependencia));
	}
}
