package dominio.criterioDeSeleccionDeProveedor;

import java.util.Comparator;
import java.util.List;
import dominio.proveedor.Proveedor;

import dominio.presupuesto.Presupuesto;

public class MenorValor implements CriterioDeSeleccionDeProveedor {

	@Override
	public Proveedor elegirSegunCriterio(List<Presupuesto> presupuestos) {
		Comparator<Presupuesto> totalDelPresupuesto = Comparator.comparing(Presupuesto::presupuestoTotal );
		//Con la siguiente linea obtenemos el presupuesto que buscamos
		Presupuesto presupuestoElegido = presupuestos.stream().min(totalDelPresupuesto).get();
		return presupuestoElegido.getProveedor();
	}
	
}
