package dominio.medioDePago;
import dominio.operacionDeEgreso.*;

public interface MedioDePago {
    void pagar(OperacionEgreso egreso);
}
