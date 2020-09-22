package dominio.criterioDeSeleccionDeProveedor;

import java.util.Comparator;
import java.util.List;

import dominio.presupuesto.Presupuesto;
import dominio.proveedor.Proveedor;


public enum CriterioDeSeleccionDeProveedor {
	
	MENOR_VALOR{
		public Proveedor elegirSegunCriterio(List<Presupuesto> presupuestos) {
			Comparator<Presupuesto> totalDelPresupuesto = Comparator.comparing(Presupuesto::presupuestoTotal );
			//Con la siguiente linea obtenemos el presupuesto que buscamos
			Presupuesto presupuestoElegido = presupuestos.stream().min(totalDelPresupuesto).get();
			return presupuestoElegido.getProveedor();
		}
		};
	
	
	public abstract Proveedor elegirSegunCriterio(List<Presupuesto> presupuestos);
	
}

