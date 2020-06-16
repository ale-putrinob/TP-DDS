package dominio.mensajes;

import java.util.ArrayList;
import java.util.List;

public class BandejaDeMensajes {
	List<Mensaje> mensajes = new ArrayList<>();
	
	public void agregarMensaje(Mensaje mensaje) {
		mensajes.add(mensaje);
	}
}
