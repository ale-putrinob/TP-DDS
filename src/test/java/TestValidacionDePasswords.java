import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import dominio.excepcion.PasswordInseguraException;
import dominio.mensajes.BandejaDeMensajes;
import dominio.usuario.Usuario;
import dominio.validacionPassword.LongitudPassword;
import dominio.validacionPassword.NoEstaEntrePasswordsMasInseguras;
import dominio.validacionPassword.SinCaracteresConsecutivos;
import dominio.validacionPassword.SinCaracteresRepetidos;
import dominio.validacionPassword.ValidadorPassword;
import junit.framework.Assert;


@SuppressWarnings("deprecation")
public class TestValidacionDePasswords {
	Usuario usuario;
	static BandejaDeMensajes bandejaDeMensajes;
	
	@BeforeClass 
	public static void init() {
		bandejaDeMensajes = new BandejaDeMensajes();
		ValidadorPassword.agregarValidaciones(new SinCaracteresRepetidos());
		ValidadorPassword.agregarValidaciones(new SinCaracteresConsecutivos());
		ValidadorPassword.agregarValidaciones(new LongitudPassword());
		ValidadorPassword.agregarValidaciones(new NoEstaEntrePasswordsMasInseguras());
	}
	
	@Test
	public void testPasswordEncriptada() throws IOException {
		usuario = new Usuario("usuario", "passwordsegura", false, bandejaDeMensajes);
		Assert.assertNotSame(usuario.getPassword(), "passwordsegura");
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordEntreLasMasInseguras() throws IOException {
		usuario = new Usuario("usuario", "baseball", false, bandejaDeMensajes);
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPassword7Caracteres() throws IOException {
		usuario = new Usuario("usuario", "septimo", false, bandejaDeMensajes);
	}
	
	@Test
	public void testPassword8Caracteres() throws IOException {
		usuario = new Usuario("usuario", "septimo1", false, bandejaDeMensajes);
		Assert.assertEquals(DigestUtils.md5Hex("septimo1"), usuario.getPassword());
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresConsecutivosAlFinal() throws IOException {
		usuario = new Usuario("usuario", "lunas123", false, bandejaDeMensajes);
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresConsecutivosAlPrincipio() throws IOException {
		usuario = new Usuario("usuario", "123lunas", false, bandejaDeMensajes);
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresConsecutivosEnElMedio() throws IOException {
		usuario = new Usuario("usuario", "lunabcs", false, bandejaDeMensajes);
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresRepetidosEnElMedio() throws IOException {
		usuario = new Usuario("usuario", "estrellla", false, bandejaDeMensajes);
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresRepetidosEnAlPrincipio() throws IOException {
		usuario = new Usuario("usuario", "eeestrella", false, bandejaDeMensajes);
	}
	
	@Test (expected = PasswordInseguraException.class)
	public void testPasswordConCaracteresRepetidosEnAlFinal() throws IOException {
		usuario = new Usuario("usuario", "estrellaaa", false, bandejaDeMensajes);
	}
}
