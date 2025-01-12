package Data;

import java.util.ArrayList;
import java.util.List;

import Logic.*;

public class Data {

    private List<Category> categorias;
    private List<SubCategory> subcategorias;
    private List<Items> articulos;

    public Data() {
        categorias = new ArrayList<>();
        subcategorias = new ArrayList<>();
        articulos = new ArrayList<>();
    }

    public List<Category> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Category> categorias) {
        this.categorias = categorias;
    }

    public List<SubCategory> getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(List<SubCategory> subcategorias) {
        this.subcategorias = subcategorias;
    }

    public List<Items> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Items> articulos) {
        this.articulos = articulos;
    }

    public void loadXML(){
        XmlPersistent source = new XmlPersistent();
        source.cargarCategorias();
        categorias = source.getCategorias();
    }

}
