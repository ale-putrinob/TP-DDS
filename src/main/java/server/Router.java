package server;

import controllers.*;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.StringUtils;

public class Router {
	/*private static void chequearLoginInexistente(Request req, Response res) {
		if (req.session(false) == null) {
			res.redirect("/");
			Spark.halt();
		}
	}
	
	private static void chequearLoginExistente(Request req, Response res) {
		if (req.session(false) != null) {
			res.redirect("/");
			Spark.halt();
		}
	}*/
	
	static int getHerokuAssignedPort() {
	    ProcessBuilder processBuilder = new ProcessBuilder();
	    if (processBuilder.environment().get("PORT") != null) {
	        return Integer.parseInt(processBuilder.environment().get("PORT"));
	    }
	    
	    return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
	}

	
	public static void configure() {
		
		Spark.port(getHerokuAssignedPort());
		
		Spark.before((request, response) -> {
			if((StringUtils.isEmpty(request.cookie("usuario_logueado"))) && !(request.pathInfo().equals("/")))
				response.redirect("/");	
		});
		
		
		HandlebarsTemplateEngine transformer = 
				new HandlebarsTemplateEngine();
		
		/*Spark.staticFiles.location("/public");*/
		
		ControllerOperacionEgreso controllerOperacionEgreso = new ControllerOperacionEgreso();
		ControllerCategoria controllerCategoria=new ControllerCategoria();
		ControllerProveedores controllerProveedores=new ControllerProveedores();
		ControllerCrearEntidadJuridica controllerCrearEntidadJuridica = new ControllerCrearEntidadJuridica();
		ControllerCrearEntidadBase controllerCrearEntidadBase = new ControllerCrearEntidadBase();
		ControllerBandejaDeMensajes controllerMensajes = new ControllerBandejaDeMensajes();
		ControllerPresupuesto controllerPresupuesto = new ControllerPresupuesto();
		ControllerLogin controllerLogin = new ControllerLogin();
		
		Spark.get("/", ControllerLogin::show, transformer);
		Spark.post("/", ControllerLogin::login, transformer);
		Spark.get("/home", ControllerHome::home, transformer);
		Spark.post("/logout", controllerLogin::logout,transformer);
		Spark.get("/entidad/search", ControllerSearchEntity::buscarEntidades, transformer);
		Spark.get("/operacionDeEgreso/new", controllerOperacionEgreso::show, transformer);
		Spark.post("/operacionDeEgreso/new", controllerOperacionEgreso::cargarOperacionEgreso, transformer);
		Spark.get("/operacionDeEgreso/new/2", controllerOperacionEgreso::show2, transformer);
		Spark.post("/operacionDeEgreso/new/2", controllerOperacionEgreso::cargarOperacionEgreso2, transformer);
		Spark.get("/operacionDeEgreso/new/3", controllerOperacionEgreso::show3, transformer);
		Spark.post("/operacionDeEgreso/new/3", controllerOperacionEgreso::cargarOperacionEgreso3, transformer);
		Spark.get("/operacionDeEgreso/show", controllerOperacionEgreso::mostrarOperacionEgreso, transformer);
		Spark.get("/presupuesto/new", controllerPresupuesto::show, transformer);
		Spark.post("/presupuesto/new", controllerPresupuesto::cargarPresupuesto, transformer);
		Spark.get("/presupuesto/new/2", controllerPresupuesto::show2, transformer);
		Spark.post("/presupuesto/new/2", controllerPresupuesto::cargarPresupuesto2, transformer);
		Spark.get("/presupuesto/new/3", controllerPresupuesto::show3, transformer);
		Spark.post("/presupuesto/new/3", controllerPresupuesto::cargarPresupuesto3, transformer);
		Spark.post("/categoria/crearCategoria",controllerCategoria::cargarCategoria);
		Spark.get("/categoria",ControllerCategoria::getCategoria,transformer);
		Spark.get("/categoria/asociarEntidad",ControllerCategoria::asociarConEntidad,transformer);
		Spark.post("/categoria/asociarConEntidad",controllerCategoria::asociarEntidad);		
		Spark.get("/entidad/new",ControllerCrearEntidad::show,transformer);
		Spark.post("/entidad/new",ControllerCrearEntidad::crear,transformer);
		Spark.get("/entidad/new/juridica",controllerCrearEntidadJuridica::show,transformer);
		Spark.post("/entidad/new/juridica",controllerCrearEntidadJuridica::crear,transformer);
		Spark.get("/entidad/new/base",controllerCrearEntidadBase::show,transformer);
		Spark.post("/entidad/new/base",controllerCrearEntidadBase::crear,transformer);
		Spark.get("/bandejaDeMensajes", controllerMensajes::show, transformer);
		Spark.get("/proveedores/new",controllerProveedores::obtenerProveedor,transformer);
		Spark.post("/proveedores/new", controllerProveedores::cargarProveedores,transformer);
		
		/*
		Spark.before("/entidad/*", Router::chequearLoginInexistente);
		Spark.before("/entidad", Router::chequearLoginInexistente);
		Spark.before("/proveedores/*", Router::chequearLoginInexistente);
		Spark.before("/bandejaDeMensajes", Router::chequearLoginInexistente);
		Spark.before("/categoria/*", Router::chequearLoginInexistente);
		Spark.before("/categoria", Router::chequearLoginInexistente);
		Spark.before("/presupuesto/*", Router::chequearLoginInexistente);
		Spark.before("/operacionDeEgreso/*", Router::chequearLoginInexistente);
		
		Spark.before("/", Router::chequearLoginExistente);
		*/
	}
}
