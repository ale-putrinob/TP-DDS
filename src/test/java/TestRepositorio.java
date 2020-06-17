import com.sun.jersey.api.client.ClientResponse;
import dominio.repositorioApiML.ClienteRepositorio;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestRepositorio {

    ClienteRepositorio solicitudCliente;

    @Before
    public void init() {this.solicitudCliente = new ClienteRepositorio();}

    @Test
    public void obtenemosPaisConParametro(){
        //Vamos a testear como funciona la API cuando damos los datos de un País
        ClientResponse respuesta = this.solicitudCliente.getPaises("argentina");
        //Acordemosnos lo que significaban las respuestas que obteníamos desde HTTP
        Assert.assertEquals(respuesta.getStatus(),200);
        String json = respuesta.getEntity(String.class);
        Assert.assertTrue(json.contains(""));
        Assert.assertTrue(json.contains(""));

    }
}