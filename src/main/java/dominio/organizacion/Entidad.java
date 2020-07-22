
package dominio.organizacion;

import java.util.stream.Stream;

import dominio.categoriaEntidad.CategoriaEntidad;
import dominio.excepcion.EntidadException;
import dominio.operacionDeEgreso.Etiqueta;
import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.operacionDeEgreso.RepositorioEgresos;

public abstract class Entidad {
	
	protected CategoriaEntidad categoriaEntidad;
	
	
	public double totalDeEgresos() {
		return egresosDeLaEntidad().mapToDouble(egreso -> egreso.valorTotal()).sum();
	}
	
	public double ReporteGastosMensuales(Etiqueta etiqueta, int mes, int anio) {
		return this.egresosDeLaEntidad().filter(egreso -> egreso.tieneEtiqueta(etiqueta) && egreso.esDelMes(mes,anio)).
				mapToDouble(egreso -> egreso.valorTotal()).sum();
	}
	
	private Stream<OperacionEgreso> egresosDeLaEntidad(){
		return RepositorioEgresos.todos().stream().filter(egreso -> egreso.getEntidad()==this);
	}

	public void validarAgregarEgreso() {
		if(!categoriaEntidad.aceptaNuevosEgresos(this))
			throw new EntidadException("No se puede agregar el egreso porque se ha superado el monto establecido");
	}
	

/*cada operacionEgreso tendria que tener una entidad asociada, para que cuando la filtre del repo se identifique con la
 * entidad en la que estoy parado, me las filtre, y una vez filtradas me las vuelva filtrar de acuerdo a la categoria o
 * etiqueta que le este pasando por parametro, luego hacer un sum con los valores de todas las operaciones de egresos
 * para asi obtener el reporte sobre los gastos totales. Faltaria que ese reporte se haga mensualmente. */
	
}
