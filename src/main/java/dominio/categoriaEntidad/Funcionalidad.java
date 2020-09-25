package dominio.categoriaEntidad;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import dominio.organizacion.Entidad;
import dominio.persistentEntity.PersistentEntity;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoValidacion")
public abstract class Funcionalidad extends PersistentEntity{
	
	public void validarNuevosEgresos(Entidad entidad) {
		
	}
	
	public void validarNuevasEntidadesBase() {
	
	}
	
	public void validarAdicionAEntidadJuridica(){
		
	}

}
