package dominio.proveedor;

import dominio.repositorioApiML.Ciudad;
import dominio.repositorioApiML.MerLibAPI;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Proveedor {
	
	@Id
	@GeneratedValue
	private Long id;
	
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

	public String getCiudad() {
		return ciudad.getName();
	}
}
