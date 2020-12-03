package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import dominio.mensajes.Mensaje;
import dominio.organizacion.Entidad;
import dominio.organizacion.RepoEntidades;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerSearchEntity implements WithGlobalEntityManager{
	public static ModelAndView buscarEntidades(Request req, Response res) {

		Map<String,List<Entidad>> model = new HashMap<>();
    	List<Entidad> entidades = RepoEntidades.getInstance().getEntidades();
		
		model.put("entidades", entidades);
		
		return new ModelAndView(model,"buscarEntidades.hbs");
	}
}
