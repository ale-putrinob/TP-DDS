package dominio.medioDePago;
import dominio.operacionDeEgreso.*;

public class Efectivo implements MedioDePago{
    //Para diferenciar si es Rapipago, Pago fácil u otra
    String redDeCobranza;
    int nroTransaccion;

    public Efectivo (String unaRedDeCobranza, int nroTransaccion){
        this.redDeCobranza = unaRedDeCobranza;
        this.nroTransaccion = nroTransaccion;
    }

    public void pagar(OperacionEgreso egreso){
        //Comportamiento !=
        System.out.println("El pago se hará en Efectivo.");
    }
}
