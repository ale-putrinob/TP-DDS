package dominio.categoriaEntidad;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import dominio.organizacion.Entidad;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoValidacion")
public abstract class Funcionalidad {
	@Id
	@GeneratedValue
	private Long id;
	
	public void validarNuevosEgresos(Entidad entidad) {
		
	}
	
	public void validarNuevasEntidadesBase() {
	
	}
	
	public void validarAdicionAEntidadJuridica(){
		
	}

}
