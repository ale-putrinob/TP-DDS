package dominio;

import java.io.IOException;

public class Administrador extends Usuario{

	public Administrador(String nombreUsuario, String password) throws IOException {
		super(nombreUsuario, password);
	}

}
