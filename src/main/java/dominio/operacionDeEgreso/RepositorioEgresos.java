package dominio.operacionDeEgreso;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEgresos {
	
	private List<OperacionEgreso> egresos = new ArrayList<>();	
	private final static RepositorioEgresos INSTANCE = new RepositorioEgresos();
	 
	public static RepositorioEgresos getInstance() {
		return INSTANCE;
	}
	
	public List<OperacionEgreso> getEgresos(){
		return egresos;
	}

	public void agregarEgreso(OperacionEgreso egreso) {
		egresos.add(egreso);
	}

}
