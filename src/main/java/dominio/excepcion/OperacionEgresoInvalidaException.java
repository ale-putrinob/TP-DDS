package dominio.excepcion;

public class OperacionEgresoInvalidaException extends RuntimeException {
	public OperacionEgresoInvalidaException(String msg) {
		super(msg);
	}
}
