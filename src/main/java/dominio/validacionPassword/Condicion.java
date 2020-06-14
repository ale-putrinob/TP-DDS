package dominio.validacionPassword;

@FunctionalInterface
public interface Condicion {
	public boolean comparacion(String a, String b);
}
