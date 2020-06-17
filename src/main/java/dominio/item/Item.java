package dominio.item;

import java.util.ArrayList;
import java.util.List;

import dominio.operacionDeEgreso.OperacionEgreso;

public class Item {
	double valorItem;
	TipoItem tipo;
	
	
	public Item(double valorItem, TipoItem tipo) {
		this.valorItem=valorItem;
		this.tipo=tipo;
	};
	
	public double getValorItem() {
		return this.valorItem;
	}
	
	public void asociarAEgreso(OperacionEgreso egreso) {
		tipo.asociarAEgreso(egreso);
	}
	
	public void desasociarDeEgreso(OperacionEgreso egreso) {
		tipo.desasociarDeEgreso(egreso);
	}
	
	public boolean estaAsociadoAEgreso() {
		return tipo.estaAsociadoAEgreso();
	}
}
