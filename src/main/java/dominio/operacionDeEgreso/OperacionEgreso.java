package dominio.operacionDeEgreso;

import java.util.List;

import dominio.documentoComercial.DocumentoComercial;
import dominio.item.Item;
import dominio.medioDePago.MedioDePago;
import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;

import java.util.ArrayList;
import java.util.Date;

public class OperacionEgreso {
	Date fechaOp = new Date();
	List<Item> items = new ArrayList<>();
	DocumentoComercial documentoComercial;
	Proveedor proveedor;
	MedioDePago medioDePago;
	List<Presupuesto> presupuestos = new ArrayList<>();
	//A REVISAR
	static int presupuestosRequeridos;
	
	public OperacionEgreso(Date fechaOp, List<Item> items,DocumentoComercial documentoComercial,Proveedor proveedor,
						   MedioDePago medioDePago) {
		this.fechaOp = fechaOp;
		this.items = items;
		this.items.forEach(item -> item.asociarAEgreso());
		this.documentoComercial = documentoComercial;
		this.proveedor = proveedor;
		this.medioDePago = medioDePago;
	}
	
	//A REVISAR
	public static void setPresupuestosRequeridos(int cantidadPresupuestos) {
		presupuestosRequeridos = cantidadPresupuestos;
	}
	
	public double valorTotal() {
		return items.stream().mapToDouble(item -> item.getValorItem()).sum();
	}

}
