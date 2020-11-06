package server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String args[]){
		String port = System.getenv("PORT");
		Spark.port(port == null ? 9000 : Integer.parseInt(port));
		Router.configure();
		DebugScreen.enableDebugScreen();
		Spark.init();
		
	}
}
