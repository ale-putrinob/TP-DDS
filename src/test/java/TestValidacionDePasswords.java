import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import dominio.PasswordInseguraException;
import dominio.Usuario;

public class TestValidacionDePasswords {
	Usuario usuario;
	
	@Before
	public void init() {
		
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordEntreLasMasInseguras() throws IOException {
		usuario = new Usuario("usuario", "baseball");
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordCorta() throws IOException {
		usuario = new Usuario("usuario", "luna");
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresConsecutivos() throws IOException {
		usuario = new Usuario("usuario", "lunas123");
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresRepetidos() throws IOException {
		usuario = new Usuario("usuario", "estrellaaa");
	}
}
