package dominio;

public class CajeroAutomatico implements MedioDePago{
    //Para diferenciar si es Link, o Banelco
    String redInterbancaria;

    public CajeroAutomatico (String unaRedInterbancaria){
        this.redInterbancaria = unaRedInterbancaria;
    }
}
