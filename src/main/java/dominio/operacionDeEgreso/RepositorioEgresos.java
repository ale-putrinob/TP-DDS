package dominio.operacionDeEgreso;

import java.util.List;

public class RepositorioEgresos {
	static List<OperacionEgreso> egresos;

	public static void add(OperacionEgreso egreso) {
		egresos.add(egreso);
	}
	
	public static List<OperacionEgreso> todos(){
		return egresos;
	}

}
