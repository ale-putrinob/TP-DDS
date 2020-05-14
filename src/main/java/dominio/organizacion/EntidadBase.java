package dominio;

public class EntidadBase implements Entidad {
	String nombreFicticio;
	String descripcion;
	EntidadJuridica dependencia;

	public EntidadBase(String nombreFicticio, String unaDescripcion, EntidadJuridica unaDependencia) {
		this.nombreFicticio = nombreFicticio;
		this.descripcion = unaDescripcion;
		this.dependencia = unaDependencia;
	}
	
}
