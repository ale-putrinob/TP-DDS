package dominio;

public class Empresa extends EntidadJuridica{
	Categoria_Empresa categoria;

	public Empresa(Categoria_Empresa categoria,int razonSocial, int cuit, String dirPostal, int codInscripto) {
		super(razonSocial, cuit, dirPostal, codInscripto);
		this.categoria=categoria;
	}

	public Empresa(Categoria_Empresa categoria,int razonSocial, int cuit, String dirPostal) {
		super(razonSocial, cuit, dirPostal);
		this.categoria=categoria;
	}	
}	
