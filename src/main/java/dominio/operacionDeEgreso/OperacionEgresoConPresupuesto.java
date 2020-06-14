package dominio.operacionDeEgreso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dominio.documentoComercial.DocumentoComercial;
import dominio.item.Item;
import dominio.medioDePago.MedioDePago;
import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;

public class OperacionEgresoConPresupuesto extends OperacionEgreso{
	List<Presupuesto> presupuestos = new ArrayList<>();
	static int presupuestosRequeridos;
	
	public OperacionEgresoConPresupuesto(Date fechaOp, List<Item> items, DocumentoComercial documentoComercial,
			Proveedor proveedor, MedioDePago medioDePago, List<Presupuesto> presupuestos) {
		super(fechaOp, items, documentoComercial, proveedor, medioDePago);
		this.presupuestos=presupuestos;
	}

	public static void setPresupuestosRequeridos(int cantidadPresupuestos) {
		presupuestosRequeridos = cantidadPresupuestos;
	}
	
	public int getCantPresupuestos() {
		return presupuestosRequeridos;
	}
	
}
