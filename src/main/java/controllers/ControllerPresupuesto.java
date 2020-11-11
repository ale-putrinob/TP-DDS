package controllers;

import java.util.HashMap;
import java.util.List;

import dominio.proveedor.Proveedor;
import dominio.proveedor.RepoProveedores;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerPresupuesto {
	public static ModelAndView show(Request req, Response res) {
		List<Proveedor> provs = RepoProveedores.getInstance().getProveedor();

		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("provs", provs);

		return new ModelAndView(viewModel, "cargarPresupuesto.hbs");
	}
	
	public static ModelAndView cargarPresupuesto(Request req, Response res) {
		
		res.redirect("/home");
		return null;
	}

}
