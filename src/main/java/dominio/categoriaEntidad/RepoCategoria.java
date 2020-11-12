package dominio.categoriaEntidad;

import java.util.List;

import javax.persistence.TypedQuery;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import dominio.organizacion.Entidad;

public class RepoCategoria implements WithGlobalEntityManager{
	public static RepoCategoria INSTANCE=new RepoCategoria();
	
	public static RepoCategoria getInstance() {
		return INSTANCE;
	}
	
	public void agregarCategoria(CategoriaEntidad categoria){
		entityManager().persist(categoria);
	}
	
	public CategoriaEntidad findCategoria(String id) {
		return entityManager().find(CategoriaEntidad.class, new Long(id));
	}
	
	@SuppressWarnings("unchecked")
	public List<CategoriaEntidad> getCategorias(){
		return entityManager().createQuery("from CategoriaEntidad").getResultList();
	}
}
