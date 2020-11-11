package controllers;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.proveedor.Proveedor;
import dominio.proveedor.RepoProveedores;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerOperacionEgreso {
	OperacionEgreso opEgreso;
	
	public static ModelAndView show(Request req, Response res) {
		List<Proveedor> provs = RepoProveedores.getInstance().getProveedor();

		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("provs", provs);

		return new ModelAndView(viewModel, "cargarOperacionEgreso.hbs");
	}
	
	public static ModelAndView show2(Request req, Response res) {

		return new ModelAndView(null, "cargarOperacionEgreso2.hbs");
	}
	
	public static ModelAndView cargarOperacionEgreso(Request req, Response res) {
		/*
		Date fecha = req.queryParams("Fecha"); 
		String criterioProv = req.queryParams("Criterio de Seleccion del Proveedor");
		Int identificador = req.quer
		*/
		
		res.redirect("/operacionDeEgreso/new/2");
		return null;
	}
	
	public static ModelAndView mostrarOperacionEgreso(Request req, Response res) {
		return new ModelAndView(null, "mostrarOperacionEgreso.hbs");
	}

}

