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
        Category resultCategory = data.getCategorias().stream().filter(c -> c.getId().contains(id)).findFirst().orElse(null);
        if (resultCategory != null)
            return resultCategory;
        else throw new Exception("Categoria no existe");
    }

    public  void EliminateSubcategory(String idCat, String id) throws Exception {
        Category cat = data.getCategorias().stream().filter(c -> c.getId().contains(id)).findFirst().orElse(null);
        assert cat != null;
        cat.getSubCategoryList().removeIf(subCat -> subCat.getSubCategoryID().equals(id));

    }


    public Category categoryGetName(String name) throws Exception {
        Category resultCategory = data.getCategorias().stream().filter(c -> c.getName().contains(name)).findFirst().orElse(null);
        if (resultCategory != null)
            return resultCategory;
        else throw new Exception("Categoria no existe");
    }



    public SubCategory subCategoryGetId(String id) throws Exception {
        SubCategory resultsubCategory = data.getSubcategorias().stream().filter(c -> c.getSubCategoryID().contains(id)).findFirst().orElse(null);
        if (resultsubCategory != null)
            return resultsubCategory;
        else throw new Exception("SubCategoria no existe");
    }




    public SubCategory subCategoryGetName(String name) throws Exception {
        SubCategory resultsubCategory = data.getSubcategorias().stream().filter(c -> c.getSubCategoryName().contains(name)).findFirst().orElse(null);
        if (resultsubCategory != null)
            return resultsubCategory;
        else throw new Exception("SubCategoria no existe");
    }

    public Items itemsGetId(String id) throws Exception {
        Items resultItems = data.getArticulos().stream().filter(c -> c.getId().contains(id)).findFirst().orElse(null);
        if (resultItems != null)
            return resultItems;
        else throw new Exception("Articulo no existe");
    }

    public Items itemsGetName(String name) throws Exception {
        Items resultItems = data.getArticulos().stream().filter(c -> c.getName().contains(name)).findFirst().orElse(null);
        if (resultItems != null)
            return resultItems;
        else throw new Exception("Categoria no existe");
    }


    public void addCategory(Category category) throws Exception {
        List<Category> categories = data.getCategorias();

        for (Category category1 : categories) {
            if (category1.getId().equals(category.getId())) {
                throw new Exception("Categoría ya existe");
            }
        }
        categories.add(category);
    }

    public void addSubCategory(String id, SubCategory subCategory) throws Exception {
        Category cat = categoryGetId(id);
        for (SubCategory subCat : cat.getSubCategoryList()) {
            if (subCat.getSubCategoryID().trim().equals(id.trim())) {
                throw new Exception("Subcategoria ya existe");
            }
        }
        cat.getSubCategoryList().add(subCategory);
    }


    public void CategoryEdit(String id, String nombre, String descrpcion) throws Exception {
        List<Category> categories = data.getCategorias();

        for (Category category1 : categories) {
            if (category1.getId().equals(id)) {
                category1.setName(nombre);
                category1.setDescription(descrpcion);
            }
        }
    }

    public void setEditSubCategory(String idC,String id, String name, String descrpcion) throws Exception {
        List<SubCategory> subCategories = categoryGetId(id).getSubCategoryList();
        for (SubCategory subCategory : subCategories) {
            if(subCategory.getSubCategoryID().equals(idC)){
                subCategory.setSubCategoryName(name);
                subCategory.setSubCategoryDescription(descrpcion);
            }
        }

    }
    public void CategoryDelete(int row) throws Exception {
        Category cat = data.getCategorias().get(row);
        List<Category> categoryList = data.getCategorias();
        data.getCategorias().remove(cat);
    }

    public void guardarArticulo(String cat, String subCategory, Items items, Presentation presentation) throws Exception {
        // Validar si la categoría proporcionada es válida
        Category category = categoryGetId(cat);
        if (category == null) {
            throw new Exception("Categoría no encontrada.");
        }

        // Obtener la lista de subcategorías y validarla
        List<SubCategory> subCategories = category.getSubCategoryList();
        if (subCategories == null || subCategories.isEmpty()) {
            throw new Exception("No hay subcategorías disponibles en esta categoría.");
        }

        // Validar si la subcategoría proporcionada existe
        boolean subCategoryFound = false;
        for (SubCategory subCat : subCategories) {
            if (subCat.getSubCategoryID().equals(subCategory)) {
                subCategoryFound = true;

                // Validar si el artículo y presentación no son nulos
                if (items == null) {
                    throw new Exception("El artículo no puede ser nulo.");
                }
                if (presentation == null) {
                    throw new Exception("La presentación no puede ser nula.");
                }

                // Agregar el artículo y la presentación a la subcategoría
                subCat.getItems().add(items);
                items.getPresentation().add(presentation);
                break;
            }
        }

        // Si no se encontró la subcategoría, lanzar una excepción
        if (!subCategoryFound) {
            throw new Exception("Subcategoría no encontrada.");
        }
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

    public List<Items> allItems() {
        return data.getArticulos();
    }

    public List<Presentation> allPresentation() {
        return data.getPresentations();
    }
}

