package dominio;


abstract class EntidadJuridica implements Entidad {
	String nombreFicticio;
	int razonSocial;
	int cuit;
	String dirPostal;
	int codInscripto;
	
	public EntidadJuridica(int razonSocial, int cuit, String dirPostal, int codInscripto) {
		this.razonSocial = razonSocial;
		this.cuit = cuit;
		this.dirPostal = dirPostal;
		this.codInscripto = codInscripto;
	}
	
	// Para crear una Entidad Juridica sin codigo de inscripcion en IGJ
	public EntidadJuridica(int razonSocial, int cuit, String dirPostal) {
		this.razonSocial = razonSocial;
		this.cuit = cuit;
		this.dirPostal = dirPostal;
	}
	
}
