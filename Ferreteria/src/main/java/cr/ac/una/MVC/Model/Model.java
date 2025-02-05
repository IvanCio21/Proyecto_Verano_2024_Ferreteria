package cr.ac.una.MVC.Model;



import cr.ac.una.Logic.Category;

import java.util.ArrayList;
import java.util.List;
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
