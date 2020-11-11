package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerCrearEntidadJuridica {
	public static ModelAndView show(Request req, Response res) {
		return new ModelAndView(null, "crearEntidadJuridica.hbs");
	}
	
	public static ModelAndView crear(Request req, Response res) {
		String nombreFic = req.queryParams("nombreFicticio");
		String razonSoc = req.queryParams("razonSocial");
		String cuitString = req.queryParams("razonSocial");
		String codInscripto = req.queryParams("razonSocial");
		
		
		return null;
	}
}
