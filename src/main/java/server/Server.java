package server;

import dominio.operacionDeEgreso.RepositorioEgresos;
import spark.Spark;
import spark.debug.DebugScreen;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class Server {
	public static void main(String[] args) throws Exception {

		Map<String, String> env = System.getenv();
		Map<String, Object> configOverrides = new HashMap<String, Object>();
		for (String envName : env.keySet()) {
			if (envName.contains("USERNAME")) {
				configOverrides.put("hibernate.connection.username", env.get(envName));
			}
			if (envName.contains("PASSWORD")) {
				configOverrides.put("hibernate.connection.password", env.get(envName));
			}
			// You can put more code in here to populate configOverrides...
		}

		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("db", configOverrides);

		if (args.length == 0) {
			new Bootstrap().init();
			Spark.port(9000);
			DebugScreen.enableDebugScreen();
			Router.configure();
		} else if (args[0].equals("Jobs")){
			RepositorioEgresos.getInstance().validarOperacionesPendientes();
		}
	}
}
