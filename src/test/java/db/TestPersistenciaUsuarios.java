package db;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import dominio.mensajes.Mensaje;
import dominio.usuario.RepoUsuarios;
import dominio.usuario.Usuario;

public class TestPersistenciaUsuarios extends AbstractPersistenceTest implements WithGlobalEntityManager {
	Usuario jorge;
	List<Usuario> usuarios;
	
	@Before
	public void init() {
		jorge = new Usuario("Jorge", "contraseniasegura", false, new ArrayList<Mensaje>());
		RepoUsuarios.getInstance().agregarUsuario(jorge);
		usuarios = RepoUsuarios.getInstance().getUsuarios();
	}
	
	@Test
	public void TestOperacionEgreso() {
		
		Usuario unUsuario = usuarios.get(0);
		System.out.println(unUsuario.getPassword());
		
		assertEquals(unUsuario, jorge);
	}
	
}
