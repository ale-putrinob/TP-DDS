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


public class ControllerCrearEntidadJuridica implements WithGlobalEntityManager, TransactionalOps {
	public ModelAndView show(Request req, Response res) {
		return new ModelAndView(null, "crearEntidadJuridica.hbs");
	}
	
	
	public ModelAndView crear(Request req, Response res) {
		String nombreFic = req.queryParams("nombreFicticio");
		String razonSoc = req.queryParams("razonSocial");
		Integer cuit = new Integer(req.queryParams("CUIT"));
		String direccion = req.queryParams("direccion");
		Integer codInscripto = new Integer(req.queryParams("codInscripto"));
		String tipoString = req.queryParams("Tipo");
		Tipo tipo;

		if(tipoString.equals("1")) tipo = Tipo.EmpresaMicro; 
		else if(tipoString.equals("2")) tipo = Tipo.EmpresaPequenia;
		else if(tipoString.equals("3")) tipo = Tipo.EmpresaMedianaTramo1;
		else if(tipoString.equals("4")) tipo = Tipo.EmpresaMedianaTramo2;
		else if(tipoString.equals("5")) tipo = Tipo.OSC;
		else tipo = null;
		
		
		EntidadJuridica entidad = new EntidadJuridica(nombreFic, razonSoc, cuit, direccion, codInscripto, tipo, new CategoriaEntidad(""));
		
		
		withTransaction(() ->{
			RepoEntidades.getInstance().agregarEntidadJuridica(entidad);
		});
		
		res.redirect("/home");
		
		return null;
	}
	
}
