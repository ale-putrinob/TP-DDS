package dominio.validacion;

@FunctionalInterface
public interface Condicion {
	public boolean comparacion(String a, String b);
}
