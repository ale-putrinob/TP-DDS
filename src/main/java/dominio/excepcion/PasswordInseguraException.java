package dominio;

public class PasswordInseguraException extends RuntimeException {
	PasswordInseguraException(String msg){
		super(msg);
	}
}
