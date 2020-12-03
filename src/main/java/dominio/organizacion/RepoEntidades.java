package dominio.organizacion;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		return entityManager().find(Entidad.class,new Long(id_entidad));
	}	
	@SuppressWarnings("deprecation")
	public EntidadJuridica buscarEntidadJuridica(String id_dependencia) {
		return entityManager().find(EntidadJuridica.class, new Long(id_dependencia));
	}
}
