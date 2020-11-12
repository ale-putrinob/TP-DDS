package controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

//import dominio.categoriaEntidad.BloquearNuevasEntidadesBase;
import dominio.categoriaEntidad.CategoriaEntidad;
import dominio.categoriaEntidad.RepoCategoria;
import dominio.organizacion.EntidadBase;
import dominio.organizacion.EntidadJuridica;
import dominio.organizacion.RepoEntidades;
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
			RepoCategoria.getInstance().agregarCategoria(categoria);;
		});
		
		res.redirect("/categoria");
		return null;
	}
	public Void asociarEntidad(Request req, Response res) {
		String idCategoria = req.queryParams("nombre").split(" ")[0];
		
		//String bloqueo1 = req.queryParams("bloqueo1"); 
		//String bloqueo2 = req.queryParams("bloqueo2"); 
		//String bloqueo3 = req.queryParams("bloqueo3"); 
		
		withTransaction(() ->{
			RepoCategoria.getInstance().findCategoria(idCategoria);
		});
		
		res.redirect("/home");
		return null;
	}
	public static ModelAndView asociarConEntidad(Request req, Response res) {
		List<EntidadJuridica> entidadesJuridicas=RepoEntidades.getInstance().getEntidadesJuridicas();
		List<EntidadBase> entidadesBases=RepoEntidades.getInstance().getEntidadesBase();
		List<CategoriaEntidad> categorias=RepoCategoria.getInstance().getCategorias();
		
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("entidadesJuridicas", entidadesJuridicas);
		viewModel.put("entidadesBases", entidadesBases);
		viewModel.put("categorias", categorias);
		
		return new ModelAndView(viewModel, "asociarCategoriaConEntidad.hbs");
	}
}
