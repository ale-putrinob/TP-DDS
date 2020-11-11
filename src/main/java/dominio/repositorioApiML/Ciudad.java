package dominio.repositorioApiML;

import javax.persistence.*;

@Entity
public class Ciudad {

    @Id
    @GeneratedValue
    private Long id_ciudad;

    private String id;
    private String name;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    Provincia state;
    @Transient
    private Object geoInformation;


    public Ciudad (String id, String name, Provincia state) {
        this.id = id;
        this.name = name;
        this.state = state;
    };
    
    public Ciudad() {};

    public Long getIdCiudad() {
        return id_ciudad;
    }

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

    public Object getGeoInformation() {
        return geoInformation;
    }
    public void setGeoInformation(Object geoInformation) {
        this.geoInformation = geoInformation;
    }

}
