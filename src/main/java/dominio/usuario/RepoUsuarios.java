package dominio.usuario;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
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

	public boolean tieneUsuario(String nombre, String password) {
		List<Usuario> usuarios = entityManager().createQuery("from Usuario where nombreUsuario = :nombre and password = :password", Usuario.class).
									setParameter("nombre",nombre).setParameter("password",DigestUtils.md5Hex(password)).
									getResultList();		
		
		return !usuarios.isEmpty();
	}
	
	/*public Usuario buscarUsuario(String nombre, String password) {
		return entityManager().find(Usuario.class, arg1)
	}*/
}
