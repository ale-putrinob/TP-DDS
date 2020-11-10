package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerCategoria {
	public static ModelAndView getCategoria(Request req, Response res) {
		return new ModelAndView(null, "cargarCategoria.hbs");
	}
	public static ModelAndView asociarConEntidad(Request req, Response res) {
		return new ModelAndView(null, "asociarCategoriaConEntidad.hbs");
	}
}
