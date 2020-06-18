package dominio.moneda;

public class TipoMoneda {
    String id;
    String description;
    String symbol;
    int decimal_places;id

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
