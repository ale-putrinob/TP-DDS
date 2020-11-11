package server;

import org.apache.commons.lang3.StringUtils;

import controllers.ControllerCategoria;
import controllers.ControllerCrearEntidad;
import controllers.ControllerCrearEntidadBase;
import controllers.ControllerCrearEntidadJuridica;
import controllers.ControllerHome;
import controllers.ControllerLogin;
import controllers.ControllerOperacionEgreso;
import controllers.ControllerPresupuesto;
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
		
		/* Spark.staticFiles.location("/public"); */
		
		ControllerCategoria controllerCategoria=new ControllerCategoria();
		
		Spark.get("/", ControllerLogin::show, transformer);
		Spark.post("/", ControllerLogin::login, transformer);
		Spark.get("/home", ControllerHome::home, transformer);		
		Spark.get("/entidad/search", ControllerSearchEntity::buscarEntidades, transformer);
		Spark.get("/operacionDeEgreso/new", ControllerOperacionEgreso::show, transformer);
		Spark.post("/operacionDeEgreso/new", ControllerOperacionEgreso::cargarOperacionEgreso, transformer);
		Spark.get("/operacionDeEgreso/new/2", ControllerOperacionEgreso::show2, transformer);
		Spark.post("/operacionDeEgreso/new/2", ControllerOperacionEgreso::cargarOperacionEgreso2, transformer);
		Spark.get("/operacionDeEgreso/show", ControllerOperacionEgreso::mostrarOperacionEgreso, transformer);
		Spark.get("/presupuesto/new", ControllerPresupuesto::show, transformer);
		Spark.post("/presupuesto/new", ControllerPresupuesto::cargarPresupuesto, transformer);
		Spark.post("/categoria/crearCategoria",controllerCategoria::cargarCategoria);
		Spark.get("/categoria",ControllerCategoria::getCategoria,transformer);
		Spark.get("/categoria/asociarEntidad",ControllerCategoria::asociarConEntidad,transformer);
		Spark.get("/entidad/new",ControllerCrearEntidad::show,transformer);
		Spark.post("/entidad/new",ControllerCrearEntidad::crear,transformer);
		Spark.get("/entidad/new/juridica",ControllerCrearEntidadJuridica::show,transformer);
		Spark.post("/entidad/new/juridica",ControllerCrearEntidadJuridica::crear,transformer);
		Spark.get("/entidad/new/base",ControllerCrearEntidadBase::show,transformer);
		Spark.post("/entidad/new/base",ControllerCrearEntidadBase::crear,transformer);
		
	}
}
