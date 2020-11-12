package controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import dominio.organizacion.Tipo;

@Path("query-params")
public class QueryParamsController {
	 
	  @GET
	  public String queryParams(@QueryParam("CUIT") Integer cuit, @QueryParam("codInscripto") Integer codInscripto, 
			  @QueryParam("Tipo") Tipo tipo) {
				return null;
		  
	 }
}

