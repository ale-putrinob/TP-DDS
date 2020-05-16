package dominio.medioDePago;
import dominio.operacionDeEgreso.*;

public class TarjetaDeDebito implements MedioDePago{
    //Si es VISA o MC, según MercadoPago
    String empresaFinanciera;
    Long nroDeTarjeta;

    public TarjetaDeDebito (String unaEmpresaFinanciera, Long unNroDeTarjeta) {
        this.empresaFinanciera = unaEmpresaFinanciera;
        this.nroDeTarjeta = unNroDeTarjeta;
    }

    public void pagar(OperacionEgreso egreso){
        //Comportamiento !=
        System.out.println("El pago se hará con Tarjeta de Débito.");
    }
}
