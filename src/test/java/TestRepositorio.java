import dominio.proveedor.Proveedor;
import dominio.repositorioApiML.MerLibAPI;
import dominio.repositorioApiML.Pais;
import dominio.repositorioApiML.Provincia;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestRepositorio {
    static Proveedor proveedor;
    static MerLibAPI solicitudCliente;

    @BeforeClass
    public static void init() {
        solicitudCliente = new MerLibAPI();
        proveedor = new Proveedor("Diego A. Maradona", "Diegote SRL",16354646, 2045678889,
                "TUxBQ0NBUGZlZG1sYQ", "La paternola", 1994, 3, 'D');
    }

    @Test
    public void laCiudadEsCorrecta(){
        Assert.assertEquals(proveedor.getCiudad(), "Capital Federal");
    }

    @Test
    public void laProvinciaEsCorrecta(){
        Pais pais = new Pais("AR", "Argentina");
        Provincia provincia = new Provincia("TUxBUENBUGw3M2E1", "Capital Federal", pais);
        Assert.assertEquals(provincia.getName(), "Capital Federal");
    }

    @Test
    public void elPaisEsCorrecto(){
        Pais pais = new Pais("AR", "Argentina");
        Assert.assertEquals(pais.getName(), "Argentina");
    }

   /****************************************************
    Si trabajamos con Dirección Postal  corresponden los test y condiciones de abajo
    **************************************************
    DireccionPostal direccionPostal;
    static MerLibAPI solicitudCliente;

    @BeforeClass
    public static void init() {solicitudCliente = new MerLibAPI();}

    @Test
    public void laCiudadEsCorrecta(){
        direccionPostal = new DireccionPostal("cualquiera", 1231, 12, 'A',
                "TUxBQ0NBUGZlZG1sYQ");
        Assert.assertEquals(direccionPostal.getCiudad(), "Capital Federal");
    }

    @Test
    public void laProvinciaEsCorrecta(){
        direccionPostal = new DireccionPostal("cualquiera", 1231, 12, 'A',
                "TUxVQ0NBQjY1MmQ1");
        Assert.assertEquals(direccionPostal.getProvincia(), "Rocha");
    }

    @Test
    public void elPaisEsCorrecto(){
        direccionPostal = new DireccionPostal("cualquiera", 1231, 12, 'A',
                "TUxVQ0NBQjY1MmQ1");
        Assert.assertEquals(direccionPostal.getPais(), "Uruguay");
    }*/

}