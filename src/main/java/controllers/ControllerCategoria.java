package controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dominio.categoriaEntidad.BloquearNuevasEntidadesBase;
import dominio.categoriaEntidad.BloquearNuevosEgresos;
import dominio.categoriaEntidad.BloquearSerParteDeEntidadJuridica;
//import dominio.categoriaEntidad.BloquearNuevasEntidadesBase;
import dominio.categoriaEntidad.CategoriaEntidad;
import dominio.categoriaEntidad.RepoCategoria;
import dominio.organizacion.Entidad;
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
		String bloqueo1 = req.queryParams("bloqueo1"); 
		String bloqueo2 = req.queryParams("bloqueo2"); 
		String bloqueo3 = req.queryParams("bloqueo3"); 
		System.out.print(bloqueo1+"---------------------------------------"+bloqueo2+"---------------------------------------"+bloqueo3+"---------------------------------------");
		CategoriaEntidad categoria = new CategoriaEntidad(nombre);
		
		withTransaction(() ->{
			
			if(bloqueo1 != null && bloqueo1.equals("on")) {
				BloquearNuevasEntidadesBase bloquearNuevasEntidadesBase = new BloquearNuevasEntidadesBase();
				entityManager().persist(bloquearNuevasEntidadesBase);
				categoria.agregarFuncionalidad(bloquearNuevasEntidadesBase);
			}
			if(bloqueo2 != null && bloqueo2.equals("on")) {
				BloquearNuevosEgresos bloquearNuevosEgresos = new BloquearNuevosEgresos(5.);
				entityManager().persist(bloquearNuevosEgresos);
				categoria.agregarFuncionalidad(bloquearNuevosEgresos);
			}
			if(bloqueo3 != null && bloqueo3.equals("on")) {
				BloquearSerParteDeEntidadJuridica bloquearSerParteDeEntidadJuridica = new BloquearSerParteDeEntidadJuridica();
				entityManager().persist(bloquearSerParteDeEntidadJuridica);
				categoria.agregarFuncionalidad(bloquearSerParteDeEntidadJuridica);
			}
			
			RepoCategoria.getInstance().agregarCategoria(categoria);
		});
		
		res.redirect("/categoria");
		return null;
	}
	public Void asociarEntidad(Request req, Response res) {
		String idEntidad = req.queryParams("entidad").split(" ")[0];
		Entidad entidad = RepoEntidades.getInstance().findEntidad(idEntidad);
		String idCategoria = req.queryParams("categoria").split(" ")[0];
		CategoriaEntidad categoria=RepoCategoria.getInstance().findCategoria(idCategoria);
		System.out.println(entidad.getCategoria().getNombre()+"-----------------------------");
		entidad.setCategoria(categoria);
		System.out.println(entidad.getCategoria().getNombre()+"--------------------------------");
		
		//String bloqueo1 = req.queryParams("bloqueo1"); 
		//String bloqueo2 = req.queryParams("bloqueo2"); 
		//String bloqueo3 = req.queryParams("bloqueo3"); 
		
		withTransaction(() ->{
			RepoEntidades.getInstance().agregarEntidad(entidad);
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
