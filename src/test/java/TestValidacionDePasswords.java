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
	public void testPasswordInsegura() throws IOException {
		usuario = new Usuario("usuario", "12303923");
	}
}
