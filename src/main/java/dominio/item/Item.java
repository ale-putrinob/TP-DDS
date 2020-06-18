package dominio.item;

import com.google.gson.Gson;
import dominio.moneda.TipoMoneda;
import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.repositorioApiML.ClienteRepositorio;

public class Item {
	double valorItem;
	TipoItem tipo;
	TipoMoneda moneda;
	String json;

	public Item(double valorItem, TipoItem tipo, String idMoneda) {
		Gson gson = new Gson();
		this.valorItem=valorItem;
		this.tipo=tipo;
		this.json = ClienteRepositorio.getUnaMoneda(idMoneda);
		this.moneda = gson.fromJson(this.json, TipoMoneda.class);
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
