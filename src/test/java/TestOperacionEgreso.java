import dominio.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TestOperacionEgreso {
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void testValorTotal() {
		DocumentoComercial documento;
		List<Item> items = new ArrayList<Item>();
		Proveedor proveedor;
		OperacionEgreso operacion;
		MedioDePago medioDePago;

		documento = new DocumentoComercial("Factura", 5);
		proveedor = new Proveedor("Juan Per�n","JDP",45678978,2045678889,1567);
		medioDePago = new Efectivo("Red Link");
		operacion = new OperacionEgreso(new Date(2000,13,05), items, documento, proveedor, medioDePago);
		items.add(new Item (100,"carne"));
		items.add(new Item (100,"sopa"));
		
		Assert.assertEquals(200,operacion.valorTotal(),0.0);
	}
	
	
}

