package dominio;

public class LongitudPassword implements Validacion{

	public boolean cumpleCondicion(String password) {
		return password.length() >= 8;
	}

}
