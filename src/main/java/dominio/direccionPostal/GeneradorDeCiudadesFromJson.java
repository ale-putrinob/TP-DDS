package dominio.direccionPostal;

import com.google.gson.Gson;

import dominio.repositorioApiML.CiudadFromJson;
import dominio.repositorioApiML.ClienteRepositorio;

public class GeneradorDeCiudadesFromJson {
Gson gson = new Gson();
	
	public CiudadFromJson transformarACiudad(String idCiudad) {
		String json = ClienteRepositorio.getCiudad(idCiudad);
		return gson.fromJson(json, CiudadFromJson.class);
	}
}
