package dominio.repositorioApiML;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pais {

    @Id
    @GeneratedValue
    private Long id_pais;

    String short_name;
    String name;

    public Pais (String short_name, String name) {
        this.short_name = short_name;
        this.name = name;
    };
    
    public Pais() {};

    public String getShort_name() {
        return short_name;
    }
    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
