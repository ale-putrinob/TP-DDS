
package dominio.organizacion;

import java.util.stream.Stream;

import dominio.categoriaEntidad.CategoriaEntidad;
import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.operacionDeEgreso.RepositorioEgresos;

public abstract class Entidad {
	
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
	

/*cada operacionEgreso tendria que tener una entidad asociada, para que cuando la filtre del repo se identifique con la
 * entidad en la que estoy parado, me las filtre, y una vez filtradas me las vuelva filtrar de acuerdo a la categoria o
 * etiqueta que le este pasando por parametro, luego hacer un sum con los valores de todas las operaciones de egresos
 * para asi obtener el reporte sobre los gastos totales. Faltaria que ese reporte se haga mensualmente. */
	
}
