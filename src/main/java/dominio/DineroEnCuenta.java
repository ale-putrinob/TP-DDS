package dominio;

import java.math.BigDecimal;

public class DineroEnCuenta implements MedioDePago{
    BigDecimal dineroAPagar;

    public DineroEnCuenta (BigDecimal unaCantidad){
        this.dineroAPagar = unaCantidad;
    }

    public void pagar(OperacionEgreso egreso){
        //Comportamiento !=
        System.out.println("El pago se hará con el Dinero en Cuenta.");
    }
}
