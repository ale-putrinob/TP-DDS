package dominio.item;

import java.util.ArrayList;
import java.util.List;

import dominio.operacionDeEgreso.OperacionEgreso;

public class TipoItem {
	List<OperacionEgreso> egresosAsociados = new ArrayList<>();
	
	public void asociarAEgreso(OperacionEgreso egreso) {
		this.egresosAsociados.add(egreso);
	}
	
	public void desasociarDeEgreso(OperacionEgreso egreso) {
		this.egresosAsociados.remove(egreso);
	}
	
	public boolean estaAsociadoAEgreso() {
		return this.egresosAsociados.size() > 0;
	}
}
