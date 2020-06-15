package dominio.buscador;

@FunctionalInterface
public interface Condicion {
	public boolean comparacion(String a, String b);
}
