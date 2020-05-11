package dominio;

public class TarjetaDeCredito implements MedioDePago {
    //Si es VISA o MC, según MercadoPago
    String empresaFinanciera;
    Long nroDeTarjeta;

    public TarjetaDeCredito (String unaEmpresaFinanciera, Long unNroDeTarjeta) {
        this.empresaFinanciera = unaEmpresaFinanciera;
        this.nroDeTarjeta = unNroDeTarjeta;
    }

}