package dominio.medioDePago;

public abstract class MedioDePago {
    TiposDePago tipo;
    String identificador;

    public MedioDePago(TiposDePago tipo, String identificador) {
        this.tipo = tipo;
        this.identificador = identificador;
    }
}