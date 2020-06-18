package dominio.generadorDeObjetoFromJson;

import com.google.gson.Gson;

import dominio.moneda.TipoMoneda;
import dominio.repositorioApiML.CiudadFromJson;
import dominio.repositorioApiML.ClienteRepositorio;

public class GeneradorDeObjetoFromJson {
	Gson gson = new Gson();
	
	public CiudadFromJson transformarACiudad(String idCiudad) {
		String json = ClienteRepositorio.getCiudad(idCiudad);
		return gson.fromJson(json, CiudadFromJson.class);
	}
	
	public TipoMoneda transformarAMoneda(String idMoneda) {
		String json = ClienteRepositorio.getUnaMoneda(idMoneda);
		return gson.fromJson(json, TipoMoneda.class);
	}
}
