package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerOperacionEgreso {
	public static ModelAndView cargarOperacionEgreso(Request req, Response res) {
		return new ModelAndView(null, "cargarOperacionEgreso.hbs");
	}
	
}
