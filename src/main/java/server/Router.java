package server;

import controllers.HomeController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
	public static void configure() {
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		Spark.staticFiles.location("/public");
		

		HomeController homeController=new HomeController();
		
		//Home 
		Spark.get("/", homeController::pantallaPrincipal, engine);
	}
}
