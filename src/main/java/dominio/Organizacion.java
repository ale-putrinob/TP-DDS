package dominio;

import java.util.List;
import java.util.ArrayList;

public class Organizacion {
	List<Entidad> entidades;
	List<OperacionEgreso> operacionesEgreso;
	List<Usuario> usuarios; // a revisar
	
	public Organizacion(List<Entidad> entidades, List<OperacionEgreso> operacionesEgreso) {
		this.entidades = new ArrayList<>();
		this.entidades = entidades;
		this.operacionesEgreso = new ArrayList<>();
		this.operacionesEgreso = operacionesEgreso;
	}
}
