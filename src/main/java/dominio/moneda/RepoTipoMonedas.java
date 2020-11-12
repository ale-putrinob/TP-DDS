package dominio.moneda;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepoTipoMonedas implements WithGlobalEntityManager{
	private final static RepoTipoMonedas INSTANCE = new RepoTipoMonedas();
	 
	public static RepoTipoMonedas getInstance() {
		return INSTANCE;
	}	

	public TipoMoneda findTipoMoneda(String id_moneda) {
		return null;
	}
}