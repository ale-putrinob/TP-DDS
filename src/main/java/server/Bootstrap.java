package server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import dominio.criterioDeSeleccionDeProveedor.CriterioDeSeleccionDeProveedor;
import dominio.documentoComercial.DocumentoComercial;
import dominio.item.Item;
import dominio.medioDePago.MedioDePago;
import dominio.medioDePago.TiposDePago;
import dominio.operacionDeEgreso.OperacionEgreso;
import dominio.presupuesto.Presupuesto;
import dominio.validacionEgresos.ValidacionAplicacionPresupuesto;
import dominio.validacionEgresos.ValidacionCantidadPresupuestos;
import dominio.validacionEgresos.ValidacionSeleccionProveedor;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dominio.categoriaEntidad.CategoriaEntidad;
import dominio.mensajes.Mensaje;
import dominio.organizacion.EntidadBase;
import dominio.organizacion.EntidadJuridica;
import dominio.proveedor.Proveedor;

import dominio.usuario.Usuario;

class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
	
	public static void main(String args []) {
		new Bootstrap().init();
	}
	
	public void init() {
		withTransaction(() ->{
			
			EntidadJuridica ent = new EntidadJuridica("Boca Juniors", "Campeon", 0, null, 0, null, new CategoriaEntidad(""));
			persist(ent);

			
			EntidadBase entBase = new EntidadBase("Colimba FC", "Anti Generacion de Cristal", ent, new CategoriaEntidad(""));
			persist(entBase);



			
			Proveedor proveedor = new Proveedor("Juan Peron","JDP",45678978,2045678889,"1567","Evita", 31, 2, 'A');
			persist(proveedor);

			////////////////////////////OPERACION DE EGRESO

			OperacionEgreso operacionOE = new OperacionEgreso(null, null, null, null,
					null, null, null, null, CriterioDeSeleccionDeProveedor.MENOR_VALOR, null, null);
			persist(operacionOE);

			Mensaje mensaje = new Mensaje("Se esta aplicando alguno de los presupuestos en la compra", operacionOE);
			persist(mensaje);
			Usuario usuario = new Usuario("coquitos", "bocacampeon", true,  Arrays.asList(mensaje));
			persist(usuario);


			
		});
		
	}
}
