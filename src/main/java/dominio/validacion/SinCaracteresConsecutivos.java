package dominio;

public class SinCaracteresConsecutivos implements Validacion{

	public boolean cumpleCondicion(String password) {
		return !(BuscadorEnArchivo.encontrarInclusion(password, "caracteresConsecutivos.txt"));
	}

}
