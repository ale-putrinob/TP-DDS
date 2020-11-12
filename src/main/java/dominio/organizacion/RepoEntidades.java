package dominio.organizacion;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import dominio.proveedor.Proveedor;

@SuppressWarnings("unchecked")
public class RepoEntidades implements WithGlobalEntityManager{
	private final static RepoEntidades INSTANCE = new RepoEntidades();
	 
	public static RepoEntidades getInstance() {
		return INSTANCE;
	}	
	
	public List<Entidad> getEntidades(){
		return entityManager().createQuery("from Entidad").getResultList();
	}
	
	public List<Entidad> getEntidadesTotales(){
		return Stream.concat(this.getEntidadesJuridicas().stream(), this.getEntidadesBase().stream()) .collect(Collectors.toList());
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

	@SuppressWarnings("deprecation")
	public Entidad findEntidad(String id_entidad) {
		Entidad entidad1 = entityManager().find(EntidadJuridica.class,new Long(id_entidad));
		if(entidad1 == null) {
			entidad1 = entityManager().find(EntidadBase.class,new Long(id_entidad));
		}
		
		return entidad1;
	}
}
