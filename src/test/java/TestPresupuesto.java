import dominio.criterioDeSeleccionDeProveedor.CriterioDeSeleccionDeProveedor;
import dominio.documentoComercial.DocumentoComercial;
import dominio.excepcion.PresupuestoException;
import dominio.item.Item;
import dominio.item.TipoItem;
import dominio.medioDePago.MedioDePago;
import dominio.medioDePago.TiposDePago;
import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.proveedor.Proveedor;
import dominio.usuario.Usuario;
import dominio.presupuesto.Presupuesto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class TestPresupuesto {
	OperacionEgreso operacion;
	List<DocumentoComercial> documentosComerciales = new ArrayList<>();
	List<Item> items = new ArrayList<>();
	List<Item> otrosItems = new ArrayList<>();
	Proveedor proveedor; 
	Presupuesto presupuesto, presupuesto2;
	MedioDePago medioDePago;
	List<Presupuesto> presupuestos = new ArrayList<>();
	List<Usuario> revisores = new ArrayList<>();
	CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor;
	TipoItem carne, polenta, arroz;
	DocumentoComercial documento1,documento2; 
	Item item1,item2,otroItem;
	
	@Before
    public void init(){
		documento1 = new DocumentoComercial("Factura A",150);
		documento2 = new DocumentoComercial("Factura",200);
		
		carne = new TipoItem();
		polenta = new TipoItem();
		arroz = new TipoItem();
		item1 = new Item(100,carne);
		item2 = new Item(50,polenta);
		otroItem = new Item(150,arroz);
		item1.asociarAEgreso(operacion);
		item2.asociarAEgreso(operacion);
		
		proveedor = new Proveedor("Juan","Juan SA",45678978,2045678889,1567);
		
		operacion = new OperacionEgreso(new Date(), items, documento1, proveedor, medioDePago, presupuestos, revisores, criterioDeSeleccionDeProveedor);
		
		documentosComerciales.add(documento1); 
		documentosComerciales.add(documento2);
		items.add(item1);
		items.add(item2);
		otrosItems.add(otroItem);
		
		presupuesto = new Presupuesto(documentosComerciales,items,proveedor);		
    }
    
	@Test 
	public void testCrearPresupuestoValido() {
		Assert.assertEquals(presupuesto.getProveedor(),proveedor);
	}
    
    @Test(expected = PresupuestoException.class)
    public void testCrearPresupuestoInvalido() {
	    presupuesto2 = new Presupuesto(documentosComerciales,otrosItems,proveedor);
    }
    
	@Test 
	public void testContieneMismosItemsYNoContieneOtros() {
		Assert.assertTrue(presupuesto.contieneItems(items));
		Assert.assertFalse(presupuesto.contieneItems(otrosItems));
	}
	
    @Test
    public void testPresupuestoTotal() {
    	Assert.assertEquals(150, presupuesto.presupuestoTotal(),0);
	}
}
