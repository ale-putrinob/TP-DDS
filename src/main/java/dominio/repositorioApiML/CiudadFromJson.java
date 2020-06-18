package dominio.repositorioApiML;

public class CiudadFromJson {
    private String id;
    private String name;
    private ProvinciaFromJson state;
    private PaisFromJson country;
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

    public ProvinciaFromJson getState() {
        return state;
    }
    public void setState(ProvinciaFromJson state) {
        this.state = state;
    }

    public PaisFromJson getCountry() {
        return country;
    }
    public void setCountry(PaisFromJson country) {
        this.country = country;
    }

    public Object getGeoInformation() {
        return geoInformation;
    }
    public void setGeoInformation(Object geoInformation) {
        this.geoInformation = geoInformation;
    }

}
