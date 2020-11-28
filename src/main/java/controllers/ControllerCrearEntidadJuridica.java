package controllers;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dominio.categoriaEntidad.CategoriaEntidad;
import dominio.organizacion.EntidadJuridica;
import dominio.organizacion.RepoEntidades;
import dominio.organizacion.Tipo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.Enumeration;


public class ControllerCrearEntidadJuridica implements WithGlobalEntityManager, TransactionalOps {
	public ModelAndView show(Request req, Response res) {
		return new ModelAndView(null, "crearEntidadJuridica.hbs");
	}
	
	
	public ModelAndView crear(Request req, Response res) {
		String nombreFic = req.queryParams("nombreFicticio");
		String razonSoc = req.queryParams("razonSocial");
		int cuit = Integer.parseInt(req.queryParams("CUIT"));
		String direccion = req.queryParams("direccion");
		int codInscripto = Integer.parseInt(req.queryParams("codInscripto"));
		Tipo tipo = Tipo.valueOf(req.queryParams("Tipo"));
		
		EntidadJuridica entidad = new EntidadJuridica(nombreFic, razonSoc, cuit, direccion, codInscripto, tipo, new CategoriaEntidad(""));
		
		
		withTransaction(() ->{
			RepoEntidades.getInstance().agregarEntidadJuridica(entidad);
		});
		
		res.redirect("/home");
		
		return null;
	}
	
}
