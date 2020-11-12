package dominio.medioDePago;



import javax.persistence.Entity;
import javax.persistence.Enumerated;

import dominio.persistentEntity.PersistentEntity;

@Entity
public class MedioDePago extends PersistentEntity{
	
	@Enumerated
	TiposDePago tipo;
	int identificador;
	
    public MedioDePago(TiposDePago tipo, int identificador) {
        this.tipo = tipo;
        this.identificador = identificador;
    }
    
	public MedioDePago() {};

}