package cr.ac.una.MVC.Model;



import cr.ac.una.Logic.Category;
import cr.ac.una.Logic.Proxy;
import cr.ac.una.Protocol.User;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
//Observable:Genera eventos o notificaciones
//se registra en el observable

public class Model extends java.util.Observable {

private List<Category> categories;

    User currentUser;
    Proxy serviceProxy;


public Model(){
    currentUser = null;
    serviceProxy = (Proxy) Proxy.instance();
    categories = new ArrayList<>();
}

    public User getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        this.commit(Model.USER+Model.CHAT);
    }

    public void commit(int properties){
        this.setChanged();
        this.notifyObservers(properties);
    }

    public static int USER=1;
    public static int CHAT=2;
    public static final int USERS = 4;


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
