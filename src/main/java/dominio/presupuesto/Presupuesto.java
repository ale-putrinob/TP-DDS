package dominio.presupuesto;

import dominio.documentoComercial.DocumentoComercial;
import dominio.excepcion.PresupuestoException;
import dominio.item.Item;
import dominio.moneda.GeneradorDeMonedasFromJson;
import dominio.moneda.TipoMoneda;
import dominio.proveedor.Proveedor;
import java.util.ArrayList;
import java.util.List;

public class Presupuesto {
	List<DocumentoComercial> documentosComerciales = new ArrayList<>();
	List<Item> items = new ArrayList<>();
	Proveedor proveedor;
	TipoMoneda moneda;
	GeneradorDeMonedasFromJson generadorDeMonedas = new GeneradorDeMonedasFromJson();

	public Presupuesto(List<DocumentoComercial> documentosComerciales, List<Item> items, Proveedor proveedor,
						String idMoneda) {
		this.validarItemsAsignadosAEgreso(items);
		this.documentosComerciales = documentosComerciales;
		this.items = items;
		this.proveedor=proveedor;
		this.moneda = generadorDeMonedas.transformarAMoneda(idMoneda);
	}
	
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
	
}
