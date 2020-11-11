package server;

import org.apache.commons.lang3.StringUtils;

import controllers.ControllerCategoria;
import controllers.ControllerCrearEntidad;
import controllers.ControllerHome;
import controllers.ControllerLogin;
import controllers.ControllerOperacionEgreso;
import controllers.ControllerSearchEntity;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
	public static void configure() {
		Spark.before((request, response) -> {
			if((StringUtils.isEmpty(request.cookie("usuario_logueado"))) && !(request.pathInfo().equals("/")))
				response.redirect("/");	
		});
		
		HandlebarsTemplateEngine transformer = 
				new HandlebarsTemplateEngine();
		
		
		Spark.get("/", ControllerLogin::show, transformer);
		Spark.post("/", ControllerLogin::login, transformer);
		Spark.get("/home", ControllerHome::home, transformer);		
		Spark.get("/buscarEntidades", ControllerSearchEntity::buscarEntidades, transformer);
		Spark.get("/operacionDeEgreso", ControllerOperacionEgreso::cargarOperacionEgreso, transformer);
		Spark.get("/categoria",ControllerCategoria::getCategoria,transformer);
		Spark.get("/categoria/asociarEntidad",ControllerCategoria::asociarConEntidad,transformer);
		Spark.get("/entidad/new",ControllerCrearEntidad::show,transformer);
		Spark.post("/entidad/new",ControllerCrearEntidad::crear,transformer);
		
	}
}
