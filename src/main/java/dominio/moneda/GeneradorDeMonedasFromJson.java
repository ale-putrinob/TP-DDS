package dominio.moneda;

import com.google.gson.Gson;

import dominio.repositorioApiML.ClienteRepositorio;

public class GeneradorDeMonedasFromJson {
	Gson gson = new Gson();
	
	public TipoMoneda transformarAMoneda(String idMoneda) {
		String json = ClienteRepositorio.getUnaMoneda(idMoneda);
		return gson.fromJson(json, TipoMoneda.class);
	}
}
