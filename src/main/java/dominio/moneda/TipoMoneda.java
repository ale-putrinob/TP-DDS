package dominio.moneda;


import javax.persistence.Entity;
import dominio.persistentEntity.PersistentEntity;

@Entity
public class TipoMoneda extends PersistentEntity{
	
    String moneda_id; 
    String description;
    String symbol;
    int decimal_places;

    public String getId() {
        return moneda_id;
    }
    public void setId(String id) {
        this.moneda_id = id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getDecimal_places() {
        return decimal_places;
    }
    public void setDecimal_places(int decimal_places) {
        this.decimal_places = decimal_places;
    }

}
