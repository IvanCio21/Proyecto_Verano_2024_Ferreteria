package cr.ac.una.Logic;

import cr.ac.una.Data.*;

import java.util.ArrayList;
import java.util.List;

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

    //ARCHIVO
    public void saveXml() {
        xmlPersistent.guardarCategorias(data);
    }
    public void loadXml() {
        List<Category> categorias = xmlPersistent.cargarCategorias();
        data.setCategorias(categorias);
    }

    // CATEGORIA
    public Category categoryGetId(String id) throws Exception {
        Category resultCategory = data.getCategorias().stream()
                .filter(c -> c.getId().contains(id) || c.getName().contains(id)) // Filtra por id o nombre
                .findFirst()
                .orElse(null);

        if (resultCategory != null)
            return resultCategory;
        else throw new Exception("Categoria no existe");
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
    public void CategoryDelete(String id) throws Exception {

        Category cat = categoryGetId(id);
        if(cat.getSubCategoryList().isEmpty()){
            data.getCategorias().remove(cat);
        }else{
            throw new Exception();
        }


    }
    public List<Category> allCategories() {
        return data.getCategorias();
    }
    public List<SubCategory> allSubCategories(String cat) throws Exception {
        Category category = categoryGetId(cat);
        return category.getSubCategoryList();
    }

    //Sub Categorias
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
    public void addSubCategory(String id, SubCategory subCategory) throws Exception {
        Category cat = categoryGetId(id);
        cat.getSubCategoryList().add(subCategory);
        saveXml();
    }
    public void setEditSubCategory(String idC, String id, String name, String descrpcion) throws Exception {

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
    public boolean consultarNombre(String cat, String sub) throws Exception {
        Category categoria = categoryGetId(cat);
        for(SubCategory subCat : categoria.getSubCategoryList()){
            if(subCat.getSubCategoryName().equals(sub)){
                return true;
            }
        }
        return false;

    }
    public void EliminateSubcategory(String categoriaId, String subCategoriaId) throws Exception {

        Category categoria = categoryGetId(categoriaId);


        SubCategory subCategoria = null;
        for (SubCategory subCat : categoria.getSubCategoryList()) {
            if (subCat.getSubCategoryID().equals(subCategoriaId)) {
                subCategoria = subCat;
                break;
            }
        }

        if (subCategoria != null) {

            if (subCategoria.getItems() != null && !subCategoria.getItems().isEmpty()) {
                throw new Exception("No se puede eliminar la subcategoría porque contiene artículos");
            } else {

                categoria.getSubCategoryList().remove(subCategoria);
                saveXml();
            }
        } else {
            throw new Exception("Subcategoría no encontrada");
        }
    }


    //Articulps
    public Items articuloGetId(String id) throws Exception {
        for (Category category : data.getCategorias()) {
            for (SubCategory subCategory : category.getSubCategoryList()) {
                Items resultItem = subCategory.getItems().stream()
                        .filter(item -> item.getId().equals(id) || item.getBrand().equals(id))
                        .findFirst()
                        .orElse(null);

                if (resultItem != null) {
                    return resultItem;
                }
            }
        }
        throw new Exception("Artículo no encontrado con el ID o nombre: " + id);
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


                boolean idRepetido = subCat.getItems().stream()
                        .anyMatch(articulo -> articulo.getId().equals(items.getId()));
                if (idRepetido) {
                    throw new Exception("Ya existe un artículo con el mismo ID: " + items.getId());
                }


                subCat.getItems().add(items);
                items.getPresentation().add(presentation);
                saveXml();
                break;
            }
        }

        // Lanzar excepción si la subcategoría no fue encontrada
        if (!subCategoryFound) {
            throw new Exception("Subcategoría no encontrada.");
        }
    }
    public boolean deleteItem(String categoryName, String subCategoryName, String itemId) {
        try {

            List<Items> items = allItems(categoryName, subCategoryName);

            for (Items item : items) {
                if (item.getId().equals(itemId)) {

                    if (!item.getPresentation().isEmpty()) {
                        throw new Exception("El artículo tiene presentaciones asociadas y no puede eliminarse.");
                    }


                    items.remove(item);
                    data.setCategorias(data.getCategorias());
                    saveXml();
                    return true;
                }
            }

            throw new Exception("Artículo no encontrado.");
        } catch (Exception e) {

            throw new RuntimeException("Error al eliminar el artículo: " + e.getMessage(), e);
        }
    }

    public List<Items> allItems(String id, String idSub) throws Exception {
        List<Items> items = new ArrayList<>();
        try{

            Category cat = categoryGetId(id);
            List<SubCategory> subCategories = cat.getSubCategoryList();

            for(SubCategory subCat : subCategories){
                if(subCat.getSubCategoryID().equals(idSub)){
                    items.addAll(subCat.getItems());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return items;
    }

    public void editarArticulo(String idCat, String idSub, String id, String nombre, String marca, String descripcion, String cantidad, String presentacion, String precio) {
        try {
            List<Items> items = allItems(idCat, idSub);
            for (Items item : items) {
                if (item.getId().equals(id)) {
                    item.setBrand(marca);
                    item.setName(nombre);
                    item.setDescription(descripcion);

                    boolean presentacionEncontrada = false;

                    for (Presentation p : item.getPresentation()) {
                        if (p.getMeasure().equals(presentacion)) {
                            presentacionEncontrada = true;
                            EditarPresentation(idCat, idSub, id, new Presentation(presentacion, Double.parseDouble(cantidad), Double.parseDouble(precio)));
                            break;
                        }
                    }
                    if (!presentacionEncontrada) {
                        throw new IllegalArgumentException("No se encontró la presentación especificada.");
                    }
                    break;
                }
            }
            data.setArticulos(items);

        } catch (NumberFormatException e) {
            throw new RuntimeException("Error: La cantidad debe ser un número válido.", e);
        } catch (Exception e) {
            throw new RuntimeException("Error al editar el artículo: " + e.getMessage(), e);
        }
    }

    public boolean BuscarNameArticulos(String idCat, String idSub, String nombre) {
        try {
            List<Items> items = allItems(idCat, idSub);
            boolean nombreExiste = items.stream()
                    .anyMatch(item -> item.getName().equalsIgnoreCase(nombre));
            return nombreExiste;

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el artículo: " + e.getMessage(), e);
        }
    }




    //PRESENTACIONES
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

        try {
            List<Items> items = allItems(id, sub);
            for (Items item : items) {
                if (item.getId().equals(Ar)) {

                    boolean existe = item.getPresentation().stream()
                            .anyMatch(p -> p.getMeasure().equals(presentation.getMeasure()));

                    if (existe) {

                        throw new RuntimeException("La presentación ya existe");
                    } else {
                        item.getPresentation().add(presentation);
                        data.setCategorias(data.getCategorias());
                    }
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void EditarPresentation(String id, String sub, String Ar, Presentation presentation) {

        try {
            List<Items> items = allItems(id, sub);
            for (Items item : items) {
                if (item.getId().equals(Ar)) {

                   for(Presentation p : item.getPresentation()){
                       if(p.getMeasure().equals(presentation.getMeasure())){
                           p.setQuantity(presentation.getQuantity());
                           p.setPrice(presentation.getPrice());
                           data.setCategorias(data.getCategorias());
                       }
                   }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void eliminarPresentation(String id, String sub, String Ar, Presentation presentation) {
        try {
            List<Items> items = allItems(id, sub);

            for (Items item : items) {
                if (item.getId().equals(Ar)) {

                    boolean existe = item.getPresentation().stream()
                            .anyMatch(p -> p.getMeasure().equals(presentation.getMeasure()));

                    if (existe) {
                        item.getPresentation().removeIf(p -> p.getMeasure().equals(presentation.getMeasure()));

                        data.setCategorias(data.getCategorias());
                    } else {
                        throw new IllegalArgumentException("La presentación no existe en el ítem especificado.");
                    }
                    return;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void venderCantidad(String id, String sub, String Ar, String cantidad){

    }
}

