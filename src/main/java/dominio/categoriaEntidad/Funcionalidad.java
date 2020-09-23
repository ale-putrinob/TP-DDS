package dominio.categoriaEntidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import dominio.organizacion.Entidad;

@Entity
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
