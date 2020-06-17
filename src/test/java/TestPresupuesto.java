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
	Proveedor proveedor; 
	Presupuesto presupuesto; 
	TipoItem carne = new TipoItem();
	TipoItem polenta = new TipoItem();
	DocumentoComercial documento1,documento2; 
	Item item1,item2; 
	
	@Before
    public void init(){
		documento1=new DocumentoComercial("Factura A",100);
		documento2=new DocumentoComercial("Factura",200);
		
		item1=new Item(100,carne);
		item2=new Item(50,polenta); 
		
		proveedor=new Proveedor("Juan","Juan SA",45678978,2045678889,1567);
		
		documentosComerciales.add(documento1); 
		documentosComerciales.add(documento2);
		items.add(item1);
		items.add(item2);
		
		presupuesto=new Presupuesto(documentosComerciales,items,proveedor);
    }
	
    @Test
    public void testPresupuestoTotal() {
    	Assert.assertEquals(150, presupuesto.presupuestoTotal());
	}
}
