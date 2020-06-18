package dominio.direccionPostal;

import dominio.repositorioApiML.CiudadFromJson;

public class DireccionPostal {
    String calle;
    int altura;
    int piso;
    char departamento;
    String ciudad;
    String provincia;
    String pais;
    CiudadFromJson ciudadFromJson;
    GeneradorDeCiudadesFromJson generadorDeCiudad = new GeneradorDeCiudadesFromJson();

    public DireccionPostal(String calle, int altura, int piso, char departamento, String idCiudad) {
        ciudadFromJson = generadorDeCiudad.transformarACiudad(idCiudad);
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

