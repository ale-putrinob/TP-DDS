package dominio.organizacion;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

import dominio.categoriaEntidad.CategoriaEntidad;

@Entity
public class EntidadJuridica extends Entidad {
	String nombreFicticio;
	int razonSocial;
	int cuit;
	String dirPostal;
	int codInscripto;
	
	@Enumerated
	Tipo categoria;
	
	public EntidadJuridica(String nombreFicticio, int razonSocial, int cuit, String dirPostal, int codInscripto, Tipo categoria, CategoriaEntidad categoriaEntidad) {
		this.nombreFicticio = nombreFicticio;
		this.razonSocial = razonSocial;
		this.cuit = cuit;
		this.dirPostal = dirPostal;
		this.codInscripto = codInscripto;
		this.categoria = categoria;
		this.categoriaEntidad = categoriaEntidad;
	}
	
	// Para crear una Entidad Juridica sin codigo de inscripcion en IGJ
	public EntidadJuridica(String nombreFicticio, int razonSocial, int cuit, String dirPostal) {
		this.nombreFicticio = nombreFicticio;
		this.razonSocial = razonSocial;
		this.cuit = cuit;
		this.dirPostal = dirPostal;
		this.codInscripto = 0;
	}

	public void validarNuevasEntidadesBase() {
		this.categoriaEntidad.validarNuevasEntidadesBase();
	}
	
}
