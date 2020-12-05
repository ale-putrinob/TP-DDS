package controllers;

import java.util.ArrayList;
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
		
		HashMap<String, Object> viewModel = new HashMap<>();
		OperacionEgreso opEgreso = req.session().attribute("opEgreso");
		List<Item> items = opEgreso.getItems();
		
		viewModel.put("items", items);
		
		return new ModelAndView(viewModel, "cargarOperacionEgreso2.hbs");
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
		TiposDePago tipo = TiposDePago.valueOf(req.queryParams("Tipo de pago"));
		String tipoDoc = req.queryParams("Tipo");
		@SuppressWarnings("deprecation")
		Integer numeroDoc = new Integer(req.queryParams("Numero"));
		String id_proveedor = req.queryParams("Proveedor");
		Proveedor proveedor = RepoProveedores.getInstance().findProveedor(new Long(id_proveedor));
		String id_entidad = req.queryParams("Entidad");
		Entidad entidad = RepoEntidades.getInstance().findEntidad(id_entidad);
		
		/* String id_moneda = req.queryParams("TipoMoneda").split(" - ")[0];
		TipoMoneda tipoMoneda = RepoTipoMonedas.getInstance().findTipoMoneda(id_moneda); */
				
		if(criterioProv.equals("1")) criterioProveedor = CriterioDeSeleccionDeProveedor.MENOR_VALOR;
		else criterioProveedor = null;
		
		DocumentoComercial docCom = new DocumentoComercial(tipoDoc,numeroDoc);
		MedioDePago medioPago = new MedioDePago(tipo,identificador);
		OperacionEgreso opEgreso= new OperacionEgreso(null ,null, new ArrayList<Item>(),  docCom, 
				null, medioPago, new ArrayList<Presupuesto>(), null, criterioProveedor, null,null);
		if(proveedor != null)
			opEgreso.setProveedor(proveedor);
		if(entidad != null)
			opEgreso.setEntidad(entidad);
		
		req.session().attribute("opEgreso", opEgreso);
		req.session().attribute("proveedor", proveedor);
		req.session().attribute("entidad", entidad);

		
		res.redirect("/operacionesDeEgresos/new/2");
		return null;
	}
	
	public  ModelAndView cargarOperacionEgreso2(Request req, Response res) {
		
		@SuppressWarnings("deprecation")
		Integer valor = new Integer(req.queryParams("Valor"));
		String tipoItem = req.queryParams("Tipo");

		OperacionEgreso opEgreso = req.session().attribute("opEgreso");
		
		Item item = new Item(valor,tipoItem,null);
		
		opEgreso.agregarItem(item);
		req.session().attribute("opEgreso", opEgreso);
		
		res.redirect("/operacionesDeEgresos/new/2");
		return null;
	}
	
	public  ModelAndView cargarOperacionEgreso3(Request req, Response res) {
		//String id_presupuesto = req.queryParams("Presupuesto").split(" ")[0];
		//Presupuesto presupuesto = entityManager().find(Presupuesto.class, new Long(id_presupuesto));
		
		withTransaction(() ->{
			OperacionEgreso opEgreso = req.session().attribute("opEgreso");
			RepositorioEgresos.getInstance().agregarEgreso(opEgreso);
			req.session().removeAttribute("opEgreso");
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

