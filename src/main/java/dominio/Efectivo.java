package dominio;

public class Efectivo implements MedioDePago{
    //Para diferenciar si es Rapipago, Pago fácil u otra
    String redDeCobranza;

    public Efectivo (String unaRedDeCobranza){
        this.redDeCobranza = unaRedDeCobranza;
    }

    public void pagar(OperacionEgreso egreso){
        //Comportamiento !=
        System.out.println("El pago se hará en Efectivo.");
    }
}
