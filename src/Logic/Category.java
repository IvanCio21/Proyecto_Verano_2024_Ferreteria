package Logic;

import java.util.List;

public class Category {
    private int id;
    private String name;
    private String description;
    private List<SubCategory> subCategoryList;



    public Category(int id, String name, String description, List<SubCategory> subCategoryList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.subCategoryList = subCategoryList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<SubCategory> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<SubCategory> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    public void addSubCategory(SubCategory subCategory) {
        this.subCategoryList.add(subCategory);
    }

    @Override
    public String toString() {
        return "Category" + id + name + description + subCategoryList;
    }
}
