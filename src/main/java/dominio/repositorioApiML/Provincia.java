package dominio.repositorioApiML;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Provincia {

    @Id
    @GeneratedValue
    private Long id_provincia;

    String id;
    String name;
    @ManyToOne
    Pais country;

    public Provincia (String id, String name, Pais country) {
        this.name = id;
        this.name = name;
        this.country = country;
    };

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

    public Pais getCountry() {
        return country;
    }
    public void setCountry(Pais country) {
        this.country = country;
    }

}
