package dominio.excepcion;

public class PasswordInseguraException extends RuntimeException {
	public PasswordInseguraException(String msg){
		super(msg);
	}
}
