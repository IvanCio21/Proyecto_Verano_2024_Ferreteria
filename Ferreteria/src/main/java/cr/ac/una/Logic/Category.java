package cr.ac.una.Logic;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String id;
    private String name;
    private String description;
    private List<SubCategory> subCategoryList;

    public Category(String id, String name, String description, List<SubCategory> subCategoryList)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.subCategoryList = subCategoryList;
    }

    public Category(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.subCategoryList = new ArrayList<SubCategory>();
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
        if (this.subCategoryList.size() <= 2) {
            this.subCategoryList.add(subCategory);
        }
    }

    @Override
    public String toString() {
        return "Category" + id + name + description + subCategoryList;
    }
}
