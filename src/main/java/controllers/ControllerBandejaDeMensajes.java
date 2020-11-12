package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dominio.mensajes.Mensaje;
import dominio.usuario.RepoUsuarios;
import dominio.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerBandejaDeMensajes {
    public ModelAndView show(Request req, Response res) {
    	String nombre = req.cookie("usuario_logueado");
    	Usuario usuario = RepoUsuarios.getInstance().getUsuarios().stream().filter(user->user.seLlama(nombre)).collect(Collectors.toList()).get(0);
    	Map<String,List<Mensaje>> model = new HashMap<>();
    	List<Mensaje> mensajes = usuario.getMensajes();
    	model.put("mensaje", mensajes);
        return new ModelAndView(null, "bandejaDeMensajes.hbs");
    }
}

