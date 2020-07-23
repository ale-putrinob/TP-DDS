import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;

import dominio.categoriaEntidad.CategoriaEntidad;
import dominio.item.Item;
import dominio.operacionDeEgreso.Etiqueta;
import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.operacionDeEgreso.RepositorioEgresos;
import dominio.organizacion.Entidad;
import dominio.organizacion.EntidadJuridica;
import dominio.organizacion.Tipo;
import junit.framework.Assert;


@SuppressWarnings("deprecation")
public class TestEtiquetadoEgresos {
	
	List<Etiqueta> etiquetas1;
	List<Etiqueta> etiquetas2;
	List<Etiqueta> etiquetas3;
	Date fechaOp1;
	Date fechaOp2;
	Date fechaOp3;
	List<Item> items1 = new ArrayList<Item>();
	List<Item> items2 = new ArrayList<Item>();
	List<Item> items3 = new ArrayList<Item>();
	OperacionEgreso operacion1;
	OperacionEgreso operacion2;
	OperacionEgreso operacion3;
	
	Entidad entidad;
	
	@Before 
	public void init() {
		
		entidad = new EntidadJuridica(null, 0, 0, null, 0, null,new CategoriaEntidad());
		
		etiquetas1 = new ArrayList<>();
		fechaOp1 = new Date(2000,10,15);
		items1.add(new Item (200,"carne",null));
		items1.add(new Item (200,"sopa", null));
		etiquetas1.add(Etiqueta.AMOBLAMIENTO);
		
		etiquetas2 = new ArrayList<>();
		fechaOp2 = new Date(2000,11,15);
		items2.add(new Item (300,"carne",null));
		items2.add(new Item (300,"sopa", null));
		etiquetas2.add(Etiqueta.INDUMENTARIA);
		
		etiquetas3 = new ArrayList<>();
		fechaOp3 = new Date(2000,10,20);
		items3.add(new Item (400,"carne",null));
		items3.add(new Item (400,"sopa", null));
		etiquetas3.add(Etiqueta.AMOBLAMIENTO);
		
		operacion1 = new OperacionEgreso(etiquetas1, fechaOp1, items1, null, 
				null, null, null, null, null, null, entidad);
		
		operacion2 = new OperacionEgreso(etiquetas2, fechaOp2, items2, null, 
				null, null, null, null, null, null, entidad);
		
		operacion3 = new OperacionEgreso(etiquetas3, fechaOp3, items3, null, 
				null, null, null, null, null, null, entidad);
		
		RepositorioEgresos.getInstance().agregarEgreso(operacion1);
		RepositorioEgresos.getInstance().agregarEgreso(operacion2);
		RepositorioEgresos.getInstance().agregarEgreso(operacion3);
	}
	
		@Test
		public void gastosAmoblamientoDeOctubreDel2000Suman1200() {
			Assert.assertEquals(entidad.ReporteGastosMensuales(Etiqueta.AMOBLAMIENTO,10,2000), 1200,0);
		}
		
		@Test
		public void gastosIndumentariaDeNoviembreDel2000Suman600() {
			Assert.assertEquals(entidad.ReporteGastosMensuales(Etiqueta.INDUMENTARIA,11,2000), 600,0);
		}
		
		@Test
		public void gastosProveedorDeDiciembreDel2000Suman0() {
			Assert.assertEquals(entidad.ReporteGastosMensuales(Etiqueta.PROVEEDOR,12,2000), 0,0);
		}
		
		
	
}
