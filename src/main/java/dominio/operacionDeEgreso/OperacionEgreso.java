package dominio.operacionDeEgreso;

import dominio.criterioDeSeleccionDeProveedor.CriterioDeSeleccionDeProveedor;
import dominio.documentoComercial.DocumentoComercial;
import dominio.item.Item;
import dominio.medioDePago.MedioDePago;
import dominio.mensajes.Mensaje;
import dominio.moneda.TipoMoneda;
import dominio.organizacion.Entidad;
import dominio.persistentEntity.PersistentEntity;
import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;
import dominio.repositorioApiML.MerLibAPI;
import dominio.usuario.Usuario;
import dominio.validacionEgresos.ValidadorEgresos;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class OperacionEgreso extends PersistentEntity{
	
	@ElementCollection
	List<String> etiquetas = new ArrayList<>(); 
	Date fecha = new Date();
	@ManyToMany(cascade = {CascadeType.PERSIST})
	List<Item> items = new ArrayList<>();
	// moneda va a tener todos los campos del JSON
	@ManyToOne(cascade = {CascadeType.PERSIST})
	TipoMoneda moneda;
	@OneToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "documentoComercial_id")
	DocumentoComercial documentoComercial;
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	Proveedor proveedor;
	@ManyToOne(cascade = {CascadeType.PERSIST})
	MedioDePago medioDePago;
	@OneToMany
	@JoinColumn(name = "operacionEgreso_id")
	List<Presupuesto> presupuestos = new ArrayList<>();
	@ManyToMany
	List<Usuario> revisores;
	@Enumerated(EnumType.STRING)
	CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor;
	@Enumerated(EnumType.STRING)
	EstadoEgreso estado = EstadoEgreso.SIN_VALIDAR;
	@ElementCollection
	List<String> resultadosDeValidaciones = new ArrayList<>();
	@Transient
	ValidadorEgresos validador = new ValidadorEgresos();
	@ManyToOne(cascade = CascadeType.MERGE)
	Entidad entidad;
	
	// Seteamos valor de prueba
	static final int presupuestosRequeridos = 1; /* el numero de presupuestos requeridos va de [0; ...) */

	public OperacionEgreso(List<String> etiquetas ,Date fechaOp, List<Item> items,  DocumentoComercial documentoComercial, 
			Proveedor proveedor, MedioDePago medioDePago, List<Presupuesto> presupuestos, List<Usuario> revisores,
			CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor, String idMoneda,Entidad entidad) {
		this.etiquetas = etiquetas;
		this.fecha = fechaOp;
		this.items = items;
		this.documentoComercial = documentoComercial;
		this.proveedor = proveedor;
		this.medioDePago = medioDePago;
		this.presupuestos = presupuestos;
		this.revisores = revisores;
		this.criterioDeSeleccionDeProveedor = criterioDeSeleccionDeProveedor;
		this.moneda = MerLibAPI.getUnaMoneda(idMoneda);
		if (entidad != null) {
			entidad.validarAgregarEgreso();
			}
		this.entidad = entidad;
	}
	
	public OperacionEgreso() {};

	public void validarse() {
		validador.validarEgreso(this);
		this.actualizarEstado();
		System.out.println("LLEGUE AC� ----------------------------------------------" + estado);
		RepositorioEgresos.getInstance().actualizarEgreso(this);
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
		if((this.proveedorSegunCriterio() == null || presupuestos.isEmpty()))
			return false;
		else
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
	
	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}
	

	@SuppressWarnings("deprecation")
	public boolean esDelMes(int mes, int anio) {
		return (fecha.getMonth() == mes) && (fecha.getYear() == anio);
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

	public List<Item> getItems() {
		return items;
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public List<String> getEtiquetas() { return etiquetas; }

	public Date getFecha() { return fecha; }

	public TipoMoneda getMoneda() { return moneda; }

	public DocumentoComercial getDocumentoComercial() { return documentoComercial; }

	public Proveedor getProveedor() { return proveedor; }

	public MedioDePago getMedioDePago() { return medioDePago; }

	public List<Presupuesto> getPresupuestos() { return presupuestos; }

	public List<Usuario> getRevisores() { return revisores; }

	public CriterioDeSeleccionDeProveedor getCriterioDeSeleccionDeProveedor() { return criterioDeSeleccionDeProveedor; }

	public EstadoEgreso getEstado() { return estado; }

	public List<String> getResultadosDeValidaciones() { return resultadosDeValidaciones; }

	public ValidadorEgresos getValidador() { return validador; }
}
