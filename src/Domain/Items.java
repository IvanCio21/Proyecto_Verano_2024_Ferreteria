package Domain;

public class Items {
    private String id;
    private String name;
    private Presentation presentation;
    private Category category;
    private SubCategory subCategory;

    public Items(){

    }

    public Items(String id, String name, Presentation presentation, Category category, SubCategory subcategory){
        this.id = id;
        this.name = name;
        this.presentation = presentation;
        this.category = category;
        this.subCategory = subcategory;
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

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public String[] dataName(){
        String dataName[] = {"ID", "Name", "Presentacion", "Categoria", "SubCategoria"};
        return dataName;
    }

    public String[] getData(){
        String data[] = {this.id, this.name, String.valueOf(this.presentation), String.valueOf(this.category), String.valueOf(this.subCategory)};
        return data;
    }

    public String toString(){
        return this.id + " " + this.name + " " + this.presentation + " " + this.category + " " + this.subCategory;
    }
}
