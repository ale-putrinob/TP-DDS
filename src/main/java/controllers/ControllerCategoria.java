package controllers;

import java.math.BigDecimal;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

//import dominio.categoriaEntidad.BloquearNuevasEntidadesBase;
import dominio.categoriaEntidad.CategoriaEntidad;
import dominio.categoriaEntidad.RepoCategoria;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerCategoria implements WithGlobalEntityManager, TransactionalOps{
	public static ModelAndView getCategoria(Request req, Response res) {	
		return new ModelAndView(null, "cargarCategoria.hbs");
	}
	public Void cargarCategoria(Request req, Response res) {
		String nombre = req.queryParams("nombre");
		//String bloqueo1 = req.queryParams("bloqueo1"); 
		//String bloqueo2 = req.queryParams("bloqueo2"); 
		//String bloqueo3 = req.queryParams("bloqueo3"); 
		
		CategoriaEntidad categoria = new CategoriaEntidad(nombre);
		
		withTransaction(() ->{
			RepoCategoria.INSTANCE.agregarCategoria(categoria);;
		});
		
		res.redirect("/categoria");
		return null;
	}
	
	public static ModelAndView asociarConEntidad(Request req, Response res) {
		return new ModelAndView(null, "asociarCategoriaConEntidad.hbs");
	}
}
