package controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dominio.criterioDeSeleccionDeProveedor.CriterioDeSeleccionDeProveedor;
import dominio.documentoComercial.DocumentoComercial;
import dominio.item.Item;
import dominio.medioDePago.MedioDePago;
import dominio.medioDePago.TiposDePago;
import dominio.moneda.RepoTipoMonedas;
import dominio.moneda.TipoMoneda;
import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.operacionDeEgreso.RepositorioEgresos;
import dominio.organizacion.Entidad;
import dominio.organizacion.EntidadJuridica;
import dominio.organizacion.RepoEntidades;
import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;
import dominio.proveedor.RepoProveedores;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerOperacionEgreso implements WithGlobalEntityManager, TransactionalOps  {
	
	public ModelAndView show(Request req, Response res) {
		List<Proveedor> provs = RepoProveedores.getInstance().getProveedor();
		List<Entidad> entidades = RepoEntidades.getInstance().getEntidadesTotales();

		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("provs", provs);
		viewModel.put("entidades", entidades);

		return new ModelAndView(viewModel, "cargarOperacionEgreso.hbs");
	}
	
	public ModelAndView show2(Request req, Response res) {

		return new ModelAndView(null, "cargarOperacionEgreso2.hbs");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView show3(Request req, Response res) {
		List<Presupuesto> presupuestos = entityManager().createQuery("from Presupuesto").getResultList();
		
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("presupuestos", presupuestos);
		
		return new ModelAndView(viewModel, "cargarOperacionEgreso3.hbs");
	}
	
	public ModelAndView cargarOperacionEgreso(Request req, Response res) {
		
		/* Date fecha = new Date(req.queryParams("Fecha")); */
		String criterioProv = req.queryParams("Criterio de Seleccion del Proveedor");
		CriterioDeSeleccionDeProveedor criterioProveedor;
		@SuppressWarnings("deprecation")
		Integer identificador = new Integer(req.queryParams("Identificador"));
		String tipoString = req.queryParams("Tipo de pago");
		TiposDePago tipo;
		String tipoDoc = req.queryParams("Tipo");
		@SuppressWarnings("deprecation")
		Integer numeroDoc = new Integer(req.queryParams("Numero"));
		String id_proveedor = req.queryParams("Proveedor").split(" - ")[0];
		Proveedor proveedor = RepoProveedores.getInstance().findProveedor(id_proveedor);
		String id_entidad = req.queryParams("Entidad").split(" - ")[0];
		Entidad entidad = RepoEntidades.getInstance().findEntidad(id_entidad);
		
		/* String id_moneda = req.queryParams("TipoMoneda").split(" - ")[0];
		TipoMoneda tipoMoneda = RepoTipoMonedas.getInstance().findTipoMoneda(id_moneda); */
				
		if(tipoString.equals("1")) tipo = TiposDePago.TarjetaDeCredito; 
		else if(tipoString.equals("2")) tipo = TiposDePago.TarjetaDeDebito;
		else if(tipoString.equals("3")) tipo = TiposDePago.Efectivo;
		else if(tipoString.equals("4")) tipo = TiposDePago.DineroEnCuenta;
		else if(tipoString.equals("5")) tipo = TiposDePago.CajeroAutomatico;
		else tipo = null;
		
		if(criterioProv.equals("1")) criterioProveedor = CriterioDeSeleccionDeProveedor.MENOR_VALOR;
		else criterioProveedor = null;
		
		DocumentoComercial docCom = new DocumentoComercial(tipoDoc,numeroDoc);
		MedioDePago medioPago = new MedioDePago(tipo,identificador);
		OperacionEgreso opEgreso= new OperacionEgreso(null ,null, null,  docCom, 
				proveedor, medioPago, null, null, criterioProveedor, null,entidad);
		
		
		withTransaction(() ->{
			RepositorioEgresos.getInstance().agregarEgreso(opEgreso);
			res.cookie("id_egreso", opEgreso.getId().toString());
		});
		
		res.redirect("/operacionDeEgreso/new/2");
		return null;
	}
	
	public  ModelAndView cargarOperacionEgreso2(Request req, Response res) {
		
		@SuppressWarnings("deprecation")
		Integer valor = new Integer(req.queryParams("Valor"));
		String tipoItem = req.queryParams("Tipo");
		System.out.println("ID: ----------------" + req.cookie("id_egreso"));
		OperacionEgreso opEgreso = entityManager().find(OperacionEgreso.class, new Long(req.cookie("id_egreso")));
		
		Item item = new Item(valor,tipoItem,null);
		
		
		
		withTransaction(() ->{
			entityManager().persist(item);
			opEgreso.agregarItem(item);
			RepositorioEgresos.getInstance().agregarEgreso(opEgreso);
			res.cookie("id_egreso", opEgreso.getId().toString());
		});
		
		res.redirect("/operacionDeEgreso/new/2");
		return null;
	}
	
	public  ModelAndView cargarOperacionEgreso3(Request req, Response res) {
		String id_presupuesto = req.queryParams("Presupuesto").split(" ")[0];
		Presupuesto presupuesto = entityManager().find(Presupuesto.class, new Long(id_presupuesto));
		
		OperacionEgreso opEgreso = entityManager().find(OperacionEgreso.class, new Long(req.cookie("id_egreso")));
		
		withTransaction(() ->{
			entityManager().persist(presupuesto);
			opEgreso.agregarPresupuesto(presupuesto);
			RepositorioEgresos.getInstance().agregarEgreso(opEgreso);
		});
		
		res.redirect("/home");
		return null;
	}
	
	/*para cargar los items*/
	
	public  ModelAndView mostrarOperacionEgreso(Request req, Response res) {
		List<OperacionEgreso> opEgresos = RepositorioEgresos.getInstance().getEgresos();

		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("opEgresos", opEgresos);

		return new ModelAndView(viewModel, "mostrarOperacionEgreso.hbs");
	}

}

