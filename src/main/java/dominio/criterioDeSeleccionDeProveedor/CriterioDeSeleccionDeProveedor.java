package dominio.criterioDeSeleccionDeProveedor;

import java.util.List;

import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;

//
public interface CriterioDeSeleccionDeProveedor {
	public Proveedor elegirSegunCriterio(List<Presupuesto> presupuestos);
}
