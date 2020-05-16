package dominio.organizacion;

public class OSC extends EntidadJuridica{
	
	public OSC(int razonSocial, int cuit, String dirPostal, int codInscripto) {
		super(razonSocial, cuit, dirPostal, codInscripto);
	}

	public OSC(String nombreFicticio, int razonSocial, int cuit, String dirPostal) {
		super(nombreFicticio, razonSocial, cuit, dirPostal);
	}
	
}
