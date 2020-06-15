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

public class OperacionEgresoConPresupuesto extends OperacionEgreso{
	List<Presupuesto> presupuestos = new ArrayList<>();
	static final int presupuestosRequeridos = 3;
	CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor;
	
	public OperacionEgresoConPresupuesto(Date fechaOp, List<Item> items, DocumentoComercial documentoComercial,
			Proveedor proveedor, MedioDePago medioDePago, List<Presupuesto> presupuestos, CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor) {
		super(fechaOp, items, documentoComercial, proveedor, medioDePago);
		this.presupuestos=presupuestos;
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
			throw new OperacionEgresoInvalidaException("¡No se respetó el criterio seleccionado para elegir al proveedor!");
		}
		
	}

	private boolean seEligioProveedorSegunCriterio() {
		return this.proveedorSegunCriterio().equals(proveedor);
	}

	//Revisar Si es mejor que sea el criterio el que haga el get del proveedor
	private Proveedor proveedorSegunCriterio() {
		return criterioDeSeleccionDeProveedor.elegirSegunCriterio(presupuestos).getProveedor();
	}

	private void validarAplicacionDePresupuesto() {
		if(false) //Agregar Condición para Verificar que se Aplicó alguno de los Presupuestos 
			throw new OperacionEgresoInvalidaException("¡No se aplicó ninguno de los presupuestos en la compra!");
	}

	private void validarCantidadDePresupuestos() {
		if(presupuestos.size() != presupuestosRequeridos) 
			throw new OperacionEgresoInvalidaException("¡Faltan cargar presupuestos!");
	}


	public int getPresupuestosRequeridos() {
		return presupuestosRequeridos;
	}
	
}
