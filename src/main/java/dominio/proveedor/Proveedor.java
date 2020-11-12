package dominio.proveedor;

import dominio.persistentEntity.PersistentEntity;
import dominio.repositorioApiML.Ciudad;
import dominio.repositorioApiML.MerLibAPI;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class Proveedor extends PersistentEntity{
	
	String nombreApellido;
	String razonSocial;
	int dni;
	int cuil;
	/*agregarlo dps*/
	//Desnormalizamos la dirección postal porque a un proveedor le pertenece una única dirección postal
	@ManyToOne(cascade = {CascadeType.PERSIST})
	Ciudad ciudad;
	String calle;
	int altura;
	int piso;
	char departamento;
	
	public Proveedor(String nombreApellido,String razonSocial, int dni, int cuil, String idCiudad, String calle,
					 int altura, int piso, char departamento) {

		this.nombreApellido = nombreApellido;
		this.razonSocial = razonSocial;
		this.dni = dni;
		this.cuil = cuil;
		ciudad = MerLibAPI.getUnaCiudad(idCiudad);
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.departamento = departamento;
	};
	public Proveedor(String nombreApellido) {};

	public String getCiudad() {
		return ciudad.getName();
	}

	public String getNombreApellido() {
		return nombreApellido;
	}
}
