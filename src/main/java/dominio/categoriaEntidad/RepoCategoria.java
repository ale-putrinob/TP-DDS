package dominio.categoriaEntidad;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepoCategoria implements WithGlobalEntityManager{
	public static RepoCategoria INSTANCE=new RepoCategoria();
	
	public void agregarCategoria(CategoriaEntidad categoria){
		entityManager().persist(categoria);
	}
}
