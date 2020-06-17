import dominio.criterioDeSeleccionDeProveedor.CriterioDeSeleccionDeProveedor;
import dominio.criterioDeSeleccionDeProveedor.MenorValor;
import dominio.documentoComercial.DocumentoComercial;
import dominio.item.Item;
import dominio.item.TipoItem;
import dominio.medioDePago.TiposDePago;
import dominio.medioDePago.MedioDePago;
import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;
import dominio.usuario.Usuario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TestOperacionEgreso {
	DocumentoComercial documento;
	List<Item> items = new ArrayList<Item>();
	Proveedor proveedor;
	OperacionEgreso operacion;
	MedioDePago medioDePago;
	List<Presupuesto> presupuestos = new ArrayList<>();
	List<Usuario> revisores = new ArrayList<>();
	CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor;
	
	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		documento = new DocumentoComercial("Factura", 5);
		proveedor = new Proveedor("Juan Peron","JDP",45678978,2045678889,1567);
		medioDePago = new MedioDePago(TiposDePago.TarjetaDeCredito, 1234567890);
		criterioDeSeleccionDeProveedor = new MenorValor();
		operacion = new OperacionEgreso(new Date(2000,13,05), items, documento, proveedor, medioDePago, presupuestos, revisores, criterioDeSeleccionDeProveedor);
	}
	
	@Test
	public void testValorTotal() {
		TipoItem carne = new TipoItem();
		TipoItem sopa = new TipoItem();
		operacion.agregarItem(new Item (100,carne));
		operacion.agregarItem(new Item (100,sopa));
		medioDePago = new MedioDePago(TiposDePago.Efectivo, 200);
		
		Assert.assertEquals(200,operacion.valorTotal(),0.0);
	}
	
	
}


