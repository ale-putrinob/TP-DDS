package gesoc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dominio.criterioDeSeleccionDeProveedor.CriterioDeSeleccionDeProveedor;
import dominio.criterioDeSeleccionDeProveedor.MenorValor;
import dominio.documentoComercial.DocumentoComercial;
import dominio.item.Item;
import dominio.medioDePago.MedioDePago;
import dominio.medioDePago.TiposDePago;
import dominio.mensajes.BandejaDeMensajes;
import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.organizacion.Entidad;
import dominio.organizacion.Organizacion;
import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;
import dominio.usuario.Usuario;
import dominio.validacionEgresos.ValidacionAplicacionPresupuesto;
import dominio.validacionEgresos.ValidacionCantidadPresupuestos;
import dominio.validacionEgresos.ValidacionSeleccionProveedor;
import dominio.validacionEgresos.ValidadorEgresos;

public class Main {

	public static void main(String[] args) {
		System.out.println(" - Bienvenido a GeSoc - ");
		System.out.println("Gestion de Proyectos Sociales");
		System.out.println("");

		/* datos de la organizacion */
		List<Entidad> entidades = new ArrayList<>();
		List<OperacionEgreso> operacionesEgreso = new ArrayList<>();
		List<OperacionEgreso> operacionesEgresoPendientesDeValidacion = new ArrayList<>();
		List<Usuario> usuarios = new ArrayList<>();
		Organizacion organizacion = new Organizacion(entidades, operacionesEgreso);

		/* datos de la operacion */
		Date fechaOp = new Date();
		List<Item> items = new ArrayList<>();
		DocumentoComercial documentoComercial = new DocumentoComercial("DNI", 43545454);
		Proveedor proveedor = new Proveedor("carlosGardel", "peronia", 43523423, 2043523423, 1424);
		MedioDePago medioDePago = new MedioDePago(TiposDePago.Efectivo, 200);
		List<Presupuesto> presupuestos = new ArrayList<>();
		List<Usuario> revisores = new ArrayList<>();
		CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor = new MenorValor();
		List<Presupuesto> presupuestos2 = new ArrayList<>();
		
		OperacionEgreso operacion1 = new OperacionEgreso(fechaOp
				,items, documentoComercial, proveedor,medioDePago, presupuestos, revisores, criterioDeSeleccionDeProveedor);
		
		OperacionEgreso operacion2 = new OperacionEgreso(fechaOp
				,items, documentoComercial, proveedor,medioDePago, presupuestos2, revisores, criterioDeSeleccionDeProveedor);
		
		/*validaciones*/
		ValidadorEgresos validador = ValidadorEgresos.getInstance();
		validador.agregarValidacion(new ValidacionAplicacionPresupuesto());
		validador.agregarValidacion(new ValidacionCantidadPresupuestos());
		validador.agregarValidacion(new ValidacionSeleccionProveedor());
		
		/*usuarios*/
		revisores.add(new Usuario("pepe","890754983gh",false,new BandejaDeMensajes()));
		
		/*presupuestos*/
		presupuestos.add(new Presupuesto(new ArrayList<DocumentoComercial>(),items,proveedor));
		presupuestos.add(new Presupuesto(new ArrayList<DocumentoComercial>(),items,proveedor));
		presupuestos.add(new Presupuesto(new ArrayList<DocumentoComercial>(),items,proveedor));
		presupuestos.add(new Presupuesto(new ArrayList<DocumentoComercial>(),items,proveedor));
		presupuestos2.add(new Presupuesto(new ArrayList<DocumentoComercial>(),items,proveedor));
		presupuestos2.add(new Presupuesto(new ArrayList<DocumentoComercial>(),items,proveedor));
		presupuestos2.add(new Presupuesto(new ArrayList<DocumentoComercial>(),items,proveedor));
		
		
		organizacion.operacionesEgresoPendientesDeValidacion.add(operacion1);
		organizacion.operacionesEgresoPendientesDeValidacion.add(operacion2);
		organizacion.validarOperacionesPendientes();
		if (operacionesEgresoPendientesDeValidacion.size() == 1)
			System.out.println("Se han realizado las validaciones correctamente.");
		else
			System.out.println("No se han realizado las validaciones correctamente.");
		
		if (operacionesEgreso.size() == 1)
			System.out.println("Se han realizado las validaciones correctamente.");
		else
			System.out.println("No se han realizado las validaciones correctamente.");
		


	}

}

















