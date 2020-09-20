package dominio.documentoComercial;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DocumentoComercial {
	
	@Id
	@GeneratedValue
	private Long id_documento_comercial;
	
	String tipo;
	int numero;
	
	public DocumentoComercial(String tipo, int numero) {
		this.tipo=tipo;
		this.numero=numero;
	};


}
