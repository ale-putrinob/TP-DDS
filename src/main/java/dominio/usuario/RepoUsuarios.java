package dominio.usuario;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepoUsuarios implements WithGlobalEntityManager{
	private final static RepoUsuarios INSTANCE = new RepoUsuarios();
	 
	public static RepoUsuarios getInstance() {
		return INSTANCE;
	}	
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuarios(){
		return entityManager()
				.createQuery("from Usuario")
				.getResultList();
	}

	public void agregarUsuario(Usuario usuario) {
		entityManager().persist(usuario);
	}
	
	/*public Usuario buscarUsuario(String nombre, String password) {
		return entityManager().find(Usuario.class, arg1)
	}*/
}
