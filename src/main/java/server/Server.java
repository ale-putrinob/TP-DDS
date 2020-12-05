package server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) throws Exception {
		if (args[0].equals("Server")) {
			Spark.port(9000);
			DebugScreen.enableDebugScreen();
			Router.configure();
		} else if (args[0].equals("Jobs")){
			JobScheduler.execute(args);
		}
	}
}
