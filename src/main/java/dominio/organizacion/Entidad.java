
package dominio.organizacion;

import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import dominio.categoriaEntidad.CategoriaEntidad;
import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.operacionDeEgreso.RepositorioEgresos;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Entidad {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	protected CategoriaEntidad categoriaEntidad;
	
	
	public double totalDeEgresosDeLaEntidad() {
		return totalDeEgresos(this.egresosDeLaEntidad());
	}
	
	private double totalDeEgresos(Stream<OperacionEgreso> egresos) {
		return egresos.mapToDouble(egreso -> egreso.valorTotal()).sum();
	}
	
	private Stream<OperacionEgreso> egresosDeLaEntidadDelMesConEtiqueta(String etiqueta, int mes, int anio) {
		return egresosDeLaEntidad().filter(egreso -> egreso.tieneEtiqueta(etiqueta) && egreso.esDelMes(mes,anio));
	}
	
	public double ReporteGastosMensuales(String etiqueta, int mes, int anio) {
		return totalDeEgresos(this.egresosDeLaEntidadDelMesConEtiqueta(etiqueta, mes, anio));
	}
	
	private Stream<OperacionEgreso> egresosDeLaEntidad(){
		return RepositorioEgresos.getInstance().getEgresos().stream().filter(egreso -> egreso.getEntidad()==this);
	}

	public void validarAgregarEgreso() {
		categoriaEntidad.validarNuevosEgresos(this);	
	}
	
}
