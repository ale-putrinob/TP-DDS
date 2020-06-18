package dominio.direccionPostal;

import com.google.gson.Gson;
import dominio.repositorioApiML.Ciudad;
import dominio.repositorioApiML.ClienteRepositorio;

public class DireccionPostal {
    String calle;
    int altura;
    int piso;
    char departamento;
    String ciudad;
    String provincia;
    String pais;
    String json;
    Ciudad ciudadFromJson;
    Gson gson = new Gson();

    public DireccionPostal(String calle, int altura, int piso, char departamento, String idCiudad) {
        this.json = ClienteRepositorio.getCiudad(idCiudad);
        ciudadFromJson = gson.fromJson(this.json, Ciudad.class);
        System.out.printf("2");
        this.calle = calle;
        this.altura = altura;
        this.piso = piso;
        this.departamento = departamento;
        this.ciudad = ciudadFromJson.getName();
        this.provincia = ciudadFromJson.getState().getName();
        this.pais = ciudadFromJson.getCountry().getName();
    };

    public String getCiudad() {
        return ciudad;
    }
    public String getProvincia() {
        return provincia;
    }
    public String getPais() {
        return pais;
    }

}

