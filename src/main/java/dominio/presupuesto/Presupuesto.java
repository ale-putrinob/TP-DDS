package dominio.presupuesto;

import dominio.documentoComercial.DocumentoComercial;
import dominio.excepcion.PresupuestoException;
import dominio.item.Item;
import dominio.moneda.TipoMoneda;
import dominio.persistentEntity.PersistentEntity;
import dominio.proveedor.Proveedor;
import dominio.repositorioApiML.MerLibAPI;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Presupuesto extends PersistentEntity{
	
	@OneToMany
	@JoinColumn(name = "presupuesto_id")
	List<DocumentoComercial> documentosComerciales = new ArrayList<>();
	@ManyToMany
	List<Item> items = new ArrayList<>();
	@ManyToOne
	Proveedor proveedor;
	@ManyToOne
	TipoMoneda moneda;

	public Presupuesto(List<DocumentoComercial> documentosComerciales, List<Item> items, Proveedor proveedor,
						String idMoneda) {
		//this.validarItemsAsignadosAEgreso(items);
		this.documentosComerciales = documentosComerciales;
		this.items = items;
		this.proveedor=proveedor;
		if(idMoneda != null)
			this.moneda = MerLibAPI.getUnaMoneda(idMoneda);
	}
	
	public Presupuesto() {};
	
	public void validarItemsAsignadosAEgreso(List<Item> items) {
		if(!(items.stream().allMatch(item->item.estaAsociadoAEgreso()))) {
			throw new PresupuestoException("Alguno de los Items Cargados no están asociados a un egreso");
		}
	}
	
	public double presupuestoTotal() {
		return items.stream().mapToDouble(item -> item.getValorItem()).sum();
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public boolean contieneItems (List<Item> otrosItems){
		/*aca se verifica que las listas contengan los mismo items, y que sean del mismo tama�o, para que los presupuestos sean iguales*/
		return this.items.containsAll(otrosItems) && this.items.size()==otrosItems.size();
	}
	
	public void agregarItem(Item item) {
		items.add(item);
	}
	
	public void agregarDocComercial(DocumentoComercial doc) {
		documentosComerciales.add(doc);
	}

	public void setProveedor(Proveedor prov) {
		this.proveedor = prov;
	}
	
}
