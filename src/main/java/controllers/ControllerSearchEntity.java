package controllers;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerSearchEntity implements WithGlobalEntityManager{
	public static ModelAndView buscarEntidades(Request req, Response res) {

/*		Map<String, List<Entidad>> model = new HashMap<>();
		@SuppressWarnings("unchecked")
		List<Entidad> entidades = entityManager()
				.createQuery("from Entidad")
				.getResultList();
		
		model.put("entidades", entidades);
*/		
		return new ModelAndView(null,"buscarEntidades.hbs");
	}
}
