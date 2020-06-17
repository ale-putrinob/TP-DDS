package gesoc;

import dominio.organizacion.Organizacion;

public class Main {
	static Organizacion organizacion;

	public static void main(String[] args) {
		System.out.println("a tu cola le falta crema");
		organizacion.validarOperacionesPendientes();
	}
	
	public static void setOrganizacion(Organizacion unaOrganizacion) {
		organizacion=unaOrganizacion;
	}

}