package controllers;

import java.util.HashMap;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dominio.documentoComercial.DocumentoComercial;
import dominio.item.Item;
import dominio.moneda.TipoMoneda;
import dominio.organizacion.EntidadJuridica;
import dominio.organizacion.RepoEntidades;
import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;
import dominio.proveedor.RepoProveedores;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerPresupuesto implements WithGlobalEntityManager, TransactionalOps {
	Long idPresupuesto;
	
	public ModelAndView show(Request req, Response res) {
		List<Proveedor> provs = RepoProveedores.getInstance().getProveedor();

		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("provs", provs);

		return new ModelAndView(viewModel, "cargarPresupuesto.hbs");
	}
	
	public ModelAndView show2(Request req, Response res) {
		List<Proveedor> provs = RepoProveedores.getInstance().getProveedor();

		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("provs", provs);

		return new ModelAndView(viewModel, "cargarPresupuesto2.hbs");
	}
	
	public ModelAndView show3(Request req, Response res) {
		List<Proveedor> provs = RepoProveedores.getInstance().getProveedor();

		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("provs", provs);

		return new ModelAndView(viewModel, "cargarPresupuesto3.hbs");
	}
	
	public ModelAndView cargarPresupuesto(Request req, Response res) {
		Integer valor = new Integer(req.queryParams("Valor"));
		String tipo = req.queryParams("Tipo");
		
		Presupuesto pres = new Presupuesto();
		Item item = new Item(valor, tipo, null);
		
		
		withTransaction(() ->{
			entityManager().persist(item);
			pres.agregarItem(item);
			entityManager().persist(pres);
			res.cookie("id_proveedor", pres.getId().toString());
		});
		
		res.redirect("/presupuesto/new");
		return null;
	}
	
	public ModelAndView cargarPresupuesto2(Request req, Response res) {
		Integer numero = new Integer(req.queryParams("Numero"));
		String tipo = req.queryParams("Tipo");
		
		DocumentoComercial doc = new DocumentoComercial(tipo, numero);
		System.out.println("ID: ----------------" + req.cookie("id_proveedor"));
		Presupuesto pres = entityManager().find(Presupuesto.class, new Long(req.cookie("id_proveedor")));
		
		
		
		withTransaction(() ->{
			entityManager().persist(doc);
			pres.agregarDocComercial(doc);
			entityManager().persist(pres);
		});
		
		res.redirect("/presupuesto/new/2");
		return null;
	}

	public ModelAndView cargarPresupuesto3(Request req, Response res) {
		String id_proveedor = req.queryParams("Proveedor").split(" ")[0];
		Proveedor prov = RepoProveedores.getInstance().findProveedor(id_proveedor);
		Presupuesto pres = entityManager().find(Presupuesto.class, new Long(req.cookie("id_proveedor")));
		
		withTransaction(() ->{
			entityManager().persist(prov);
			pres.setProveedor(prov);
			entityManager().persist(pres);
		});
		
		res.redirect("/home");
		return null;
	}

}
