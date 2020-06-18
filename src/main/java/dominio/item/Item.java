package dominio.item;

import dominio.generadorDeObjetoFromJson.GeneradorDeObjetoFromJson;
import dominio.moneda.TipoMoneda;
import dominio.operacionDeEgreso.OperacionEgreso;

public class Item {
	double valorItem;
	TipoItem tipo;
	TipoMoneda moneda;
	GeneradorDeObjetoFromJson generadorDeMonedas = new GeneradorDeObjetoFromJson();

	public Item(double valorItem, TipoItem tipo, String idMoneda) {
		this.valorItem=valorItem;
		this.tipo=tipo;
		this.moneda = generadorDeMonedas.transformarAMoneda(idMoneda);
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
