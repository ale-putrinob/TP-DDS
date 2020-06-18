package dominio.repositorioApiML;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import javax.ws.rs.core.MediaType;

public class ClienteRepositorio {
    private static Client cliente;
    private static final String API_MERCADOLIBRE = "https://api.mercadolibre.com";
    private static final String PAISES = "/classified_locations/countries";
    private static final String PROVINCIAS = "/classified_locations/states/";
    private static final String CIUDADES = "/classified_locations/cities/";
    private static final String MONEDAS = "/currencies/";
    private static ClientResponse respuesta;

    public static String getCiudad(String ciudad){
        cliente = Client.create();
        String id = ciudad;
        respuesta = cliente.resource(API_MERCADOLIBRE).path(CIUDADES+id)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        String json = respuesta.getEntity(String.class);
        //System.out.printf(json);
        return json;
    }

    public ClientResponse getPaises(){
        respuesta = this.cliente.resource(API_MERCADOLIBRE).path(PAISES)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        return respuesta;
    }

    //Traemos país por su nombre
    public ClientResponse getUnPais(String pais){
        String idPais = pais;
        respuesta = this.cliente.resource(API_MERCADOLIBRE).path(PAISES+idPais)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        return respuesta;
    }

    public ClientResponse getProvincia(String provincia){
        String idProvincia = provincia;
        respuesta = this.cliente.resource(API_MERCADOLIBRE).path(PROVINCIAS+idProvincia)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        return respuesta;
    }

    //Traer todas las monedas de la API
    public ClientResponse getMonedas(){
        respuesta = this.cliente.resource(API_MERCADOLIBRE).path(MONEDAS)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        return respuesta;
    }

    //Traemos una única moneda de la api mediante su ID
    public static String getUnaMoneda(String unaMoneda){
        cliente = Client.create();
        String moneda = unaMoneda;
        respuesta = cliente.resource(API_MERCADOLIBRE).path(MONEDAS+moneda)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        String json = respuesta.getEntity(String.class);
        //System.out.printf(json);
        return json;
    }
}