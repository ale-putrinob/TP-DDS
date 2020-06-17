package dominio.operacionDeEgreso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dominio.criterioDeSeleccionDeProveedor.CriterioDeSeleccionDeProveedor;
import dominio.documentoComercial.DocumentoComercial;
import dominio.excepcion.OperacionEgresoInvalidaException;
import dominio.item.Item;
import dominio.medioDePago.MedioDePago;
import dominio.mensajes.Mensaje;
import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;
import dominio.usuario.Usuario;
import dominio.validacionEgresos.ValidadorEgresos;

public class OperacionEgreso{
	Date fechaOp = new Date();
	List<Item> items = new ArrayList<>();
	DocumentoComercial documentoComercial;
	Proveedor proveedor;
	MedioDePago medioDePago;
	List<Presupuesto> presupuestos = new ArrayList<>();
	List<Usuario> revisores;
	CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor;
	//Seteamos valor de prueba
	static final int presupuestosRequeridos = 3; /*el numero de presupuestos requeridos va de [0; ...) */
	
	
	public OperacionEgreso(Date fechaOp, List<Item> items, DocumentoComercial documentoComercial, 
										 Proveedor proveedor, MedioDePago medioDePago, List<Presupuesto> presupuestos, 
										 List<Usuario> revisores, CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor) {
		this.fechaOp = fechaOp;
		this.items = items;
		this.items.forEach(item -> item.asociarAEgreso(this));
		this.documentoComercial = documentoComercial;
		this.proveedor = proveedor;
		this.medioDePago = medioDePago;
		this.presupuestos=presupuestos;
		this.revisores = revisores;
		this.criterioDeSeleccionDeProveedor = criterioDeSeleccionDeProveedor;
	}
	
	public void validarse() {
		ValidadorEgresos.getInstance().validarEgreso(this);
	}
	
	public void agregarItem(Item item) {
		this.items.add(item);
		item.asociarAEgreso(this);
	}
	
	public void quitarItem(Item item) {
		this.items.remove(item);
		item.desasociarDeEgreso(this);
	}
	
	public void agregarPresupuesto(Presupuesto presupuesto) {
		this.presupuestos.add(presupuesto);
	}
	
	public void quitarPresupuesto(Presupuesto presupuesto) {
		this.presupuestos.remove(presupuesto);
	}
	
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public void enviarMensajeARevisores(Mensaje mensaje) {
		this.revisores.forEach(revisor -> revisor.recibirMensaje(mensaje));
	}

	public boolean seEligioProveedorSegunCriterio() {
		if(presupuestosRequeridos > 0)
			return this.proveedorSegunCriterio().equals(proveedor);
		else
			return true;
	}

	private Proveedor proveedorSegunCriterio() {
		return this.criterioDeSeleccionDeProveedor.elegirSegunCriterio(presupuestos);
	}
	
	public boolean cumpleConLosPresupuestosRequeridos() {
		return this.presupuestos.size() == presupuestosRequeridos;
	}

	public boolean aplicaAlgunPresupuesto() {
		if(presupuestosRequeridos > 0)
			return this.presupuestos.stream().anyMatch(presupuesto -> presupuesto.contieneItems(items));
		else
			return true;
	}
	
	public double valorTotal() {
		return this.items.stream().mapToDouble(item -> item.getValorItem()).sum();
	}

	public int getPresupuestosRequeridos() {
		return presupuestosRequeridos;
	}
	
	public boolean esValida() {
		return ValidadorEgresos.getInstance().pasaTodasLasValidaciones(this);
		
	}
	
}
