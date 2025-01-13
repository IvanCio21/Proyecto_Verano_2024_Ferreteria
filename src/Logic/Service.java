package Logic;
import Data.Data;
import Data.XmlPersistent;
import org.xml.sax.SAXException;

import javax.swing.text.Document;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.time.Period;
import java.util.List;

public class Service {

    private static Service theInstance;
    private Data data;
    private XmlPersistent xmlPersistent;
    private Document doc;

    public Service(){
        data = new Data();
        xmlPersistent = new XmlPersistent();
        loadXml();

    }

    public static Service instance(){
        if(theInstance == null){
            theInstance = new Service();
        }
        return theInstance;
    }

    //BUSQUEDA PRUEBA
    public Category categoryGetId(String id) throws Exception {
        Category resultCategory = data.getCategorias().stream().filter(c -> c.getId().contains(id)).findFirst().orElse(null);
        if(resultCategory != null)
            return resultCategory;
        else throw new Exception("Categoria no existe");
    }

    public Category categoryGetName(String name) throws Exception {
        Category resultCategory = data.getCategorias().stream().filter(c -> c.getName().contains(name)).findFirst().orElse(null);
        if(resultCategory != null)
            return resultCategory;
        else throw new Exception("Categoria no existe");
    }

    public SubCategory subCategoryGetId(String id) throws Exception {
        SubCategory resultsubCategory = data.getSubcategorias().stream().filter(c -> c.getSubCategoryID().contains(id)).findFirst().orElse(null);
        if(resultsubCategory != null)
            return resultsubCategory;
        else throw new Exception("SubCategoria no existe");
    }

    public SubCategory subCategoryGetName(String name) throws Exception {
        SubCategory resultsubCategory = data.getSubcategorias().stream().filter(c -> c.getSubCategoryName().contains(name)).findFirst().orElse(null);
        if(resultsubCategory != null)
            return resultsubCategory;
        else throw new Exception("SubCategoria no existe");
    }

    public Items itemsGetId(String id) throws Exception {
        Items resultItems = data.getArticulos().stream().filter(c -> c.getId().contains(id)).findFirst().orElse(null);
        if(resultItems != null)
            return resultItems;
        else throw new Exception("Articulo no existe");
    }

    public Items itemsGetName(String name) throws Exception {
        Items resultItems = data.getArticulos().stream().filter(c -> c.getName().contains(name)).findFirst().orElse(null);
        if(resultItems != null)
            return resultItems;
        else throw new Exception("Categoria no existe");
    }

    public void addCategory(Category category) throws Exception {
        Category newCategory = data.getCategorias().stream().filter(c -> c.getId().contains(category.getId())).findFirst().orElse(null);
        if(newCategory == null) {
            data.getCategorias().add(category);
            saveXml();
        }
        else throw new Exception("Categoria ya existe");
    }

    public void addsubCategory(SubCategory subCategory) throws Exception {
        SubCategory newsubCategory = data.getSubcategorias().stream().filter(c -> c.getSubCategoryID().contains(subCategory.getSubCategoryID())).findFirst().orElse(null);
        if(newsubCategory == null) {
            data.getSubcategorias().add(subCategory);
            saveXml();
        }
        else throw new Exception("subCategoria ya existe");
    }

    public void addItems(Items items) throws Exception {
        Items newItem = data.getArticulos().stream().filter(c -> c.getId().contains(items.getId())).findFirst().orElse(null);
        if(newItem == null) {
            data.getArticulos().add(items);
            saveXml();
        }
        else throw new Exception("Articulo ya existe");
    }

    public void addPresentation(Presentation presentation) throws Exception {
        data.getPresentations().add(presentation);
        saveXml();
    }

    public void saveXml(){
        xmlPersistent.guardarCategorias(data);
    }

    public void loadXml(){
        List<Category> categories = xmlPersistent.cargarCategorias();
        data.setCategorias(categories);
    }

    public List<Category> allCategories(){
        return data.getCategorias();
    }

    public List<SubCategory> allSubCategories(){
        return data.getSubcategorias();
    }

    public List<Items> allItems(){
        return data.getArticulos();
    }

    public List<Presentation> allPresentation(){
        return data.getPresentations();
    }

    public Category categoryGetNumber(int pos) throws Exception{ //Devuelve un empleado exp
        Category result=data.getCategorias().get(pos);
        if (result!=null) return result;
        else throw new Exception("Posicion erronea");
    }



    /*
    public List<Category> getCategories() {
        Data data = new Data();
        data.loadXML();
        return data.getCategorias();
    }

    public void SaveCategories(List<Category> categories) {
        Data data = new Data();
        data.setCategorias(categories);
    }

public void addCategory(Category category) {
        Data data = new Data();
        data.loadXML();
        List<Category> categories = data.getCategorias();
        categories.add(category);
        data.setCategorias(categories);
}

    public void removeCategory(String categoryname) {
        Data data = new Data();
        data.loadXML();  // Cargar los datos actuales
        List<Category> categories = data.getCategorias();  // Obtener la lista de categorías
        categories.removeIf(category -> category.getName().equals(categoryname));  // Eliminar la categoría por su código
        data.setCategorias(categories);  // Guardar los cambios
    }

    public Category findCategory(String categoryName) {
        Data data = new Data();
        data.loadXML();  // Cargar los datos actuales
        List<Category> categories = data.getCategorias();  // Obtener la lista de categorías
        return categories.stream()
                .filter(category -> category.getName().equals(categoryName))
                .findFirst()
                .orElse(null);  // Retorna null si no encuentra la categoría
    }

    public List<Category> getAllCategories() {
        Data data = new Data();
        data.loadXML();  // Cargar los datos actuales
        return data.getCategorias();  // Obtener la lista completa de categorías
    }

*/

}
