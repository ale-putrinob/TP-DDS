package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import dominio.categoriaEntidad.CategoriaEntidad;
import dominio.criterioDeSeleccionDeProveedor.CriterioDeSeleccionDeProveedor;
import dominio.documentoComercial.DocumentoComercial;
import dominio.item.Item;
import dominio.medioDePago.MedioDePago;
import dominio.medioDePago.TiposDePago;
import dominio.mensajes.Mensaje;
import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.organizacion.Entidad;
import dominio.organizacion.EntidadJuridica;
import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;
import dominio.usuario.Usuario;
import dominio.validacionEgresos.ValidacionAplicacionPresupuesto;
import dominio.validacionEgresos.ValidacionCantidadPresupuestos;
import dominio.validacionEgresos.ValidacionSeleccionProveedor;
import dominio.validacionEgresos.ValidadorEgresos;

public class TestPersistenciaOperacionEgreso extends AbstractPersistenceTest implements WithGlobalEntityManager {
	List<String> etiquetas;
	DocumentoComercial documento;
	List<Item> items = new ArrayList<Item>();
	List<Item> otrosItems = new ArrayList<Item>();
	Proveedor proveedor;
	Proveedor otroProveedor;
	OperacionEgreso operacion, otraOperacion;
	MedioDePago medioDePago;
	List<Presupuesto> presupuestos = new ArrayList<>();
	List<Usuario> revisores = new ArrayList<>();
	CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor;

	List<DocumentoComercial> documentos; 
	List<Mensaje> bandejaDeMensajes = new ArrayList<>();
	ValidadorEgresos validador = new ValidadorEgresos();
	Mensaje mensaje1;
	Mensaje mensaje2;
	Mensaje mensaje3;
	
	Entidad entidad;
	
	Usuario juan;
	
	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		validador.agregarValidacion(new ValidacionAplicacionPresupuesto());
		validador.agregarValidacion(new ValidacionCantidadPresupuestos());
		validador.agregarValidacion(new ValidacionSeleccionProveedor());
		etiquetas = new ArrayList<>();
		documento = new DocumentoComercial("Factura", 5);
		proveedor = new Proveedor("Juan Peron","JDP",45678978,2045678889,"1567","Evita", 31, 2, 'A');
		medioDePago = new MedioDePago(TiposDePago.TarjetaDeCredito, 1234567890);
		criterioDeSeleccionDeProveedor = CriterioDeSeleccionDeProveedor.MENOR_VALOR;
		medioDePago = new MedioDePago(TiposDePago.Efectivo, 200);
		criterioDeSeleccionDeProveedor = CriterioDeSeleccionDeProveedor.MENOR_VALOR;
		juan = new Usuario("Juan", "PasswordSegura",false, bandejaDeMensajes);
		revisores.add(juan);
		etiquetas.add("AMOBLAMIENTO");
		CategoriaEntidad categoria = new CategoriaEntidad("BlackFreedom");
		entidad = new EntidadJuridica(null, null, 0, null, 0, null, categoria);
		operacion = new OperacionEgreso(etiquetas, new Date(2000,13,05), items, documento, 
				proveedor, medioDePago, presupuestos, revisores, criterioDeSeleccionDeProveedor, null, entidad);
		
		operacion.agregarItem(new Item (100,"carne", null));
		operacion.agregarItem(new Item (100,"sopa", null));
		
		otraOperacion = new OperacionEgreso(etiquetas, new Date(2000,13,05), otrosItems, documento, 
				proveedor, medioDePago, presupuestos, revisores, criterioDeSeleccionDeProveedor, null, entidad);
		
		otraOperacion.agregarItem(new Item (200,"carne premium",null));
		otraOperacion.agregarItem(new Item (200,"sopa premium", null));
		
		entityManager().persist(operacion);
		entityManager().persist(otraOperacion);
		
		operacion.agregarPresupuesto(new Presupuesto(documentos,otrosItems,otroProveedor,null));
		operacion.agregarPresupuesto(new Presupuesto(documentos,otrosItems,otroProveedor,null));
		
	}
	
	@After
	public void cleanup() {
		entityManager().remove(operacion);
		entityManager().remove(otraOperacion);
	}
	
	@Test
	public void TestOperacionEgreso() {

		OperacionEgreso operacionDesdeBD = entityManager().find(OperacionEgreso.class, new Long(1));
		OperacionEgreso otraOperacionDesdeBD = entityManager().find(OperacionEgreso.class, new Long(2));
		
		assertEquals(operacion, operacionDesdeBD);
		assertEquals(otraOperacion, otraOperacionDesdeBD);
	
		assertNotNull(operacionDesdeBD);
		assertNotNull(otraOperacionDesdeBD);
	}
}
