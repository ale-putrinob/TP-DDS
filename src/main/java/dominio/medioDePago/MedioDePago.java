package dominio.medioDePago;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class MedioDePago {
	
	@Id
	int id;
	/*agregarlo despues*/
	@Transient
	TiposDePago tipo;

    public MedioDePago(TiposDePago tipo, int identificador) {
        this.tipo = tipo;
        this.id = identificador;
    }
}