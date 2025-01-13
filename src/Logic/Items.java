package Logic;

import java.util.ArrayList;
import java.util.List;

public class Items {
    private String id;
    private String brand;
    private String name;
    private String description;
    private List<Presentation> presentation;

    public Items(String id, String brand, String name, String description, List<Presentation> presentation) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.presentation = presentation;
    }


    public Items(String cod, String nombre, String descripcion) {
        this.id = cod;
        this.brand = nombre;
        this.name = name;
        this.description = descripcion;
        this.presentation = new ArrayList();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Presentation> getPresentation() {
        return presentation;
    }

}
