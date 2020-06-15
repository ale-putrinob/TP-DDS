package dominio.validacionPassword;

import dominio.buscador.BuscadorEnArchivo;

public class SinCaracteresConsecutivos implements ValidacionPassword{

	public boolean cumpleCondicion(String password) {
		return !(BuscadorEnArchivo.encontrarInclusion(password, "caracteresConsecutivos.txt"));
	}

}
