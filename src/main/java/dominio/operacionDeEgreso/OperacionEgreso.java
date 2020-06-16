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
	//Seteamos valor de prueba
	static final int presupuestosRequeridos = 3; /*el numero de presupuestos requeridos va de [0; ...) */
	CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor;
	ValidadorEgresos validadorEgresos = new ValidadorEgresos(); //Quizás conviene hacer un SINGLETON
	
	public OperacionEgreso(Date fechaOp, List<Item> items, DocumentoComercial documentoComercial, 
										 Proveedor proveedor, MedioDePago medioDePago, List<Presupuesto> presupuestos, 
										 List<Usuario> revisores, CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor) {
		this.fechaOp = fechaOp;
		this.items = items;
		this.items.forEach(item -> item.asociarAEgreso());
		this.documentoComercial = documentoComercial;
		this.proveedor = proveedor;
		this.medioDePago = medioDePago;
		this.presupuestos=presupuestos;
		this.revisores = revisores;
		this.criterioDeSeleccionDeProveedor = criterioDeSeleccionDeProveedor;
		validadorEgresos.validarEgreso(this);
	}

	public void enviarMensajeARevisores(Mensaje mensaje) {
		revisores.forEach(revisor -> revisor.recibirMensaje(mensaje));
	}

	public boolean seEligioProveedorSegunCriterio() {
		return this.proveedorSegunCriterio().equals(proveedor);
	}

	private Proveedor proveedorSegunCriterio() {
		return criterioDeSeleccionDeProveedor.elegirSegunCriterio(presupuestos);
	}
	
	public boolean cumpleConLosPresupuestosRequeridos() {
		return presupuestos.size() == presupuestosRequeridos;
	}

	public boolean aplicaAlgunPresupuesto() {
		return this.presupuestos.stream().anyMatch(presupuesto -> presupuesto.contieneItems(items));
	}
	
	public double valorTotal() {
		return items.stream().mapToDouble(item -> item.getValorItem()).sum();
	}

	public int getPresupuestosRequeridos() {
		return presupuestosRequeridos;
	}
	
}
