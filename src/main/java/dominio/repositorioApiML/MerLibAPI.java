package dominio.repositorioApiML;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import dominio.moneda.TipoMoneda;

import javax.ws.rs.core.MediaType;

public class MerLibAPI {
    private static final String API_MERCADOLIBRE = "https://api.mercadolibre.com";
    private static final String PAISES = "/classified_locations/countries/";
    private static final String PROVINCIAS = "classified_locations/states/";
    private static final String CIUDADES = "classified_locations/cities/";
    private static final String MONEDAS = "currencies/";
    private static Gson gson = new Gson();

    public static Ciudad transformarACiudad(String json) {
        return gson.fromJson(json, Ciudad.class);
    }
    public static TipoMoneda transformarAMoneda(String json) {
        return gson.fromJson(json, TipoMoneda.class);
    }

    //Función encargada de hacer la request a mercadolibre
    public static String requestMerLib (String urlMerLib, String zonaGeografica, String datoASolicitar) {
        Client cliente = Client.create();
        ClientResponse respuesta;
        respuesta = cliente.resource(urlMerLib).path(zonaGeografica+datoASolicitar)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        String json = respuesta.getEntity(String.class);
        //System.out.printf(json);
        return json;
    }

    //Traemos una única ciudad pasandole por parámetro el ID
    public static Ciudad getUnaCiudad(String idCiudad){
        //nuestro JSON es solicitud
        String solicitud = requestMerLib(API_MERCADOLIBRE,CIUDADES,idCiudad);
        Ciudad ciudad = transformarACiudad(solicitud);
        return ciudad;
    }

    //Traemos una única moneda de la api mediante su ID
    public static TipoMoneda getUnaMoneda(String idMoneda) {
        //nuestro JSON es solicitud
        String solicitud = requestMerLib(API_MERCADOLIBRE, MONEDAS, idMoneda);
        TipoMoneda unaMoneda = transformarAMoneda(solicitud);
        return unaMoneda;
    }

    //Traer todos los paises de la API
    public String getPaises(){
        String solicitud = requestMerLib(API_MERCADOLIBRE,PAISES,"");
        return solicitud;
    }
    //Traer todas las monedas de la API
    public String getMonedas(){
        String solicitud = requestMerLib(API_MERCADOLIBRE,MONEDAS,"");
        return solicitud;
    }

    /*//Traemos país por su ID
    public Pais getUnPais(String pais){
        String idPais = pais;
        String solicitud = requestMerLib(API_MERCADOLIBRE,PAISES,idPais);
        //Hay que hacer la transformación de la solicitud a algo tipo país
        return pais;
    }
    public Provincia getProvincia(String unaProvincia){
        String idProvincia = unaProvincia;
        String solicitud = requestMerLib(API_MERCADOLIBRE,PAISES,idProvincia);
        //Hay que hacer la transformación de la solicitud a algo tipo provincia
        return provincia;
    }*/


    /*
    //Traemos una única moneda de la api mediante su ID
    public static String getUnaMoneda(String unaMoneda){
        cliente = Client.create();
        String moneda = unaMoneda;
        respuesta = cliente.resource(API_MERCADOLIBRE).path(MONEDAS+moneda)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        String json = respuesta.getEntity(String.class);

        return json;
    }*/

    /*public static String getCiudad(String ciudad){
        cliente = Client.create();
        String id = ciudad;
        respuesta = cliente.resource(API_MERCADOLIBRE).path(CIUDADES+id)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        String json = respuesta.getEntity(String.class);
        //System.out.printf(json);
        return json;
    }*/
}