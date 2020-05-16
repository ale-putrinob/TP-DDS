package dominio.item;

public class Item {
	double valorItem;
	String descripcion;
	
	public Item(double valorItem, String descripcion) {
		this.valorItem=valorItem;
		this.descripcion=descripcion;
	};
	
	public double getValorItem() {
		return valorItem;
	}
}
