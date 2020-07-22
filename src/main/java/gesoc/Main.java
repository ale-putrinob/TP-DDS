package gesoc;

import dominio.categoriaEntidad.CategoriaEntidad;
import dominio.criterioDeSeleccionDeProveedor.CriterioDeSeleccionDeProveedor;
import dominio.criterioDeSeleccionDeProveedor.MenorValor;
import dominio.documentoComercial.DocumentoComercial;
import dominio.item.Item;
import dominio.medioDePago.MedioDePago;
import dominio.medioDePago.TiposDePago;
import dominio.mensajes.BandejaDeMensajes;
import dominio.operacionDeEgreso.Etiqueta;
import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.organizacion.Entidad;
import dominio.organizacion.EntidadJuridica;
import dominio.organizacion.Organizacion;
import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;
import dominio.usuario.Usuario;
import dominio.validacionEgresos.ValidacionAplicacionPresupuesto;
import dominio.validacionEgresos.ValidacionCantidadPresupuestos;
import dominio.validacionEgresos.ValidacionSeleccionProveedor;
import dominio.validacionEgresos.ValidadorEgresos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		System.out.println(" - Bienvenido a GeSoc - ");
		System.out.println("Gestion de Proyectos Sociales");
		System.out.println("");

		/* datos de la organizacion */
		List<Entidad> entidades = new ArrayList<>();
		List<OperacionEgreso> operacionesEgreso = new ArrayList<>();
		List<Usuario> usuarios = new ArrayList<>();
		Organizacion organizacion = new Organizacion(entidades, operacionesEgreso, usuarios);

		Entidad entidad = new EntidadJuridica(null, 0, 0, null,0,null,new CategoriaEntidad());
		/* datos de la operacion */
		List<Etiqueta> etiquetas = new ArrayList<>();
		Date fechaOp = new Date();
		List<Item> items = new ArrayList<>();
		DocumentoComercial documentoComercial = new DocumentoComercial("DNI", 43545454);
		Proveedor proveedor = new Proveedor("carlosGardel", "peronia", 43523423, 2043523423, 1424);
		MedioDePago medioDePago = new MedioDePago(TiposDePago.Efectivo, 200);
		List<Presupuesto> presupuestos = new ArrayList<>();
		List<Usuario> revisores = new ArrayList<>();
		CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor = new MenorValor();
		List<Presupuesto> presupuestos2 = new ArrayList<>();
		etiquetas.add(Etiqueta.AMOBLAMIENTO);
		
		OperacionEgreso operacion1 = new OperacionEgreso(etiquetas, fechaOp
				,items, documentoComercial, proveedor,medioDePago, presupuestos, revisores, criterioDeSeleccionDeProveedor, null, entidad);
		
		OperacionEgreso operacion2 = new OperacionEgreso(etiquetas, fechaOp
				,items, documentoComercial, proveedor,medioDePago, presupuestos2, revisores, criterioDeSeleccionDeProveedor, null, entidad);
		
		/*validaciones*/
		ValidadorEgresos validador = new ValidadorEgresos();
		validador.agregarValidacion(new ValidacionAplicacionPresupuesto());
		validador.agregarValidacion(new ValidacionCantidadPresupuestos());
		validador.agregarValidacion(new ValidacionSeleccionProveedor());
		
		/*usuarios*/
		revisores.add(new Usuario("pepe","890754983gh",false,new BandejaDeMensajes()));
		
		/*presupuestos*/
		operacion1.agregarPresupuesto(new Presupuesto(new ArrayList<DocumentoComercial>(),items,proveedor,null));
		operacion1.agregarPresupuesto(new Presupuesto(new ArrayList<DocumentoComercial>(),items,proveedor,null));
		operacion1.agregarPresupuesto(new Presupuesto(new ArrayList<DocumentoComercial>(),items,proveedor,null));
		operacion1.agregarPresupuesto(new Presupuesto(new ArrayList<DocumentoComercial>(),items,proveedor,null));
		operacion2.agregarPresupuesto(new Presupuesto(new ArrayList<DocumentoComercial>(),items,proveedor,null));
		operacion2.agregarPresupuesto(new Presupuesto(new ArrayList<DocumentoComercial>(),items,proveedor,null));
		operacion2.agregarPresupuesto(new Presupuesto(new ArrayList<DocumentoComercial>(),items,proveedor,null));
		
		
		
		
		 TimerTask timerTask = new TimerTask()
	     {
	         public void run() {
	        	 	organizacion.agregarOperacionesEgreso(operacion1);
	        	 	organizacion.agregarOperacionesEgreso(operacion2);
	        	 	organizacion.validarOperacionesPendientes();
	        	 	
	        	 	System.out.println("Se terminaron de procesar las validaciones exitosamente!");
	        		
	        		System.out.print(" -> Operaciones de egreso validas: ");
	        		System.out.println(organizacion.operacionesEgresoValidas().size());
	        		
	        		System.out.print(" -> Operaciones de egreso que requieren revision: ");
	        		System.out.println(organizacion.operacionesEgresoPendientesDeValidacion().size());
	        		System.out.println(" -> Se notificÃ³ a los revisores de cada operacion.");
	         }
	     };
	     
	     Timer timer = new Timer();
	     // Dentro de 0 milisegundos avísame cada 1000 milisegundos
	     timer.scheduleAtFixedRate(timerTask, 0, 1000);
	/*     timer.wait();  */
	     
		System.out.println("");
		System.out.println(" - Fin del procesamiento - ");
	}

}

















