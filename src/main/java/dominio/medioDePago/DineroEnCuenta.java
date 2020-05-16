package dominio.medioDePago;
import dominio.operacionDeEgreso.*;

public class DineroEnCuenta implements MedioDePago{
    int nroCuenta;

    public DineroEnCuenta (int nroCuenta){
        this.nroCuenta = nroCuenta;
    }

    public void pagar(OperacionEgreso egreso){
        //Comportamiento !=
        System.out.println("El pago se hará con el Dinero en Cuenta.");
    }
}
