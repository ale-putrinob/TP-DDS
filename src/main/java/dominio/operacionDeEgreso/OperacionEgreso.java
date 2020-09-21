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

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class OperacionEgreso {
	
	@Id
	@GeneratedValue
	private Long operacionEgreso_id;
	
	/*agregar despues*/
	@Transient
	List<String> etiquetas = new ArrayList<>(); 
	Date fechaOp = new Date();
	@ManyToMany
	List<Item> items = new ArrayList<>();
	// moneda va a tener todos los campos del JSON
	@ManyToOne
	TipoMoneda moneda;
	@OneToOne
	@JoinColumn(name = "documentoComercial_id")
	DocumentoComercial documentoComercial;
	@ManyToOne
	Proveedor proveedor;
	@ManyToOne
	MedioDePago medioDePago;
	@OneToMany
	@JoinColumn(name = "operacionEgreso_id")
	List<Presupuesto> presupuestos = new ArrayList<>();
	@ManyToMany
	List<Usuario> revisores;
	@Transient
	CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor;
	@Enumerated
	EstadoEgreso estado = EstadoEgreso.SIN_VALIDAR;
	@Transient
	List<String> resultadosDeValidaciones = new ArrayList<>();
	@Transient
	ValidadorEgresos validador = new ValidadorEgresos();
	/*arreglar lo de la entidad y su herencia*/
	@Transient
	Entidad entidad;
	
	// Seteamos valor de prueba
	static final int presupuestosRequeridos = 3; /* el numero de presupuestos requeridos va de [0; ...) */

	public OperacionEgreso(List<String> etiquetas ,Date fechaOp, List<Item> items,  DocumentoComercial documentoComercial, 
			Proveedor proveedor, MedioDePago medioDePago, List<Presupuesto> presupuestos, List<Usuario> revisores,
			CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor, String idMoneda,Entidad entidad) {
		this.etiquetas = etiquetas;
		this.fechaOp = fechaOp;
		this.items = items;
		this.documentoComercial = documentoComercial;
		this.proveedor = proveedor;
		this.medioDePago = medioDePago;
		this.presupuestos = presupuestos;
		this.revisores = revisores;
		this.criterioDeSeleccionDeProveedor = criterioDeSeleccionDeProveedor;
		this.moneda = MerLibAPI.getUnaMoneda(idMoneda);
		entidad.validarAgregarEgreso();
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
		return this.estado.equals(EstadoEgreso.VALIDO);
	}
	
	public boolean esInvalida() {
		return this.estado == EstadoEgreso.INVALIDO;
	}

	public boolean estaPendienteDeValidacion() {
		return this.estado == EstadoEgreso.SIN_VALIDAR;
	}

	public void cargarResultadoDeValidacion(String resultado) {
		resultadosDeValidaciones.add(resultado);
	}
	
	public boolean tieneEtiqueta(String etiqueta) {
		return etiquetas.contains(etiqueta);
	}
	
	public Entidad getEntidad() {
		return entidad;
	}

	@SuppressWarnings("deprecation")
	public boolean esDelMes(int mes, int anio) {
		return (fechaOp.getMonth() == mes) && (fechaOp.getYear() == anio);
	}

	public void setValidador(ValidadorEgresos validador) {
		this.validador = validador;
	}

	public boolean tieneItem(String tipoItem) {
		return items.stream().anyMatch(item -> item.tieneTipo(tipoItem));
	}

	public void agregarItem(Item item) {
		items.add(item);
	}
}
