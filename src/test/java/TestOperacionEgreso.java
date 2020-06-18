import dominio.criterioDeSeleccionDeProveedor.CriterioDeSeleccionDeProveedor;
import dominio.criterioDeSeleccionDeProveedor.MenorValor;
import dominio.documentoComercial.DocumentoComercial;
import dominio.item.Item;
import dominio.item.TipoItem;
import dominio.medioDePago.MedioDePago;
import dominio.medioDePago.TiposDePago;
import dominio.mensajes.BandejaDeMensajes;
import dominio.mensajes.Mensaje;
import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;
import dominio.usuario.Usuario;
import dominio.validacionEgresos.ValidacionAplicacionPresupuesto;
import dominio.validacionEgresos.ValidacionCantidadPresupuestos;
import dominio.validacionEgresos.ValidacionSeleccionProveedor;
import dominio.validacionEgresos.ValidadorEgresos;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TestOperacionEgreso {
	DocumentoComercial documento;
	List<Item> items = new ArrayList<Item>();
	List<Item> otrosItems = new ArrayList<Item>();
	List<Item> otrosItems2 = new ArrayList<Item>();
	Proveedor proveedor;
	Proveedor otroProveedor;
	OperacionEgreso operacion;
	MedioDePago medioDePago;
	List<Presupuesto> presupuestos = new ArrayList<>();
	List<Usuario> revisores = new ArrayList<>();
	CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor;

	List<DocumentoComercial> documentos; 
	BandejaDeMensajes bandejaDeMensajes = new BandejaDeMensajes();
	ValidadorEgresos validador = ValidadorEgresos.getInstance();
	Mensaje mensaje1;
	Mensaje mensaje2;
	Mensaje mensaje3;

	TipoItem carne = new TipoItem();
	TipoItem sopa = new TipoItem();
	TipoItem polenta = new TipoItem();
	
	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		validador.agregarValidacion(new ValidacionAplicacionPresupuesto());
		validador.agregarValidacion(new ValidacionCantidadPresupuestos());
		validador.agregarValidacion(new ValidacionSeleccionProveedor());
		documento = new DocumentoComercial("Factura", 5);
		proveedor = new Proveedor("Juan Peron","JDP",45678978,2045678889,1567);
		medioDePago = new MedioDePago(TiposDePago.TarjetaDeCredito, 1234567890);
		criterioDeSeleccionDeProveedor = new MenorValor();
		otrosItems.add(new Item (200,carne,null));
		otrosItems.add(new Item (200,sopa, null));
		medioDePago = new MedioDePago(TiposDePago.Efectivo, 200);
		criterioDeSeleccionDeProveedor=new MenorValor();
		revisores.add(new Usuario("Juan", "PasswordSegura",false, bandejaDeMensajes));
		
		operacion = new OperacionEgreso(new Date(2000,13,05), items, documento, 
				proveedor, medioDePago, presupuestos, revisores, criterioDeSeleccionDeProveedor, null);
		
		operacion.agregarItem(new Item (100,carne, null));
		operacion.agregarItem(new Item (100,sopa, null));
		
		
		operacion.agregarPresupuesto(new Presupuesto(documentos,otrosItems,otroProveedor,null));
		operacion.agregarPresupuesto(new Presupuesto(documentos,otrosItems,otroProveedor,null));
		
	}
	
	@Test
	public void testValorTotal() {
		operacion.agregarPresupuesto(new Presupuesto(documentos,items,proveedor,null));
		Assert.assertEquals(200,operacion.valorTotal(),0.0);
	}
	
	@Test
	public void testCompraCumpleConPresupuestosRequeridos() {
		operacion.agregarPresupuesto(new Presupuesto(documentos,items,proveedor,null));
		Assert.assertTrue(operacion.cumpleConLosPresupuestosRequeridos());
	}
	@Test
	public void testCompraEnBaseAPresupuesto() {
		operacion.agregarPresupuesto(new Presupuesto(documentos,items,proveedor,null));
		Assert.assertTrue(operacion.aplicaAlgunPresupuesto());
	}
	
	@Test
	public void testNoCompraCumpleConPresupuestosRequeridos() {
		operacion.agregarPresupuesto(new Presupuesto(documentos,items,proveedor,null));
		operacion.agregarPresupuesto(new Presupuesto(documentos,items,proveedor,null));
		Assert.assertFalse(operacion.cumpleConLosPresupuestosRequeridos());
	}
	@Test
	public void testNoCompraEnBaseAPresupuesto() {
		operacion.agregarPresupuesto(new Presupuesto(documentos,otrosItems,proveedor,null));
		operacion.agregarItem(new Item (300,polenta, null));
		Assert.assertFalse(operacion.aplicaAlgunPresupuesto());
	}
	
	@Test
	public void testValidarElDeMenorValor() {
		operacion.agregarPresupuesto(new Presupuesto(documentos,items,proveedor,null));
		Assert.assertTrue(operacion.seEligioProveedorSegunCriterio());
	}
	
	@Test
	public void testNoSeEligeElDeMenorValor() {
		operacion.agregarPresupuesto(new Presupuesto(documentos,items,proveedor,null));
		operacion.setProveedor(otroProveedor);
		Assert.assertFalse(operacion.seEligioProveedorSegunCriterio());
	}
	
	@Test
	public void testPasaTodasLasValidaciones() {
		operacion.agregarPresupuesto(new Presupuesto(documentos,items,proveedor,null));
		Assert.assertTrue(operacion.esValida());
	}
	
	@Test
	public void revisorRecibeTodosMensajesDeValidacionesPositivas() {
		operacion.agregarPresupuesto(new Presupuesto(documentos,items,proveedor,null));
		operacion.validarse();
		Assert.assertTrue(bandejaDeMensajes.tieneMensajeConEseContenido("Se está aplicando alguno de los presupuestos en la compra"));
		Assert.assertTrue(bandejaDeMensajes.tieneMensajeConEseContenido("Cantidad correcta de presupuestos cargados"));
		Assert.assertTrue(bandejaDeMensajes.tieneMensajeConEseContenido("Se ha seleccionado al proveedor correcto según el criterio elegido"));
	}
	
	@Test
	public void revisorRecibeMensajesDeValidacionNegativo() {
		operacion.agregarPresupuesto(new Presupuesto(documentos,items,proveedor,null));
		operacion.agregarPresupuesto(new Presupuesto(documentos,items,proveedor,null));
		operacion.validarse();
		Assert.assertTrue(bandejaDeMensajes.tieneMensajeConEseContenido("Cantidad incorrecta de presupuestos cargados"));
	}
}