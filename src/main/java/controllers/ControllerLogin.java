package controllers;

import java.util.ArrayList;
import java.util.List;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import dominio.mensajes.Mensaje;
import dominio.usuario.RepoUsuarios;
import dominio.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerLogin implements WithGlobalEntityManager {
	
	
	public static ModelAndView show(Request req, Response res) {
		Usuario admin = new Usuario("admin", "contraseniasegura", false, new ArrayList<Mensaje>());
		RepoUsuarios.getInstance().agregarUsuario(admin);
		return new ModelAndView(null, "login.hbs");
	}
	
	public static ModelAndView login(Request req, Response res) {
		
		String nombre = req.queryParams("usuario");
		String password = req.queryParams("password");
		
		res.cookie("usuario_logueado", nombre);
		
		List<Usuario> usuarios = RepoUsuarios.getInstance().getUsuarios();
		
		
		if(usuarios.stream().anyMatch(user -> user.matchea(nombre, password)))
			res.redirect("/home");
		else{
			res.redirect("/");
		}
		
		return null;
	}
}
