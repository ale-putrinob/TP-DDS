package dominio;

public class OSC extends EntidadJuridica{
	
	public OSC(int razonSocial, int cuit, String dirPostal, int codInscripto) {
		super(razonSocial, cuit, dirPostal, codInscripto);
	}

	public OSC(int razonSocial, int cuit, String dirPostal) {
		super(razonSocial, cuit, dirPostal);
	}
	
}
