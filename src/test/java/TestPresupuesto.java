import dominio.documentoComercial.DocumentoComercial;
import dominio.item.Item;
import dominio.item.TipoItem;
import dominio.medioDePago.MedioDePago;
import dominio.medioDePago.TiposDePago;
import dominio.proveedor.Proveedor;
import dominio.presupuesto.Presupuesto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class TestPresupuesto {
	List<DocumentoComercial> documentosComerciales;
	List<Item> items;
	Presupuesto presupuesto; 
	TipoItem carne = new TipoItem();
	DocumentoComercial documento= new DocumentoComercial("Factura", 5); 
	Item item= new Item (100,carne);
	
	@Before
    public void init(){
		documentosComerciales.add(documento);
    	items.add(item);
    }
    
    /*@Test
    public void testCrearPresupuesto(){
    	presupuesto = new Presupuesto(documentosComerciales,items);
    	Assert.assertNotNull(presupuesto);
    }*/
    @Test
    public void testPresupuestoTotal() {
		presupuesto=(new Presupuesto(documentosComerciales,items));
		double total=presupuesto.presupuestoTotal();
    	Assert.assertEquals(100, total); 
	}
}
