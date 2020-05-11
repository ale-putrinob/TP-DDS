package dominio;

public class TarjetaDeDebito implements MedioDePago{
    //Si es VISA o MC, según MercadoPago
    String empresaFinanciera;
    Long nroDeTarjeta;

    public TarjetaDeDebito (String unaEmpresaFinanciera, Long unNroDeTarjeta) {
        this.empresaFinanciera = unaEmpresaFinanciera;
        this.nroDeTarjeta = unNroDeTarjeta;
    }

}
