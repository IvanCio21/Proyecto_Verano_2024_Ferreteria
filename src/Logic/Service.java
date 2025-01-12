package Logic;
import Data.Data;
import Data.XmlPersistent;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class Service {

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

}
