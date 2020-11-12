package controllers;

import java.util.HashMap;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dominio.organizacion.EntidadBase;
import dominio.organizacion.EntidadJuridica;
import dominio.organizacion.RepoEntidades;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerCrearEntidadBase implements WithGlobalEntityManager, TransactionalOps{
	public ModelAndView show(Request req, Response res) {
		List<EntidadJuridica> entidades = RepoEntidades.getInstance().getEntidadesJuridicas();
		
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("entidades", entidades);
		
		return new ModelAndView(viewModel, "crearEntidadBase.hbs");
	}
	
	public ModelAndView crear(Request req, Response res) {
		String nombreFic = req.queryParams("nombreFicticio");
		String razonSoc = req.queryParams("razonSocial");
		String id_dependencia = req.queryParams("Dependencia").split(" ")[0];
		
		EntidadJuridica dependencia = RepoEntidades.getInstance().buscarEntidadJuridica(id_dependencia);
		
		EntidadBase entidad = new EntidadBase(nombreFic, razonSoc, dependencia, null);
		
		withTransaction(() ->{
			RepoEntidades.getInstance().agregarEntidadBase(entidad);
		});
		
		res.redirect("/home");
		
		return null;
	}
}
