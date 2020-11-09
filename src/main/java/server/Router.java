package server;

import controllers.ControllerHome;
import controllers.ControllerSearchEntity;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
	public static void configure() {
		HandlebarsTemplateEngine transformer = 
				new HandlebarsTemplateEngine();
		
		Spark.get("/", ControllerHome::home, transformer);		
		Spark.get("/buscarEntidades", ControllerSearchEntity::buscarEntidades, transformer);
	}
}
