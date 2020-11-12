package dominio.item;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import dominio.moneda.TipoMoneda;
import dominio.operacionDeEgreso.RepositorioEgresos;
import dominio.persistentEntity.PersistentEntity;
import dominio.repositorioApiML.MerLibAPI;

@Entity
public class Item extends PersistentEntity{
		
	double valorItem;
	String tipo;
	@ManyToOne
	TipoMoneda moneda;

	public Item(double valorItem, String tipo, String idMoneda) {
		this.valorItem=valorItem;
		this.tipo=tipo;
		if(idMoneda != null)
			this.moneda = MerLibAPI.getUnaMoneda(idMoneda);
	};
	
	public Item() {}
	
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
