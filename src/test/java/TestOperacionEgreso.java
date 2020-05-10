import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dominio.DocumentoComercial;
import dominio.Item;
import dominio.OperacionEgreso;
import dominio.Proveedor;


public class TestOperacionEgreso {
	
	
	@SuppressWarnings("deprecation")
	@Test 
	public void testValorTotal() {
		DocumentoComercial documento;
		List<Item> items = new ArrayList<Item>();
		Proveedor proveedor;
		OperacionEgreso operacion;
		
				
		documento = new DocumentoComercial("Factura", 5);
		proveedor = new Proveedor("Juan Perón","JDP",45678978,2045678889,1567);
		operacion = new OperacionEgreso(new Date(2000,13,05), items, documento, proveedor);
		items.add(new Item (100,"carne"));
		items.add(new Item (100,"sopa"));
		
		Assert.assertEquals(200,operacion.valorTotal(),0.0);
	}
	
	
}

