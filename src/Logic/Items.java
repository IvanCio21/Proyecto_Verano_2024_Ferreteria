package Logic;

import java.util.ArrayList;
import java.util.List;

public class Items {
    private int id;
    private String marca;
    private String name;
    private String description;
    private List<Presentation> presentation;

    public Items(int id, String marca, String name, String description, List<Presentation> presentation) {
        this.id = id;
        this.marca = marca;
        this.name = name;
        this.description = description;
        this.presentation = presentation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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

    /*
    public String[] dataName(){
        return new String[]{"ID", "Marca","Nombre", "Descripcion", "Subcategoria"};
    }

    public String[] getData(){
        return new String[]{
                String.valueOf(this.id),
                this.marca,
                this.name,
                this.description,
                presentation != null ? presentation.getName() : "Sin subcategoría"
        };
    }

    public String toString(){
        return id + " " + marca + " " + name + " " + description + " Subcategoria: " +
                (presentation != null ? presentation.getName() : "N/A");
    }
    */
}
