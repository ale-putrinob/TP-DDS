package dominio;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class OperacionEgreso {
	Date fechaOp = new Date();
	List<Item> items = new ArrayList<>();
	DocumentoComercial documentoComercial;
	Proveedor proveedor;
	MedioDePago medioDePago;
	
	public OperacionEgreso(Date fechaOp, List<Item> items,DocumentoComercial documentoComercial,Proveedor proveedor,
						   MedioDePago medioDePago) {
		this.fechaOp = fechaOp;
		this.items = items;
		this.documentoComercial = documentoComercial;
		this.proveedor = proveedor;
		this.medioDePago = medioDePago;
	}
	
	public double valorTotal() {
		return items.stream().mapToDouble(item -> item.getValorItem()).sum();
	}

	public void pagar(){
		this.medioDePago.pagar(this);
	}
}
