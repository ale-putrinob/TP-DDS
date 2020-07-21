package dominio.organizacion;

import java.util.ArrayList;
import java.util.List;

import dominio.operacionDeEgreso.OperacionEgreso;

public class RepositorioEgresos {
	private static List<OperacionEgreso> egresos = new ArrayList<>();	 
	 
	public  static RepositorioEgresos getRepo() {
	 return (RepositorioEgresos) egresos;
	 }

	public static void add(OperacionEgreso egreso) {
		egresos.add(egreso);
	}
	
	public static List<OperacionEgreso> todos(){
		return egresos;
	}

}
