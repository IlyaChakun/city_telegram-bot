package by.chekun.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class City extends AbstractEntity {

    @Column(name = "name", nullable = false, unique = true, length = 64)
    private String name;
    @Column(name = "info", nullable = false, length = 512)
    private String info;

    public City() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


}
