import dominio.documentoComercial.DocumentoComercial;
import dominio.item.Item;
import dominio.medioDePago.TiposDePago;
import dominio.medioDePago.MedioDePago;
import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.proveedor.Proveedor;

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
	
	/*@SuppressWarnings("deprecation")
	@Before
	public void init() {
		documento = new DocumentoComercial("Factura", 5);
		proveedor = new Proveedor("Juan Per�n","JDP",45678978,2045678889,1567);
		operacion = new OperacionEgreso(new Date(2000,13,05), items, documento, proveedor, medioDePago);
	}
	
	@Test
	public void testValorTotal() {
		items.add(new Item (100,"carne"));
		items.add(new Item (100,"sopa"));
		medioDePago = new MedioDePago(TiposDePago.Efectivo, 200);
		
		Assert.assertEquals(200,operacion.valorTotal(),0.0);
	}*/
	
	
}

