package dominio.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import dominio.moneda.TipoMoneda;
import dominio.operacionDeEgreso.RepositorioEgresos;
import dominio.repositorioApiML.MerLibAPI;

@Entity
public class Item {
	
	@Id
	@GeneratedValue
	private Long id;
	
	double valorItem;
	String tipo;
	@ManyToOne
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
	public Long getId() {
		return id;
	}
	public String getTipo() {
		return tipo;
	}
}
