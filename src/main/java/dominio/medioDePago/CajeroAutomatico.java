package dominio;

public class CajeroAutomatico implements MedioDePago{
    //Para diferenciar si es Link, o Banelco
    String redInterbancaria;
    int nroCuenta;

    public CajeroAutomatico (String unaRedInterbancaria, int nroCuenta){
        this.redInterbancaria = unaRedInterbancaria;
        this.nroCuenta = nroCuenta;
    }

    public void pagar(OperacionEgreso egreso){
        //Comportamiento !=
        System.out.println("El pago se hará mediante Cajero automático.");
    }
}
