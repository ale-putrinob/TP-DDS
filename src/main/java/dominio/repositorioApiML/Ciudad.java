package dominio.repositorioApiML;

public class Ciudad {
    private String id;
    private String name;
    private Provincia state;
    private Pais country;
    private Object geoInformation;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Provincia getState() {
        return state;
    }
    public void setState(Provincia state) {
        this.state = state;
    }

    public Pais getCountry() {
        return country;
    }
    public void setCountry(Pais country) {
        this.country = country;
    }

    public Object getGeoInformation() {
        return geoInformation;
    }
    public void setGeoInformation(Object geo_information) {
        this.geoInformation = geoInformation;
    }

}
