package dominio.operacionDeEgreso;

import com.google.gson.Gson;
import dominio.criterioDeSeleccionDeProveedor.CriterioDeSeleccionDeProveedor;
import dominio.documentoComercial.DocumentoComercial;
import dominio.item.Item;
import dominio.medioDePago.MedioDePago;
import dominio.mensajes.Mensaje;
import dominio.moneda.TipoMoneda;
import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;
import dominio.repositorioApiML.ClienteRepositorio;
import dominio.usuario.Usuario;
import dominio.validacionEgresos.ValidadorEgresos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperacionEgreso {
	Date fechaOp = new Date();
	List<Item> items = new ArrayList<>();
	//moneda va a tener todos los campos del JSON
	TipoMoneda moneda;
	DocumentoComercial documentoComercial;
	Proveedor proveedor;
	MedioDePago medioDePago;
	List<Presupuesto> presupuestos = new ArrayList<>();
	List<Usuario> revisores;
	CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor;
	Gson gson = new Gson();
	String json;
	// Seteamos valor de prueba
	static final int presupuestosRequeridos = 3; /* el numero de presupuestos requeridos va de [0; ...) */

	public OperacionEgreso(Date fechaOp, List<Item> items,  DocumentoComercial documentoComercial, Proveedor proveedor,
			MedioDePago medioDePago, List<Presupuesto> presupuestos, List<Usuario> revisores,
			CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor, String idMoneda) {

		this.fechaOp = fechaOp;
		this.items = items;
		this.items.forEach(item -> item.asociarAEgreso(this));
		this.documentoComercial = documentoComercial;
		this.proveedor = proveedor;
		this.medioDePago = medioDePago;
		this.presupuestos = presupuestos;
		this.revisores = revisores;
		this.criterioDeSeleccionDeProveedor = criterioDeSeleccionDeProveedor;
		this.json = ClienteRepositorio.getUnaMoneda(idMoneda);
		this.moneda = gson.fromJson(this.json, TipoMoneda.class);

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
		return this.proveedorSegunCriterio().equals(proveedor);
	}

	private Proveedor proveedorSegunCriterio() {
		return this.criterioDeSeleccionDeProveedor.elegirSegunCriterio(presupuestos);
	}

	public boolean cumpleConLosPresupuestosRequeridos() {
		return this.presupuestos.size() == presupuestosRequeridos;
	}

	public boolean aplicaAlgunPresupuesto() {
		return this.presupuestos.stream().anyMatch(presupuesto -> presupuesto.contieneItems(items));
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
