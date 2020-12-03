package dominio.organizacion;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

import dominio.categoriaEntidad.CategoriaEntidad;

@Entity
public class EntidadJuridica extends Entidad {
	String nombreFicticio;
	String razonSocial;
	int cuit;
	String dirPostal;
	int codInscripto;
	
	@Enumerated
	Tipo tipo;
	
	public EntidadJuridica(String nombreFicticio, String razonSocial, int cuit, String dirPostal, int codInscripto, Tipo tipo, CategoriaEntidad categoriaEntidad) {
		this.nombreFicticio = nombreFicticio;
		this.razonSocial = razonSocial;
		this.cuit = cuit;
		this.dirPostal = dirPostal;
		this.codInscripto = codInscripto;
		this.tipo = tipo;
		this.categoriaEntidad = categoriaEntidad;
	}
	
	// Para crear una Entidad Juridica sin codigo de inscripcion en IGJ
	public EntidadJuridica(String nombreFicticio, String razonSocial, int cuit, String dirPostal) {
		this.nombreFicticio = nombreFicticio;
		this.razonSocial = razonSocial;
		this.cuit = cuit;
		this.dirPostal = dirPostal;
		this.codInscripto = 0;
	}
	
	public EntidadJuridica() {}

	public void validarNuevasEntidadesBase() {
		if(categoriaEntidad != null)
			this.categoriaEntidad.validarNuevasEntidadesBase();
	}
	
	public String getNombreFicticio() {
		return nombreFicticio;
	}

	public CategoriaEntidad getCategoriaEntidad() {
		return categoriaEntidad;
	}
	
	public String getTipoEntidad() {
		return "Juridica";
	}
}
