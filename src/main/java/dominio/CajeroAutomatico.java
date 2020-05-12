package dominio;

public class CajeroAutomatico implements MedioDePago{
    //Para diferenciar si es Link, o Banelco
    String redInterbancaria;

    public CajeroAutomatico (String unaRedInterbancaria){
        this.redInterbancaria = unaRedInterbancaria;
    }

    public void pagar(OperacionEgreso egreso){
        //Comportamiento !=
        System.out.println("El pago se hará mediante Cajero automático.");
    }
}
