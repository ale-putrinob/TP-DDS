package dominio.direccionPostal;

import dominio.repositorioApiML.Ciudad;
import dominio.repositorioApiML.MerLibAPI;


public class DireccionPostal {
    String calle;
    int altura;
    int piso;
    char departamento;
    String provincia;
    String pais;
    String ciudad;

    public DireccionPostal(String calle, int altura, int piso, char departamento, String idCiudad) {
        Ciudad ciudad = MerLibAPI.getUnaCiudad(idCiudad);
        this.calle = calle;
        this.altura = altura;
        this.piso = piso;
        this.departamento = departamento;
        this.ciudad = ciudad.getName();
        this.provincia = ciudad.getState().getName();
        this.pais = ciudad.getCountry().getName();
    };

    //Getters para TSTs
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

