package server;

import controllers.*;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.StringUtils;

public class Router {
	public static void configure() {
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
		
		Spark.get("/", ControllerLogin::show, transformer);
		Spark.post("/", ControllerLogin::login, transformer);
		Spark.get("/home", ControllerHome::home, transformer);		
		Spark.get("/entidad/search", ControllerSearchEntity::buscarEntidades, transformer);
		Spark.get("/operacionDeEgreso/new", controllerOperacionEgreso::show, transformer);
		Spark.post("/operacionDeEgreso/new", controllerOperacionEgreso::cargarOperacionEgreso, transformer);
		Spark.get("/operacionDeEgreso/new/2", controllerOperacionEgreso::show2, transformer);
		Spark.post("/operacionDeEgreso/new/2", controllerOperacionEgreso::cargarOperacionEgreso2, transformer);
		Spark.get("/operacionDeEgreso/show", controllerOperacionEgreso::mostrarOperacionEgreso, transformer);
		Spark.get("/presupuesto/new", ControllerPresupuesto::show, transformer);
		Spark.post("/presupuesto/new", ControllerPresupuesto::cargarPresupuesto, transformer);
		Spark.post("/categoria/crearCategoria",controllerCategoria::cargarCategoria);
		Spark.get("/categoria",ControllerCategoria::getCategoria,transformer);
		Spark.get("/categoria/asociarEntidad",ControllerCategoria::asociarConEntidad,transformer);
		Spark.get("/entidad/new",ControllerCrearEntidad::show,transformer);
		Spark.post("/entidad/new",ControllerCrearEntidad::crear,transformer);
		Spark.get("/entidad/new/juridica",controllerCrearEntidadJuridica::show,transformer);
		Spark.post("/entidad/new/juridica",controllerCrearEntidadJuridica::crear,transformer);
		Spark.get("/entidad/new/base",controllerCrearEntidadBase::show,transformer);
		Spark.post("/entidad/new/base",controllerCrearEntidadBase::crear,transformer);
		Spark.get("/bandejaDeMensajes", controllerMensajes::show, transformer);
		Spark.get("/proveedores/new",controllerProveedores::obtenerProveedor,transformer);
		Spark.post("/proveedores/new", controllerProveedores::cargarProveedores,transformer);
	}
}
