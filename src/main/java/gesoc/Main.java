package gesoc;

import dominio.categoriaEntidad.CategoriaEntidad;
import dominio.criterioDeSeleccionDeProveedor.CriterioDeSeleccionDeProveedor;
import dominio.documentoComercial.DocumentoComercial;
import dominio.item.Item;
import dominio.medioDePago.MedioDePago;
import dominio.medioDePago.TiposDePago;
import dominio.mensajes.Mensaje;
import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.operacionDeEgreso.RepositorioEgresos;
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

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class Main implements WithGlobalEntityManager {

	public void main(String[] args) throws InterruptedException {
		System.out.println(" - Bienvenido a GeSoc - ");
		System.out.println("Gestion de Proyectos Sociales");
		System.out.println("");

		/* datos de la organizacion */
		List<Entidad> entidades = new ArrayList<>();
		List<OperacionEgreso> operacionesEgreso = new ArrayList<>();
		List<Usuario> usuarios = new ArrayList<>();
		Organizacion organizacion = new Organizacion(entidades, operacionesEgreso, usuarios);

		CategoriaEntidad categoria = new CategoriaEntidad("BlackFreedom");
		Entidad entidad = new EntidadJuridica(null, 0, 0, null,0,null,categoria);
		/* datos de la operacion */
		List<String> etiquetas = new ArrayList<>();
		Date fechaOp = new Date();
		List<Item> items = new ArrayList<>();
		DocumentoComercial documentoComercial = new DocumentoComercial("DNI", 43545454);
		Proveedor proveedor = new Proveedor("carlosGardel", "peronia", 43523423, 2043523423, "1424", "Evita",1945, 3, 'F');
		MedioDePago medioDePago = new MedioDePago(TiposDePago.Efectivo, 200);
		List<Presupuesto> presupuestos = new ArrayList<>();
		List<Usuario> revisores = new ArrayList<>();
		CriterioDeSeleccionDeProveedor criterioDeSeleccionDeProveedor = CriterioDeSeleccionDeProveedor.MENOR_VALOR;
		List<Presupuesto> presupuestos2 = new ArrayList<>();
		etiquetas.add("AMOBLAMIENTO");
		
		OperacionEgreso operacion1 = new OperacionEgreso(etiquetas, fechaOp
				,items, documentoComercial, proveedor,medioDePago, presupuestos, revisores, criterioDeSeleccionDeProveedor, null, entidad);
		
		OperacionEgreso operacion2 = new OperacionEgreso(etiquetas, fechaOp
				,items, documentoComercial, proveedor,medioDePago, presupuestos2, revisores, criterioDeSeleccionDeProveedor, null, entidad);
		
		/*validaciones*/
		ValidadorEgresos validador = new ValidadorEgresos();
		validador.agregarValidacion(new ValidacionAplicacionPresupuesto());
		validador.agregarValidacion(new ValidacionCantidadPresupuestos());
		validador.agregarValidacion(new ValidacionSeleccionProveedor());
		
		operacion1.setValidador(validador);
		operacion2.setValidador(validador);
		
		/*usuarios*/
		List<Mensaje> bandejaDeMensajes = new ArrayList<>();
		revisores.add(new Usuario("pepe","890754983gh",false,bandejaDeMensajes));
		
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
	        	 	entityManager().persist(operacion1);
	        	 	entityManager().persist(operacion2);
	        	 	organizacion.validarOperacionesPendientes();
	        	 	
	        	 	System.out.println("Se terminaron de procesar las validaciones exitosamente!");
	        		
	        	 	System.out.print(" -> Operaciones de egreso totales: ");
	        		System.out.println(RepositorioEgresos.getInstance().getEgresos().size());
	        	 	
	        		System.out.print(" -> Operaciones de egreso validas: ");
	        		System.out.println(organizacion.operacionesEgresoValidas().size());
	        		
	        		System.out.print(" -> Operaciones de egreso invalidas: ");
	        		System.out.println(organizacion.operacionesEgresoInvalidas().size());
	        		
	        		System.out.print(" -> Operaciones de egreso que requieren revision: ");
	        		System.out.println(organizacion.operacionesEgresoPendientesDeValidacion().size());
	        		System.out.println(" -> Se notifica a los revisores de cada operacion.");
	         }
	     };
	     
	     Timer timer = new Timer();
	     // Dentro de 0 milisegundos av�same cada 10000 milisegundos
	     timer.scheduleAtFixedRate(timerTask, 0, 10000);
	/*     timer.wait();  */
	     Thread.sleep(600000);
	     
		System.out.println("");
		System.out.println(" - Fin del procesamiento - ");
	}

}

















