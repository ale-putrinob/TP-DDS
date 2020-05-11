package dominio;

public class Efectivo implements MedioDePago{
    //Para diferenciar si es Rapipago, Pago fácil u otra
    String redDeCobranza;

    public Efectivo (String unaRedDeCobranza){
        this.redDeCobranza = unaRedDeCobranza;
    }
}
