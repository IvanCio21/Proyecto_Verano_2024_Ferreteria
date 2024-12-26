package dataStructure;

public class SubCategory extends Category {
    private Category category;

    public SubCategory() {
    }

    public SubCategory(String id, String name, Category category, String description) {
        super(id, name, description);
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
