import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;

import dominio.categoriaEntidad.CategoriaEntidad;
import dominio.item.Item;
import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.operacionDeEgreso.RepositorioEgresos;
import dominio.organizacion.Entidad;
import dominio.organizacion.EntidadJuridica;
import junit.framework.Assert;


@SuppressWarnings("deprecation")
public class TestEtiquetadoEgresos extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
	static List<String> etiquetas1;
	static List<String> etiquetas2;
	static List<String> etiquetas3;
	static Date fechaOp1;
	static Date fechaOp2;
	static Date fechaOp3;
	static List<Item> items1 = new ArrayList<Item>();
	static List<Item> items2 = new ArrayList<Item>();
	static List<Item> items3 = new ArrayList<Item>();
	static OperacionEgreso operacion1;
	static OperacionEgreso operacion2;
	static OperacionEgreso operacion3;
	
	static Entidad entidad;
	
	@Before
	public void init() {
		
		entidad = new EntidadJuridica(null, 0, 0, null, 0, null,new CategoriaEntidad());
		
		etiquetas1 = new ArrayList<>();
		fechaOp1 = new Date(2000,10,15);
		items1.add(new Item (200,"carne",null));
		items1.add(new Item (200,"sopa", null));
		etiquetas1.add("AMOBLAMIENTO");
		
		etiquetas2 = new ArrayList<>();
		fechaOp2 = new Date(2000,11,15);
		items2.add(new Item (300,"carne",null));
		items2.add(new Item (300,"sopa", null));
		etiquetas2.add("INDUMENTARIA");
		
		etiquetas3 = new ArrayList<>();
		fechaOp3 = new Date(2000,10,20);
		items3.add(new Item (400,"carne",null));
		items3.add(new Item (400,"sopa", null));
		etiquetas3.add("AMOBLAMIENTO");
		
		operacion1 = new OperacionEgreso(etiquetas1, fechaOp1, items1, null, 
				null, null, null, null, null, null, entidad);
		
		operacion2 = new OperacionEgreso(etiquetas2, fechaOp2, items2, null, 
				null, null, null, null, null, null, entidad);
		
		operacion3 = new OperacionEgreso(etiquetas3, fechaOp3, items3, null, 
				null, null, null, null, null, null, entidad);
		
		/* RepositorioEgresos.getInstance().agregarEgreso(operacion1);
		RepositorioEgresos.getInstance().agregarEgreso(operacion2);
		RepositorioEgresos.getInstance().agregarEgreso(operacion3); */ 
		
		entityManager().persist(operacion1);
		entityManager().persist(operacion2);
		entityManager().persist(operacion3);
	}
	
		@Test
		public void gastosAmoblamientoDeOctubreDel2000Suman1200() {
			Assert.assertEquals(entidad.ReporteGastosMensuales("AMOBLAMIENTO",10,2000), 1200,0);
		}
		
		@Test
		public void gastosIndumentariaDeNoviembreDel2000Suman600() {
			Assert.assertEquals(entidad.ReporteGastosMensuales("INDUMENTARIA",11,2000), 1200,0);
		}
		
		@Test
		public void gastosProveedorDeDiciembreDel2000Suman0() {
			Assert.assertEquals(entidad.ReporteGastosMensuales("PROVEEDOR",12,2000), 0,0);
		}
		
		
	
}
