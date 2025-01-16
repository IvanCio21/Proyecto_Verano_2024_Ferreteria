package Logic;
import Data.Data;
import Data.XmlPersistent;

import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;

public class Service {

    private static Service theInstance;
    private Data data;
    private XmlPersistent xmlPersistent;

    public Service() {
        data = new Data();
        xmlPersistent = new XmlPersistent();
    }

    public static Service instance() {
        if (theInstance == null) {
            theInstance = new Service();
        }
        return theInstance;
    }

    //BUSQUEDA PRUEBA
    public Category categoryGetId(String id) throws Exception {
        Category resultCategory = data.getCategorias().stream()
                .filter(c -> c.getId().contains(id) || c.getName().contains(id)) // Filtra por id o nombre
                .findFirst()
                .orElse(null);

        if (resultCategory != null)
            return resultCategory;
        else throw new Exception("Categoria no existe");
    }


    public SubCategory subCategoryGetId(String id, String idC) throws Exception {
        List<SubCategory> listSub = categoryGetId(id).getSubCategoryList();
        SubCategory resultSubCategory = null;
        for (SubCategory subCategory : listSub) {
            if (subCategory.getSubCategoryID().equals(idC) || subCategory.getSubCategoryName().equals(idC)) {
                resultSubCategory = subCategory;
                break;
            }
        }
        if (resultSubCategory == null) {
            throw new Exception();
        }

        return resultSubCategory;
    }

    public void CategoryEdit(String id, String nombre, String descrpcion) throws Exception {
        List<Category> categories = data.getCategorias();

        for (Category category1 : categories) {
            if (category1.getId().equals(id)) {
                category1.setName(nombre);
                category1.setDescription(descrpcion);
                saveXml();
            }
        }
    }


    public Items itemsGetName(String name) throws Exception {
        Items resultItems = data.getArticulos().stream().filter(c -> c.getName().contains(name)).findFirst().orElse(null);
        if (resultItems != null)
            return resultItems;
        else throw new Exception("Categoria no existe");
    }

    /// Category
    public void addCategory(Category category) throws Exception {
        List<Category> categories = data.getCategorias();

        for (Category category1 : categories) {
            if (category1.getId().equals(category.getId())) {
                throw new Exception("Categoría ya existe");
            }
        }
        categories.add(category);
        data.setCategorias(categories);

        saveXml();
    }


    //SubCategory

    public void addSubCategory(String id, SubCategory subCategory) throws Exception {
        Category cat = categoryGetId(id);
        cat.getSubCategoryList().add(subCategory);
        saveXml();
    }

    public void setEditSubCategory(String idC, String id, String name, String descrpcion) throws Exception {
        // Obtener la categoría por el ID
        Category categoria = categoryGetId(idC);

        SubCategory subCategoria = null;
        for (SubCategory subCat : categoria.getSubCategoryList()) {
            if (subCat.getSubCategoryID().equals(id)) {
                subCategoria = subCat;
                break;
            }
        }

        if (subCategoria != null) {
            subCategoria.setSubCategoryName(name);
            subCategoria.setSubCategoryDescription(descrpcion);
            data.setSubcategorias(categoria.getSubCategoryList());
            saveXml();
        }
    }

    public void CategoryDelete(int row) throws Exception {

        Category cat = data.getCategorias().get(row);
        if(cat.getSubCategoryList().size() == 0){
            data.getCategorias().remove(cat);
        }else{
            throw new Exception();
        }


    }

    public void EliminateSubcategory(String categoriaId, String subCategoriaId) throws Exception {
        // Obtener la categoría por el ID
        Category categoria = categoryGetId(categoriaId);

        // Buscar la subcategoría por el ID
        SubCategory subCategoria = null;
        for (SubCategory subCat : categoria.getSubCategoryList()) {
            if (subCat.getSubCategoryID().equals(subCategoriaId)) {
                subCategoria = subCat;
                break;
            }
        }

        if (subCategoria != null) {
            // Verificar si la subcategoría tiene artículos
            if (subCategoria.getItems() != null && !subCategoria.getItems().isEmpty()) {
                throw new Exception("No se puede eliminar la subcategoría porque contiene artículos");
            } else {
                // Eliminar la subcategoría si no tiene artículos
                categoria.getSubCategoryList().remove(subCategoria);
                saveXml(); // Guardar los cambios en el archivo XML
            }
        } else {
            throw new Exception("Subcategoría no encontrada");
        }
    }








    public void guardarArticulo(String cat, String subCategory, Items items, Presentation presentation) throws Exception {
        Category category = categoryGetId(cat);
        if (category == null) {
            throw new Exception("Categoría no encontrada.");
        }
        List<SubCategory> subCategories = category.getSubCategoryList();
        if (subCategories == null || subCategories.isEmpty()) {
            throw new Exception("No hay subcategorías disponibles en esta categoría.");
        }

        boolean subCategoryFound = false;
        for (SubCategory subCat : subCategories) {
            if (subCat.getSubCategoryID().equals(subCategory)) {
                subCategoryFound = true;
                if (items == null) {
                    throw new Exception("El artículo no puede ser nulo.");
                }
                if (presentation == null) {
                    throw new Exception("La presentación no puede ser nula.");
                }

                subCat.getItems().add(items);
                items.getPresentation().add(presentation);
                saveXml();
                break;
            }
        }
        if (!subCategoryFound) {
            throw new Exception("Subcategoría no encontrada.");
        }
    }

    public boolean deleteItem(String categoryName, String subCategoryName, String itemId) {
        List<Category> categorias = data.getCategorias();

        for (Category categoria : categorias) {
            if (categoria.getName().equalsIgnoreCase(categoryName)) {

                List<SubCategory> subCategorias = categoria.getSubCategoryList();

                for (SubCategory subCategoria : subCategorias) {
                    if (subCategoria.getSubCategoryName().equalsIgnoreCase(subCategoryName)) {

                        List<Items> articulos = subCategoria.getItems();

                        for (Items articulo : articulos) {
                            if (articulo.getName().equalsIgnoreCase(itemId)) {
                                articulos.remove(articulo);
                                saveXml();
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }


    public void saveXml() {
        xmlPersistent.guardarCategorias(data);
    }

    public void loadXml() {
        List<Category> categorias = xmlPersistent.cargarCategorias();
        data.setCategorias(categorias);
    }

    public List<Category> allCategories() {
        return data.getCategorias();
    }

    public List<SubCategory> allSubCategories() {
        return data.getSubcategorias();
    }

    public List<Items> allItems(String id, String idSub) throws Exception {
        List<Items> items = new ArrayList<>();
        try{

            Category cat = categoryGetId(id);
            List<SubCategory> subCategories = cat.getSubCategoryList();

            for(SubCategory subCat : subCategories){
                if(subCat.getSubCategoryID().equals(idSub)){
                    items = subCat.getItems();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return items;
    }

    public List<Presentation> allPresentation(String id, String idSub, String ar) {

        List<Presentation> presentations = new ArrayList<>();
        try{
            List<Items> items = allItems(id,idSub);
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i).getId().equals(ar)){
                    presentations = items.get(i).getPresentation();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return presentations;
    }

    public void agregarPresentation(String id, String sub, String Ar, Presentation presentation) {

       try{
           List<Items> items = allItems(id, sub);
           for(Items item : items){
               if(item.getId().equals(Ar)){
                   item.getPresentation().add(presentation);
                   data.setCategorias(data.getCategorias());
               }

           }
       } catch (Exception e) {
           throw new RuntimeException(e);
       }


    }
}

