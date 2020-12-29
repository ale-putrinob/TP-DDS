package dominio.criterioDeSeleccionDeProveedor;

import java.util.Comparator;
import java.util.List;

import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;


public enum CriterioDeSeleccionDeProveedor {
	
	MENOR_VALOR{
		public Proveedor elegirSegunCriterio(List<Presupuesto> presupuestos) {
			if (presupuestos.size() != 0 && presupuestos != null) {
				Comparator<Presupuesto> totalDelPresupuesto = Comparator.comparing(Presupuesto::presupuestoTotal);
				//Con la siguiente linea obtenemos el presupuesto que buscamos
				Presupuesto presupuestoElegido = presupuestos.stream().min(totalDelPresupuesto).orElse(null);
				return presupuestoElegido.getProveedor();
			}
			else return null;
		}
		};
	
	
	public abstract Proveedor elegirSegunCriterio(List<Presupuesto> presupuestos);
	
}

