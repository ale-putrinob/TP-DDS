import dominio.direccionPostal.DireccionPostal;
import dominio.repositorioApiML.MerLibAPI;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestRepositorio {
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
    }

}