package server;

import controllers.*;
import org.apache.commons.lang3.StringUtils;

<<<<<<< HEAD
import controllers.ControllerBandejaDeMensajes;
import controllers.ControllerCategoria;
import controllers.ControllerCrearEntidad;
import controllers.ControllerCrearEntidadBase;
import controllers.ControllerCrearEntidadJuridica;
import controllers.ControllerHome;
import controllers.ControllerLogin;
import controllers.ControllerOperacionEgreso;
import controllers.ControllerPresupuesto;
import controllers.ControllerSearchEntity;
=======
>>>>>>> 4630423d4c0c74bd17d70d03163444c5e1cfadfb
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
	public static void configure() {
		//Spark.before((request, response) -> {
		//	if((StringUtils.isEmpty(request.cookie("usuario_logueado"))) && !(request.pathInfo().equals("/")))
		//		response.redirect("/");	
		//});
		
		HandlebarsTemplateEngine transformer = 
				new HandlebarsTemplateEngine();
		
		/*Spark.staticFiles.location("/public");*/
		
		ControllerCategoria controllerCategoria=new ControllerCategoria();
		ControllerProveedores controllerProveedores=new ControllerProveedores();
		ControllerCrearEntidadJuridica controllerCrearEntidadJuridica = new ControllerCrearEntidadJuridica();
		ControllerCrearEntidadBase controllerCrearEntidadBase = new ControllerCrearEntidadBase();
		ControllerBandejaDeMensajes controllerMensajes = new ControllerBandejaDeMensajes();
		
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
		Spark.get("/entidad/new/juridica",controllerCrearEntidadJuridica::show,transformer);
		Spark.post("/entidad/new/juridica",controllerCrearEntidadJuridica::crear,transformer);
		Spark.get("/entidad/new/base",controllerCrearEntidadBase::show,transformer);
		Spark.post("/entidad/new/base",controllerCrearEntidadBase::crear,transformer);
		Spark.get("/bandejaDeMensajes", controllerMensajes::show, transformer);
		Spark.get("/proveedores/show",controllerProveedores::obtenerProveedor,transformer);
		Spark.post("/proveedores/new", controllerProveedores::cargarProveedores,transformer);
	}
}
