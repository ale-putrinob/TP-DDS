package dominio.item;

import dominio.moneda.TipoMoneda;
import dominio.operacionDeEgreso.RepositorioEgresos;
import dominio.repositorioApiML.MerLibAPI;

public class Item {
	double valorItem;
	String tipo;
	TipoMoneda moneda;

	public Item(double valorItem, String tipo, String idMoneda) {
		this.valorItem=valorItem;
		this.tipo=tipo;
		this.moneda = MerLibAPI.getUnaMoneda(idMoneda);
	};
	
	public double getValorItem() {
		return this.valorItem;
	}
	
	public boolean estaAsociadoAEgreso() {
		return RepositorioEgresos.getInstance().getEgresos().stream().anyMatch(egreso->egreso.tieneItem(tipo));
	}

	public boolean tieneTipo(String tipoItem) {
		return tipo.equals(tipoItem);
	}
}
