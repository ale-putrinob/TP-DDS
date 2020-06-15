package dominio.operacionDeEgreso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dominio.criterioDeSeleccionDeProveedor.CriterioDeSeleccionDeProveedor;
import dominio.documentoComercial.DocumentoComercial;
import dominio.excepcion.OperacionEgresoInvalidaException;
import dominio.item.Item;
import dominio.medioDePago.MedioDePago;
import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;
import dominio.usuario.Usuario;

public class OperacionEgreso{
	Date fechaOp = new Date();
	List<Item> items = new ArrayList<>();
	DocumentoComercial documentoComercial;
	Proveedor proveedor;
	MedioDePago medioDePago;
	List<Presupuesto> presupuestos = new ArrayList<>();
	Usuario revisor;
	//Seteamos valor de prueba
	static final int presupuestosRequeridos = 3; /*el numero de presupuestos requeridos va de [0; ...) */
	CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor;
	
	public OperacionEgreso(Date fechaOp, List<Item> items, DocumentoComercial documentoComercial, 
										 Proveedor proveedor, MedioDePago medioDePago, List<Presupuesto> presupuestos, 
										 Usuario revisor, CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor) {
		this.fechaOp = fechaOp;
		this.items = items;
		this.items.forEach(item -> item.asociarAEgreso());
		this.documentoComercial = documentoComercial;
		this.proveedor = proveedor;
		this.medioDePago = medioDePago;
		this.presupuestos=presupuestos;
		this.revisor = revisor;
		this.criterioDeSeleccionDeProveedor = criterioDeSeleccionDeProveedor;
		this.validarEgresoConPresupuesto();
	}
	
	public void validarEgresoConPresupuesto() {
		this.validarCantidadDePresupuestos();
		this.validarAplicacionDePresupuesto();
		this.validarSeleccionDeProveedor();
		
	}
	
	private void validarSeleccionDeProveedor() {
		if(!(this.seEligioProveedorSegunCriterio())) {
			throw new OperacionEgresoInvalidaException("No se respeta el criterio seleccionado para elegir al proveedor!");
		}

	}

	private boolean seEligioProveedorSegunCriterio() {
		return this.proveedorSegunCriterio().equals(proveedor);
	}

	//Revisar Si es mejor que sea el criterio el que haga el get del proveedor
	private Proveedor proveedorSegunCriterio() {
		return criterioDeSeleccionDeProveedor.elegirSegunCriterio(presupuestos);
	}

	private void validarAplicacionDePresupuesto() {
		if(this.aplicaAlgunPresupuesto()) //Agregar condicion para verificar que se aplica alguno de los Presupuestos
			throw new OperacionEgresoInvalidaException("No se aplica ninguno de los presupuestos en la compra!");
	}

	private void validarCantidadDePresupuestos() {
		if(presupuestos.size() != presupuestosRequeridos) 
			throw new OperacionEgresoInvalidaException("Cantidad incorrecta de presupuestos");
	}

	private boolean aplicaAlgunPresupuesto() {
		return this.presupuestos.stream().anyMatch(presupuesto -> presupuesto.contieneItems(items));
	}
	
	public double valorTotal() {
		return items.stream().mapToDouble(item -> item.getValorItem()).sum();
	}

	public int getPresupuestosRequeridos() {
		return presupuestosRequeridos;
	}
	
}
