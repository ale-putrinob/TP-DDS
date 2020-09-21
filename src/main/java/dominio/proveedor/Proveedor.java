package dominio.proveedor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Proveedor {
	
	@Id
	@GeneratedValue
	private Long proveedor_id;
	
	String nombreApellido;
	String razonSocial;
	int dni;
	int cuil;
	/*agregarlo dps*/
	int dirPostal;
	
	public Proveedor(String nombreApellido,String razonSocial, int dni, int cuil, int dirPostal) {
		this.nombreApellido=nombreApellido;
		this.razonSocial=razonSocial;
		this.dni=dni;
		this.cuil=cuil;
		this.dirPostal=dirPostal;
	};
}
