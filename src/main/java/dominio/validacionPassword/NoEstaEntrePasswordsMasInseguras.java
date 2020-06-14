package dominio.validacionPassword;

import dominio.buscador.BuscadorEnArchivo;

public class NoEstaEntrePasswordsMasInseguras implements Validacion{

	public boolean cumpleCondicion(String password) {
		return !(BuscadorEnArchivo.encontrarIgualdad(password, "10k-most-common-passwords.txt"));
	}

}
