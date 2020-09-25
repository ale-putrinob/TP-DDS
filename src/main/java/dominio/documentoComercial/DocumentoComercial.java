package dominio.documentoComercial;

import javax.persistence.Entity;
import dominio.persistentEntity.PersistentEntity;

@Entity
public class DocumentoComercial extends PersistentEntity{
	
	String tipo;
	int numero;
	
	public DocumentoComercial(String tipo, int numero) {
		this.tipo=tipo;
		this.numero=numero;
	};


}
