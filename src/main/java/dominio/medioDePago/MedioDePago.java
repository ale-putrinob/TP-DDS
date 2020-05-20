package dominio.medioDePago;

public class MedioDePago {
    TiposDePago tipo;
    int identificador;

    public MedioDePago(TiposDePago tipo, int identificador) {
        this.tipo = tipo;
        this.identificador = identificador;
    }
}