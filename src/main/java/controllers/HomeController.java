package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {
	public ModelAndView pantallaPrincipal(Request req, Response res) {
		return new ModelAndView(null, "home.hbs");
	}
}
