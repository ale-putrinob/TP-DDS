package dominio;

public class EntidadBase implements Entidad {
	String descripcion;
	EntidadJuridica dependencia;

	public EntidadBase(String unaDescripcion, EntidadJuridica unaDependencia) {
		this.descripcion = unaDescripcion;
		this.dependencia = unaDependencia;
	}
	
}