package dominio.item;

public class Item {
	double valorItem;
	String descripcion;
	boolean asociadoAEgreso = false;
	
	public Item(double valorItem, String descripcion) {
		this.valorItem=valorItem;
		this.descripcion=descripcion;
	};
	
	public double getValorItem() {
		return this.valorItem;
	}
	
	public void asociarAEgreso() {
		this.asociadoAEgreso = true;
	}
	
	public boolean estaAsociadoAEgreso() {
		return this.asociadoAEgreso;
	}
}
