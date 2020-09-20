package dominio.proveedor;

import javax.persistence.Transient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import dominio.direccionPostal.DireccionPostal;

@Entity
public class Proveedor {
	
	@Id
	@GeneratedValue
	private Long id_proveedor;
	
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
