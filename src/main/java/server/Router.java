package server;

import controllers.ControllerCategoria;
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
		Spark.get("/categoria",ControllerCategoria::getCategoria,transformer);
		Spark.get("/categoria/asociarEntidad",ControllerCategoria::asociarConEntidad,transformer);
		
	}
}
