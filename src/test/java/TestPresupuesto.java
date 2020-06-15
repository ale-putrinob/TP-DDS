import dominio.documentoComercial.DocumentoComercial;
import dominio.item.Item;
import dominio.proveedor.Proveedor;
import dominio.presupuesto.Presupuesto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//Revisar
public class TestPresupuesto {
    List <Integer> Items1 = new ArrayList<>();
    List <Integer> Items2 = new ArrayList<>();

    @Before
    public void init(){
        Items1.add(1);
        Items1.add(2);
        Items2.add(2);
        Items2.add(1);
    }
    @Test
    public void sonIguales(){
        Assert.assertEquals(Items1,Items2);
    }
}
