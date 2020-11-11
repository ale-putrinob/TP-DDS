package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerCrearEntidad {
	public static ModelAndView show(Request req, Response res) {
		return new ModelAndView(null, "crearEntidad.hbs");
	}
	
public static ModelAndView crear(Request req, Response res) {
	
	String clase = req.queryParams("Clase");
	System.out.println(clase + "-------------------------");
	
	if(clase.equals("0"))
		res.redirect("/entidad/new");
	if(clase.equals("1"))
		res.redirect("/entidad/new/juridica");
	else res.redirect("/entidad/new/base");
	
		return null;
	}
}
