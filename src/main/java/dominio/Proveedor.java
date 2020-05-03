package dominio;

public class Proveedor {
	String nombreApellido;
	String razonSocial;
	int dni;
	int cuil;
	int dirPostal;
	
	public Proveedor(String nombreApellido,String razonSocial, int dni, int cuil, int dirPostal) {
		this.nombreApellido=nombreApellido;
		this.razonSocial=razonSocial;
		this.dni=dni;
		this.cuil=cuil;
		this.dirPostal=dirPostal;
	};
}
