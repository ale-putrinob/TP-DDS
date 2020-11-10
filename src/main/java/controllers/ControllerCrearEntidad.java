package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerCrearEntidad {
	public static ModelAndView show(Request req, Response res) {
		return new ModelAndView(null, "login.hbs");
	}
	
public static ModelAndView crear(Request req, Response res) {	
		return null;
	}
}
