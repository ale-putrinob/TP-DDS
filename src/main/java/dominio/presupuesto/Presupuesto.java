package dominio.presupuesto;

import java.util.ArrayList;
import java.util.List;

import dominio.documentoComercial.DocumentoComercial;
import dominio.excepcion.PresupuestoException;
import dominio.item.Item;
import dominio.proveedor.Proveedor;

public class Presupuesto {
	List<DocumentoComercial> documentosComerciales = new ArrayList<>();
	List<Item> items = new ArrayList<>();
	Proveedor proveedor;
	
	public Presupuesto(List<DocumentoComercial> documentosComerciales, List<Item> items) {
		this.validarItemsAsignadosAEgreso(items);
		
		this.documentosComerciales = documentosComerciales;
		this.items = items;
	}
	
	public void validarItemsAsignadosAEgreso(List<Item> items) {
		if(!(items.stream().allMatch(item->item.estaAsociadoAEgreso()))) {
			throw new PresupuestoException("Alguno de los Items Cargados no estÃ¡n asociados a un egreso");
		}
	}
	
	public double presupuestoTotal() {
		return items.stream().mapToDouble(item -> item.getValorItem()).sum();
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public boolean contieneItems (List<Item> items1){
		/*aca se verifica que las listas contengan los mismo items, y que sean del mismo tamaño, para que los presupuestos sean iguales*/
		return this.items.containsAll(items1) && this.items.size()==items1.size();
	}
	
}
