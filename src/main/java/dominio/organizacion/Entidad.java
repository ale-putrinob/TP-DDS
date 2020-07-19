package dominio.organizacion;

import dominio.operacionDeEgreso.Etiqueta;

public abstract class Entidad {
	
	public double ReporteGastosMensuales(Etiqueta etiqueta) {
		return RepositorioEgresos.todos().stream().
				filter(egreso -> egreso.tieneEtiqueta(etiqueta) && egreso.getEntidad()==this).
				mapToDouble(egreso -> egreso.valorTotal()).sum();
	}

/*cada operacionEgreso tendria que tener una entidad asociada, para que cuando la filtre del repo se identifique con la
 * entidad en la que estoy parado, me las filtre, y una vez filtradas me las vuelva filtrar de acuerdo a la categoria o
 * etiqueta que le este pasando por parametro, luego hacer un sum con los valores de todas las operaciones de egresos
 * para asi obtener el reporte sobre los gastos totales. Faltaria que ese reporte se haga mensualmente. */
	
}
