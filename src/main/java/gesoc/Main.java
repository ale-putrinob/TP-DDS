package gesoc;

import dominio.organizacion.Organizacion;

public class Main {
	static Organizacion organizacion;

	public static void main(String[] args) {
		System.out.println(" - Bienvenido a GeSoc - ");
		System.out.println("Gestion de Proyectos Sociales");
		organizacion.validarOperacionesPendientes();
	}
	
	public static void setOrganizacion(Organizacion unaOrganizacion) {
		organizacion=unaOrganizacion;
	}

}