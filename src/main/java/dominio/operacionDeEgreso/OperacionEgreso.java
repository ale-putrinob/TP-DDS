package dominio.operacionDeEgreso;

import dominio.criterioDeSeleccionDeProveedor.CriterioDeSeleccionDeProveedor;
import dominio.documentoComercial.DocumentoComercial;
import dominio.item.Item;
import dominio.medioDePago.MedioDePago;
import dominio.mensajes.Mensaje;
import dominio.moneda.TipoMoneda;
import dominio.organizacion.Entidad;
import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;
import dominio.repositorioApiML.MerLibAPI;
import dominio.usuario.Usuario;
import dominio.validacionEgresos.ValidadorEgresos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperacionEgreso {
	List<Etiqueta> etiquetas = new ArrayList<>(); 
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
	EstadoEgreso estado = EstadoEgreso.SIN_VALIDAR;
	List<String> resultadosDeValidaciones = new ArrayList<>();
	ValidadorEgresos validador = new ValidadorEgresos();
	Entidad entidad;
	
	// Seteamos valor de prueba
	static final int presupuestosRequeridos = 3; /* el numero de presupuestos requeridos va de [0; ...) */

	public OperacionEgreso(List<Etiqueta> etiquetas ,Date fechaOp, List<Item> items,  DocumentoComercial documentoComercial, 
			Proveedor proveedor, MedioDePago medioDePago, List<Presupuesto> presupuestos, List<Usuario> revisores,
			CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor, String idMoneda,Entidad entidad) {
		this.etiquetas = etiquetas;
		this.fechaOp = fechaOp;
		this.items = items;
		this.items.forEach(item -> item.asociarAEgreso(this));
		this.documentoComercial = documentoComercial;
		this.proveedor = proveedor;
		this.medioDePago = medioDePago;
		this.presupuestos = presupuestos;
		this.revisores = revisores;
		this.criterioDeSeleccionDeProveedor = criterioDeSeleccionDeProveedor;
		this.moneda = MerLibAPI.getUnaMoneda(idMoneda);
		this.entidad = entidad;
	}

	public void validarse() {
		validador.validarEgreso(this);
		this.actualizarEstado();
	}

	private void actualizarEstado() {
		if(validador.pasaTodasLasValidaciones(this)) estado = EstadoEgreso.VALIDO;
		else estado = EstadoEgreso.INVALIDO;
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
		return this.estado == EstadoEgreso.VALIDO;
	}

	public boolean estaPendienteDeValidacion() {
		return this.estado == EstadoEgreso.SIN_VALIDAR;
	}

	public void cargarResultadoDeValidacion(String resultado) {
		resultadosDeValidaciones.add(resultado);
	}
	
	public boolean tieneEtiqueta(Etiqueta etiqueta) {
		return etiquetas.contains(etiqueta);
	}
	
	public Entidad getEntidad() {
		return entidad;
	}

}
