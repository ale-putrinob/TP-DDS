package dominio.organizacion;

import java.util.List;

import dominio.operacionDeEgreso.OperacionEgreso;

public class RepositorioEgresos {
	static List<OperacionEgreso> egresos;

	public static void add(OperacionEgreso egreso) {
		egresos.add(egreso);
	}
	
	public static List<OperacionEgreso> todos(){
		return egresos;
	}

}
