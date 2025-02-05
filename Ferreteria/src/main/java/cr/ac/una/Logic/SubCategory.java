package cr.ac.una.Logic;

import java.util.ArrayList;
import java.util.List;

public class SubCategory {

    private String subCategoryID;
    private String subCategoryName;
    private String subCategoryDescription;
    private List<Items> items;

    public SubCategory() {}
    public SubCategory(String subCategoryID, String subCategoryName, String subCategoryDescription, List<Items> articulos) {
        this.subCategoryID = subCategoryID;
        this.subCategoryName = subCategoryName;
        this.subCategoryDescription = subCategoryDescription;
        this.items = articulos;
    }

    public SubCategory(String subCategoryID, String subCategoryName, String subCategoryDescription) {
        this.subCategoryID = subCategoryID;
        this.subCategoryName = subCategoryName;
        this.subCategoryDescription = subCategoryDescription;
        this.items = new ArrayList<Items>();
    }

    public String getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(String subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getSubCategoryDescription() {
        return subCategoryDescription;
    }
    public void setSubCategoryDescription(String subCategoryDescription) {
        this.subCategoryDescription = subCategoryDescription;
    }

    public List<Items> getItems() {
        return items;
    }

}
