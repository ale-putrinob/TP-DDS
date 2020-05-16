package dominio.validacion;

import dominio.buscador.BuscadorEnArchivo;

public class SinCaracteresConsecutivos implements Validacion{

	public boolean cumpleCondicion(String password) {
		return !(BuscadorEnArchivo.encontrarInclusion(password, "caracteresConsecutivos.txt"));
	}

}
