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
		this.validarEgreso();
	}
	
	public void validarEgreso() {
		this.validarCantidadDePresupuestos();
		this.validarAplicacionDePresupuesto();
		this.validarSeleccionDeProveedor();
	}
	
	private void validarSeleccionDeProveedor() {
		this.validar(this.seEligioProveedorSegunCriterio(), "No se respeta el criterio seleccionado para elegir al proveedor!",
				"Se ha seleccionado al proveedor correcto según el criterio elegido");
	}
	
	private void validar(boolean condicion, String mensajeNegativo, String mensajePositivo) {
		if(condicion) {
			this.enviarMensajeARevisores(new Mensaje(mensajePositivo, this));
		}
		else {
			this.enviarMensajeARevisores(new Mensaje(mensajeNegativo, this));
		}
	}

	private void enviarMensajeARevisores(Mensaje mensaje) {
		revisores.forEach(revisor -> revisor.recibirMensaje(mensaje));
	}

	private boolean seEligioProveedorSegunCriterio() {
		return this.proveedorSegunCriterio().equals(proveedor);
	}

	private Proveedor proveedorSegunCriterio() {
		return criterioDeSeleccionDeProveedor.elegirSegunCriterio(presupuestos);
	}

	private void validarAplicacionDePresupuesto() {
		this.validar(this.aplicaAlgunPresupuesto(), "¡No se aplica ninguno de los presupuestos en la compra!",
				"Se está aplicando alguno de los presupuestos en la compra");
	}

	private void validarCantidadDePresupuestos() {
		this.validar(presupuestos.size() == presupuestosRequeridos, "Cantidad incorrecta de presupuestos",
				"Cantidad correcta de presupuestos cargados");
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
