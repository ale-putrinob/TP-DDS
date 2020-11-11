package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import dominio.usuario.RepoUsuarios;
import dominio.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerLogin implements WithGlobalEntityManager {
	static Map<String, Object> modelo = new HashMap<>();
	
	public static ModelAndView show(Request req, Response res) {
		/*Usuario admin = new Usuario("admin", "contraseniasegura", false, new ArrayList<Mensaje>());
		RepoUsuarios.getInstance().agregarUsuario(admin);*/
		return new ModelAndView(modelo, "login.hbs");
	}
	
	public static ModelAndView login(Request req, Response res) {
		
		String nombre = req.queryParams("usuario");
		String password = req.queryParams("password");
		
		res.cookie("usuario_logueado", nombre);
		
		List<Usuario> usuarios = RepoUsuarios.getInstance().getUsuarios();
		
		//if(nombre.equals("admin") && password.equals("1234"))
		if(usuarios.stream().anyMatch(user -> user.matchea(nombre, password)))
			res.redirect("/home");
		else{
			res.redirect("/");
			modelo.put("error_logueo", "Usuario o password incorrectos");
		}
		
		return null;
	}
}
