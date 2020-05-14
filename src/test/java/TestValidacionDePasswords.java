import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

import dominio.SinCaracteresConsecutivos;
import dominio.SinCaracteresRepetidos;
import dominio.NoEstaEntrePasswordsMasInseguras;
import dominio.LongitudPassword;
import dominio.PasswordInseguraException;
import dominio.Usuario;
import dominio.ValidadorPassword;
import junit.framework.Assert;

public class TestValidacionDePasswords {
	Usuario usuario;
	
	@Before
	public void init() {
		ValidadorPassword.agregarValidaciones(new SinCaracteresRepetidos());
		ValidadorPassword.agregarValidaciones(new SinCaracteresConsecutivos());
		ValidadorPassword.agregarValidaciones(new LongitudPassword());
		ValidadorPassword.agregarValidaciones(new NoEstaEntrePasswordsMasInseguras());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPasswordEncriptada() throws IOException {
		usuario = new Usuario("usuario", "passwordsegura", false);
		Assert.assertNotSame(usuario.getPassword(), "passwordsegura");
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordEntreLasMasInseguras() throws IOException {
		usuario = new Usuario("usuario", "baseball", false);
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPassword7Caracteres() throws IOException {
		usuario = new Usuario("usuario", "septimo", false);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPassword8Caracteres() throws IOException {
		usuario = new Usuario("usuario", "septimo1", false);
		Assert.assertEquals(DigestUtils.md5Hex("septimo1"), usuario.getPassword());
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresConsecutivosAlFinal() throws IOException {
		usuario = new Usuario("usuario", "lunas123", false);
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresConsecutivosAlPrincipio() throws IOException {
		usuario = new Usuario("usuario", "123lunas", false);
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresConsecutivosEnElMedio() throws IOException {
		usuario = new Usuario("usuario", "lunabcs", false);
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresRepetidosEnElMedio() throws IOException {
		usuario = new Usuario("usuario", "estrellla", false);
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresRepetidosEnAlPrincipio() throws IOException {
		usuario = new Usuario("usuario", "eeestrella", false);
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresRepetidosEnAlFinal() throws IOException {
		usuario = new Usuario("usuario", "estrellaaa", false);
	}
}
