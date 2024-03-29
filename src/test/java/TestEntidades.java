import dominio.categoriaEntidad.*;
import dominio.criterioDeSeleccionDeProveedor.CriterioDeSeleccionDeProveedor;
import dominio.documentoComercial.DocumentoComercial;
import dominio.excepcion.EntidadException;
import dominio.item.Item;
import dominio.mensajes.Mensaje;
import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.organizacion.EntidadBase;
import dominio.organizacion.EntidadJuridica;
import dominio.organizacion.Tipo;
import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;
import dominio.validacionEgresos.ValidacionAplicacionPresupuesto;
import dominio.validacionEgresos.ValidacionCantidadPresupuestos;
import dominio.validacionEgresos.ValidacionSeleccionProveedor;
import dominio.validacionEgresos.ValidadorEgresos;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class TestEntidades {
	List<String> etiquetas;
	List<Item> items = new ArrayList<Item>();
	List<Item> otrosItems = new ArrayList<Item>();
	List<Item> otrosItems2 = new ArrayList<Item>();
	Proveedor proveedor;
	OperacionEgreso operacion;
	List<Presupuesto> presupuestos = new ArrayList<>();
	CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor;

	List<DocumentoComercial> documentos; 
	List<Mensaje> bandejaDeMensajes = new ArrayList<>();
	ValidadorEgresos validador = new ValidadorEgresos();
	
	EntidadJuridica entidadJuridica;
	EntidadBase entidadBase;
	CategoriaEntidad categoria1;
	CategoriaEntidad categoria2;
	Funcionalidad funcionalidad1;
	Funcionalidad funcionalidad2;
	Funcionalidad funcionalidad3;
	
	@Before
	public void init() {
		validador.agregarValidacion(new ValidacionAplicacionPresupuesto());
		validador.agregarValidacion(new ValidacionCantidadPresupuestos());
		validador.agregarValidacion(new ValidacionSeleccionProveedor());
		etiquetas = new ArrayList<>();
		proveedor = new Proveedor("Juan Peron","JDP",45678978,2045678889,"1567","Evita", 31, 2, 'A');
		criterioDeSeleccionDeProveedor = CriterioDeSeleccionDeProveedor.MENOR_VALOR;
		otrosItems.add(new Item (200,"carne",null));
		otrosItems.add(new Item (200,"sopa", null));
		criterioDeSeleccionDeProveedor= CriterioDeSeleccionDeProveedor.MENOR_VALOR;
		etiquetas.add("AMOBLAMIENTO");
		
		funcionalidad1 = new BloquearNuevasEntidadesBase();
		funcionalidad2 = new BloquearNuevosEgresos(0);
		funcionalidad3 = new BloquearSerParteDeEntidadJuridica();
		
		categoria1 = new CategoriaEntidad("BlackFreedom");
		categoria2 = new CategoriaEntidad("Wannabe");
		
		/*operacion = new OperacionEgreso(etiquetas, new Date(2000,13,05), items, null, 
				proveedor, null, presupuestos, null, criterioDeSeleccionDeProveedor, null, entidadBase);*/
	}
	
	@Test(expected = EntidadException.class)
	public void seBloqueanNuevasEntidadesBase() {
		categoria1.agregarFuncionalidad(funcionalidad1);
		entidadJuridica = new EntidadJuridica(null, null, 0, null, 0, Tipo.EmpresaPequenia, categoria1);
		entidadBase = new EntidadBase(null, null, entidadJuridica, categoria2);
	}
	
	@Test(expected = EntidadException.class)
	public void seBloqueaSerParteDeEntidadJuridica() {
		categoria2.agregarFuncionalidad(funcionalidad3);
		entidadJuridica = new EntidadJuridica(null, null, 0, null, 0, Tipo.EmpresaPequenia, categoria1);
		entidadBase = new EntidadBase(null, null, entidadJuridica, categoria2);
	}
	
	@Test(expected = EntidadException.class)
	public void seBloqueanNuevosEgresos() {
		categoria1.agregarFuncionalidad(funcionalidad2);
		entidadJuridica = new EntidadJuridica(null, null, 0, null, 0, Tipo.EmpresaPequenia, categoria1);
		operacion = new OperacionEgreso(null, null, items, null, null, null, null, null, null, null, entidadJuridica);
	}
	
	@Test
	public void noSeBloqueanNuevasEntidadesBase() {
		entidadJuridica = new EntidadJuridica(null, null, 0, null, 0, Tipo.EmpresaPequenia, categoria1);
		entidadBase = new EntidadBase(null, null, entidadJuridica, categoria2);
		Assert.assertEquals(entidadBase.getDependencia(), entidadJuridica);
	}
	
	@Test
	public void noSeBloqueanNuevosEgresos() {
		entidadJuridica = new EntidadJuridica(null, null, 0, null, 0, Tipo.EmpresaPequenia, categoria1);
		operacion = new OperacionEgreso(null, null, items, null, null, null, null, null, null, null, entidadJuridica);
		Assert.assertEquals(operacion.getEntidad(), entidadJuridica);
	}
	
}
