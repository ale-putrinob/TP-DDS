package dominio.repositorioApiML;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import javax.ws.rs.core.MediaType;

public class ClienteRepositorio {
    private Client cliente;
    private static final String API_MERCADOLIBRE = "https://api.mercadolibre.com/";
    private static final String PAISES = "/classified_locations/countries";
    private static final String PROVINCIAS = "";
    private static final String CIUDADES = "";

    public void crearCliente() {
        this.cliente = Client.create();
    }

    public ClientResponse getPaises(String pais){
        ClientResponse respuesta = this.cliente.resource(API_MERCADOLIBRE).path(PAISES)
                .queryParam("Country_id", pais)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        return respuesta;
    }

    public ClientResponse getProvincias(String provincia){
        ClientResponse respuesta = this.cliente.resource(API_MERCADOLIBRE).path(PROVINCIAS)
                .queryParam("State_id", provincia)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        return respuesta;
    }

    public ClientResponse getCiudades(String ciudad){
        ClientResponse respuesta = this.cliente.resource(API_MERCADOLIBRE).path(CIUDADES)
                .queryParam("City_id", ciudad)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        return respuesta;
    }

}