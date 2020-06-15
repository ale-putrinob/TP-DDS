package dominio.criterioDeSeleccionDeProveedor;

import java.util.List;

import dominio.presupuesto.Presupuesto;

public interface CriterioDeSeleccionDeProveedor {
	public Presupuesto elegirSegunCriterio(List<Presupuesto> presupuestos);
}
