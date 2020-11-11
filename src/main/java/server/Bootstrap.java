package server;

import java.util.ArrayList;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dominio.mensajes.Mensaje;
import dominio.proveedor.Proveedor;
import dominio.usuario.Usuario;

class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
	
	public static void main(String[] args) {
		new Bootstrap().init();
	}
	
	public void init() {
		withTransaction(() ->{
			Usuario usuario = new Usuario("admin", "bocacampeon", true, new ArrayList<Mensaje>());
			persist(usuario);
			
			Proveedor proveedor = new Proveedor("Juan Peron","JDP",45678978,2045678889,"1567","Evita", 31, 2, 'A');
			persist(proveedor);
		});
		
	}
}
