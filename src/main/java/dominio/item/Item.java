package dominio.item;

import java.util.ArrayList;
import java.util.List;

import dominio.moneda.TipoMoneda;
import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.repositorioApiML.MerLibAPI;

public class Item {
	double valorItem;
	String tipo;
	TipoMoneda moneda;
	List<OperacionEgreso> egresosAsociados = new ArrayList<>();

	public Item(double valorItem, String tipo, String idMoneda) {
		this.valorItem=valorItem;
		this.tipo=tipo;
		this.moneda = MerLibAPI.getUnaMoneda(idMoneda);
	};
	
	public double getValorItem() {
		return this.valorItem;
	}
	
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
