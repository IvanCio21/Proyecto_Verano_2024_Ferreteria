package Logic;

import java.util.ArrayList;
import java.util.List;

public class Items {
    private String id;
    private String marca;
    private String name;
    private String description;
    private List<Presentation> presentation;
    private SubCategory subCategory;

    public Items(){
        this.presentation  = new ArrayList<>();
    }

    public Items(String id, String marca, String name, String descripcion, SubCategory subcategory){
        this.id = id;
        this.marca = marca;
        this.name = name;
        this.description = descripcion;
        this.subCategory = subcategory;
        this.presentation = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public String[] dataName(){
        return new String[]{"ID", "Marca","Nombre", "Descripcion", "Subcategoria"};
    }

    public String[] getData(){
        return new String[]{
                this.id,
                this.marca,
                this.name,
                this.description,
                subCategory != null ? subCategory.getName() : "Sin subcategoría"
        };
    }

    public String toString(){
        return id + " " + marca + " " + name + " " + description + " Subcategoria: " +
                (subCategory != null ? subCategory.getName() : "N/A");
    }
}
