package dominio;

public class Empresa extends EntidadJuridica{
	CategoriaEmpresa categoria;

	public Empresa(CategoriaEmpresa categoria,int razonSocial, int cuit, String dirPostal, int codInscripto) {
		super(razonSocial, cuit, dirPostal, codInscripto);
		this.categoria=categoria;
	}

	public Empresa(String nombreFicticio, CategoriaEmpresa categoria,int razonSocial, int cuit, String dirPostal) {
		super(nombreFicticio, razonSocial, cuit, dirPostal);
		this.categoria=categoria;
	}	
}	
