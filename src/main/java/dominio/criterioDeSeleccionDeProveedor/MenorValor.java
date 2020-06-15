package dominio.criterioDeSeleccionDeProveedor;

import java.util.Comparator;
import java.util.List;

import dominio.presupuesto.Presupuesto;

public class MenorValor implements CriterioDeSeleccionDeProveedor {


	@Override
	public Presupuesto elegirSegunCriterio(List<Presupuesto> presupuestos) {
		Comparator<Presupuesto> totalDelPresupuesto = Comparator.comparing( Presupuesto::presupuestoTotal );
	
		return presupuestos.stream().min(totalDelPresupuesto).get();
	}
	
}
