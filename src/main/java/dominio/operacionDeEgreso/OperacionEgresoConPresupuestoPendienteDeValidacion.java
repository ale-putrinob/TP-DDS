package dominio.operacionDeEgreso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dominio.criterioDeSeleccionDeProveedor.CriterioDeSeleccionDeProveedor;
import dominio.documentoComercial.DocumentoComercial;
import dominio.item.Item;
import dominio.medioDePago.MedioDePago;
import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;

//A REVISAR
public class OperacionEgresoConPresupuestoPendienteDeValidacion {

	Date fechaOp = new Date();
	List<Item> items = new ArrayList<>();
	DocumentoComercial documentoComercial;
	Proveedor proveedor;
	MedioDePago medioDePago;
	List<Presupuesto> presupuestos = new ArrayList<>();
	CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor;
	
	void especificarFecha(Date fechaOp) {
		this.fechaOp = fechaOp;
	}
	
	void especificarItems(List<Item> items) {
		this.items = items;
	}
	
	void especificarDocumentoComercial(DocumentoComercial documentoComercial) {
		this.documentoComercial = documentoComercial;
	}
	
	void especificarProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	void especificarMedioDePago(MedioDePago medioDePago) {
		this.medioDePago = medioDePago;
	}
	
	void especificarPresupuestos(List<Presupuesto> presupuestos) {
		this.presupuestos = presupuestos;
	}
	
	void especificarCriterioDeSeleccionDeProveedor(CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor) {
		this.criterioDeSeleccionDeProveedor = criterioDeSeleccionDeProveedor;
	}
	
	OperacionEgresoConPresupuesto construir(){
		OperacionEgresoConPresupuesto operacionDeEgresoConPresupuesto = new OperacionEgresoConPresupuesto(fechaOp, items, 
				documentoComercial, proveedor, medioDePago, presupuestos, criterioDeSeleccionDeProveedor);
		
		return operacionDeEgresoConPresupuesto;
	}
	
}
