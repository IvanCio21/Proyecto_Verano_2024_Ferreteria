package Logic;

import java.util.ArrayList;
import java.util.List;

public class SubCategory {

    private int subCategoryID;
    private String subCategoryName;
    private List<Items> articulos;

    public SubCategory(int subCategoryID, String subCategoryName, List<Items> articulos) {
        this.subCategoryID = subCategoryID;
        this.subCategoryName = subCategoryName;
        this.articulos = articulos;
    }

    public SubCategory(int subCategoryID, String subCategoryName) {
        this.subCategoryID = subCategoryID;
        this.subCategoryName = subCategoryName;
        this.articulos = new ArrayList<Items>();
    }

    public int getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(int subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public List<Items> getArticulos() {
        return articulos;
    }

}
