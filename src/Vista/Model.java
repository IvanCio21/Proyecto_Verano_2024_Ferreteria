package Vista;

import Logic.Category;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
//Observable:Genera eventos o notificaciones
//se registra en el observable

public class Model  {

private List<Category> categories;



public Model(){categories = new ArrayList<>();
}


    public List<Category> getCategories(){
    return categories;
}

    public void setCategories(List<Category> categorias) {
        this.categories = categorias;
    }

    public void addCategories(Category categoria)
    {
        this.categories.add(categoria);
    }


}
