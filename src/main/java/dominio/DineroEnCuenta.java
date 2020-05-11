package dominio;

public class DineroEnCuenta implements MedioDePago{
    //Para diferenciar si es Link, o Banelco
    decimal dineroAPagar;

    public DineroEnCuenta (decimal unaCantidad){
        this.dineroAPagar = unaCantidad;
    }
}
