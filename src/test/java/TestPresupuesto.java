import dominio.categoriaEntidad.CategoriaEntidad;
import dominio.criterioDeSeleccionDeProveedor.CriterioDeSeleccionDeProveedor;
import dominio.documentoComercial.DocumentoComercial;
import dominio.excepcion.PresupuestoException;
import dominio.item.Item;
import dominio.item.TipoItem;
import dominio.medioDePago.MedioDePago;
import dominio.operacionDeEgreso.Etiqueta;
import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.organizacion.EntidadJuridica;
import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;
import dominio.usuario.Usuario;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TestPresupuesto {
	static OperacionEgreso operacion;
	static List<DocumentoComercial> documentosComerciales = new ArrayList<>();
	static List<Item> items = new ArrayList<>();
	static List<Item> otrosItems = new ArrayList<>();
	static Proveedor proveedor; 
	static Presupuesto presupuesto;
	static Presupuesto presupuesto2;
	static MedioDePago medioDePago;
	static List<Presupuesto> presupuestos = new ArrayList<>();
	static List<Usuario> revisores = new ArrayList<>();
	static CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor;
	static TipoItem carne, polenta, arroz;
	static DocumentoComercial documento1,documento2; 
	static Item item1,item2,otroItem;
	static EntidadJuridica entidad;
	
	@BeforeClass //Antes tardaba 13,722s, ahora 0.013s
    public static void init(){
		documento1 = new DocumentoComercial("Factura A",150);
		documento2 = new DocumentoComercial("Factura",200);
		List<Etiqueta> etiquetas = new ArrayList<>();
		
		carne = new TipoItem();
		polenta = new TipoItem();
		arroz = new TipoItem();
		item1 = new Item(100, carne, null);
		item2 = new Item(50, polenta, null);
		otroItem = new Item(150, arroz, null);
		item1.asociarAEgreso(operacion);
		item2.asociarAEgreso(operacion);
		etiquetas.add(Etiqueta.AMOBLAMIENTO);
		
		proveedor = new Proveedor("Juan","Juan SA",45678978,2045678889,1567);
		
		entidad = new EntidadJuridica(null, 0, 0, null, 0, null, new CategoriaEntidad());
		
		operacion = new OperacionEgreso(etiquetas, new Date(), items, documento1, proveedor, medioDePago, presupuestos, revisores, criterioDeSeleccionDeProveedor, null, entidad);
		
		documentosComerciales.add(documento1); 
		documentosComerciales.add(documento2);
		items.add(item1);
		items.add(item2);
		otrosItems.add(otroItem);
		
		presupuesto = new Presupuesto(documentosComerciales,items,proveedor,null);
    }
    
	@Test 
	public void testCrearPresupuestoValido() {
		Assert.assertEquals(presupuesto.getProveedor(),proveedor);
	}
    
    @Test(expected = PresupuestoException.class)
    public void testCrearPresupuestoInvalido() {
	    presupuesto2 = new Presupuesto(documentosComerciales,otrosItems,proveedor,null);
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
