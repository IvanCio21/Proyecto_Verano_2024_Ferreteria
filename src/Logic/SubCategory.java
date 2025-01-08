package Logic;

import java.util.ArrayList;
import java.util.List;

public class SubCategory extends Category {

    private List<Items> articulos;

    public SubCategory() {
    }

    public SubCategory(String id, String nombre, String descripcion) {
        super(id, nombre, descripcion);
        this.articulos = new ArrayList<>();
    }

    public List<Items> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Items> articulos) {
        this.articulos = articulos;
    }

    public void addArticulo(Items articulo) {
        this.articulos.add(articulo);
    }
}
