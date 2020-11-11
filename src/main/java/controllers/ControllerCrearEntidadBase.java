package controllers;

import java.util.HashMap;
import java.util.List;

import dominio.organizacion.EntidadJuridica;
import dominio.organizacion.RepoEntidades;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerCrearEntidadBase {
	public static ModelAndView show(Request req, Response res) {
		List<EntidadJuridica> entidades = RepoEntidades.getInstance().getEntidadesJuridicas();
		
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("entidades", entidades);
		
		return new ModelAndView(viewModel, "crearEntidadBase.hbs");
	}
	
	public static ModelAndView crear(Request req, Response res) {
		
		return null;
	}
}
