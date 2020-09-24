package dominio.medioDePago;



import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class MedioDePago {
	
	@Id
	int id;
	/*agregarlo despues*/
	@Enumerated
	TiposDePago tipo;

    public MedioDePago(TiposDePago tipo, int identificador) {
        this.tipo = tipo;
        this.id = identificador;
    }
}