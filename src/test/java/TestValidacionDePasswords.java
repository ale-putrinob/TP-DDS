import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

import dominio.PasswordInseguraException;
import dominio.Usuario;
import junit.framework.Assert;

public class TestValidacionDePasswords {
	Usuario usuario;
	
	@Before
	public void init() {
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPasswordEncriptada() throws IOException {
		usuario = new Usuario("usuario", "passwordsegura");
		Assert.assertNotSame(usuario.getPassword(), "passwordsegura");
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordEntreLasMasInseguras() throws IOException {
		usuario = new Usuario("usuario", "baseball");
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPassword7Caracteres() throws IOException {
		usuario = new Usuario("usuario", "septimo");
	}
	
	@SuppressWarnings("deprecation")
	@Test (expected = PasswordInseguraException.class)
	public void testPassword8Caracteres() throws IOException {
		usuario = new Usuario("usuario", "septimo");
		Assert.assertEquals(DigestUtils.md5Hex("septimo"), usuario.getPassword());
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresConsecutivosAlFinal() throws IOException {
		usuario = new Usuario("usuario", "lunas123");
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresConsecutivosAlPrincipio() throws IOException {
		usuario = new Usuario("usuario", "123lunas");
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresConsecutivosEnElMedio() throws IOException {
		usuario = new Usuario("usuario", "lunabcs");
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresRepetidosEnElMedio() throws IOException {
		usuario = new Usuario("usuario", "estrellla");
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresRepetidosEnAlPrincipio() throws IOException {
		usuario = new Usuario("usuario", "eeestrella");
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresRepetidosEnAlFinal() throws IOException {
		usuario = new Usuario("usuario", "estrellaaa");
	}
}
