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
	//Seteamos valor de prueba
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
			throw new OperacionEgresoInvalidaException("�No se respet� el criterio seleccionado para elegir al proveedor!");
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
		if(this.aplicaAlgunPresupuesto()) //Agregar Condici�n para Verificar que se Aplic� alguno de los Presupuestos
			throw new OperacionEgresoInvalidaException("�No se aplic� ninguno de los presupuestos en la compra!");
	}

	private void validarCantidadDePresupuestos() {
		if(presupuestos.size() != presupuestosRequeridos) 
			throw new OperacionEgresoInvalidaException("�Faltan cargar presupuestos!");
	}

	private boolean aplicaAlgunPresupuesto() {
		return this.presupuestos.stream().anyMatch(presupuesto -> presupuesto.contieneItems(items));
	}

	public int getPresupuestosRequeridos() {
		return presupuestosRequeridos;
	}
	
}
